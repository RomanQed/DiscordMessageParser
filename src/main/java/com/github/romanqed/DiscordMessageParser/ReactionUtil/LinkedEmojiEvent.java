package com.github.romanqed.DiscordMessageParser.ReactionUtil;

import com.github.romanqed.DiscordMessageParser.MathUtils.Hashes;
import net.dv8tion.jda.api.entities.User;

import java.util.Objects;
import java.util.function.Consumer;

public class LinkedEmojiEvent implements EmojiEvent {
    public static final EventCollection COLLECTION = new EventCollection();
    private final long finalTime;
    private final Consumer<User> action;
    private Runnable byEnd;
    private String emoji;
    private long id;
    private long messageId;
    private long channelId;
    private int availableCalls;

    public LinkedEmojiEvent(int lifeTime, int availableCalls, Consumer<User> action, Runnable byEnd) {
        this.finalTime = System.currentTimeMillis() + Math.max(lifeTime, 0);
        this.availableCalls = Math.max(availableCalls, 1);
        this.action = Objects.requireNonNullElse(action, System.out::println);
        this.byEnd = Objects.requireNonNullElse(byEnd, () -> {
        });
        emoji = Constants.DEFAULT_EMOJI;
    }

    public LinkedEmojiEvent(int lifeTime, int availableCalls, Consumer<User> action) {
        this(lifeTime, availableCalls, action, null);
    }

    public LinkedEmojiEvent(int lifeTime, int availableCalls) {
        this(lifeTime, availableCalls, null, null);
    }

    public LinkedEmojiEvent(int lifeTime, Consumer<User> action) {
        this(lifeTime, Constants.DEFAULT_CALLS_AMOUNT, action, null);
    }

    public LinkedEmojiEvent(Consumer<User> action) {
        this(Constants.DEFAULT_LIFE_TIME, Constants.DEFAULT_CALLS_AMOUNT, action, null);
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
    public void atFinal(Runnable action) {
        if (action != null) {
            byEnd = action;
        }
    }

    @Override
    public void call(User sender) {
        try {
            if (isFinished()) {
                COLLECTION.remove(id);
            } else {
                action.accept(sender);
                availableCalls = Math.max(availableCalls - 1, 0);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void finalEvent() {
        byEnd.run();
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
        this.id = Hashes.calculateReactionId(channelId, messageId, emoji.hashCode());
    }
}
