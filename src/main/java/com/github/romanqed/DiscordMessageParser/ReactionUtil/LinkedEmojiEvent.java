package com.github.romanqed.DiscordMessageParser.ReactionUtil;

import com.github.romanqed.DiscordMessageParser.MathUtils.Hashes;
import net.dv8tion.jda.api.entities.User;

import java.util.Objects;
import java.util.function.Consumer;

public class LinkedEmojiEvent implements EmojiEvent {
    public static final EventCollection COLLECTION = new EventCollection();
    private final long finalTime;
    private final Consumer<User> action;
    private String emoji;
    private long id;
    private long messageId;
    private long channelId;
    private int availableCalls;

    public LinkedEmojiEvent(long lifeTime, int availableCalls, Consumer<User> action) {
        this.finalTime = System.currentTimeMillis() + Math.max(lifeTime, 0);
        this.availableCalls = Math.max(availableCalls, 1);
        this.action = Objects.requireNonNullElse(action, System.out::println);
        emoji = Constants.DEFAULT_EMOJI;
        COLLECTION.add(this);
    }

    public LinkedEmojiEvent(long lifeTime, int availableCalls) {
        this(lifeTime, availableCalls, null);
    }

    public LinkedEmojiEvent(long lifeTime, Consumer<User> action) {
        this(lifeTime, Constants.DEFAULT_CALLS_AMOUNT, action);
    }

    public LinkedEmojiEvent(int availableCalls, Consumer<User> action) {
        this(Constants.DEFAULT_LIFE_TIME, availableCalls, action);
    }

    public LinkedEmojiEvent(Consumer<User> action) {
        this(Constants.DEFAULT_LIFE_TIME, Constants.DEFAULT_CALLS_AMOUNT, action);
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public long getMessageId() {
        return messageId;
    }

    @Override
    public void setMessageId(long messageId) {
        this.messageId = Math.max(messageId, 0);
        calculateId();
    }

    @Override
    public long getChannelId() {
        return channelId;
    }

    @Override
    public void setChannelId(long channelId) {
        this.channelId = Math.max(channelId, 0);
        calculateId();
    }

    @Override
    public String getEmoji() {
        return emoji;
    }

    @Override
    public void setEmoji(String emoji) {
        this.emoji = Objects.requireNonNullElse(emoji, Constants.DEFAULT_EMOJI);
    }

    @Override
    public void call(User sender) {
        try {
            action.accept(sender);
            availableCalls = Math.max(availableCalls - 1, 0);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public int getRemainingLifeTime() {
        return (int) Math.max(finalTime - System.currentTimeMillis(), 0);
    }

    @Override
    public int getRemainingCalls() {
        return availableCalls;
    }

    @Override
    public boolean isFinished() {
        return getRemainingLifeTime() == 0 || getRemainingCalls() == 0;
    }

    private void calculateId() {
        long combine = Hashes.combineNumbers(channelId, messageId);
        this.id = Hashes.combineNumbers(combine, emoji.hashCode());
    }
}
