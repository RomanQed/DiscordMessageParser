package com.github.romanqed.DiscordMessageParser.ReactionUtil;

import com.github.romanqed.DiscordMessageParser.JDAUtil.JDAUtils;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;

public class EmojiEvent {
    private final String unicodeId;
    private final long finalTime;
    private final Consumer<User> action;
    private String messageId;
    private String channelId;

    // TODO сделать количество нажатий
    public EmojiEvent(@Nullable String unicodeId, int lifeTime, @Nullable Consumer<User> action) {
        this.unicodeId = Objects.requireNonNullElse(unicodeId, "\uD83D\uDE00");
        this.finalTime = System.currentTimeMillis() + lifeTime;
        this.action = Objects.requireNonNullElse(action, user -> {});
    }

    public EmojiEvent(int lifeTime, @Nullable Consumer<User> action) {
        this(null, lifeTime, action);
    }

    public EmojiEvent(int lifeTime) {
        this(null, lifeTime, null);
    }

    public @NotNull String getChannelId() {
        return channelId;
    }

    public void setChannelId(@NotNull String channelId) throws IllegalArgumentException {
        if (JDAUtils.isId(channelId)) {
            this.channelId = channelId;
        } else {
            throw new IllegalArgumentException("Invalid id!");
        }
    }

    public void call(User user) {
        try {
            action.accept(user);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public @NotNull String getMessageId() {
        return messageId;
    }

    public void setMessageId(@NotNull String messageId) throws IllegalArgumentException {
        if (JDAUtils.isId(messageId)) {
            this.messageId = messageId;
        } else {
            throw new IllegalArgumentException("Invalid id!");
        }
    }

    public int getRemainingLifeTime() {
        long result = finalTime - System.currentTimeMillis();
        if (result < 0) {
            return 0;
        }
        return (int) result;
    }

    public @NotNull String getId() {
        return channelId + messageId + unicodeId;
    }

    public @NotNull String getUnicodeId() {
        return unicodeId;
    }
}
