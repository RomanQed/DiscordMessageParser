package com.github.romanqed.DiscordMessageParser.ButtonUtil;

import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.RegexUtil.ArgumentPattern;
import com.github.romanqed.DiscordMessageParser.JDAUtil.JDAUtils;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ButtonEvent {
    private final String unicodeId;
    private final ButtonEventLifeTime lifeTime;
    private final Consumer<User> action;
    private String messageId;
    private String channelId;

    public ButtonEvent(@NotNull String unicodeId, @NotNull ButtonEventLifeTime lifeTime, @NotNull Consumer<User> action) {
        this.unicodeId = unicodeId;
        this.lifeTime = lifeTime;
        this.action = action;
    }

    public static ButtonEvent createInfEvent(@NotNull String unicodeId, @NotNull Consumer<User> action) {
        return new ButtonEvent(unicodeId, ButtonEventLifeTime.INFINITE, action);
    }

    public static ButtonEvent createDisEvent(@NotNull String unicodeId, @NotNull Consumer<User> action) {
        return new ButtonEvent(unicodeId, ButtonEventLifeTime.DISPOSABLE, action);
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(@NotNull String channelId) throws IllegalArgumentException {
        if (JDAUtils.isId(channelId)) {
            this.channelId = channelId;
        } else {
            throw new IllegalArgumentException("Invalid id!");
        }
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(@NotNull String messageId) throws IllegalArgumentException {
        if (JDAUtils.isId(messageId)) {
            this.messageId = messageId;
        } else {
            throw new IllegalArgumentException("Invalid id!");
        }
    }

    public ButtonEventLifeTime getLifeTime() {
        return lifeTime;
    }

    public String getId() {
        return channelId + messageId + unicodeId;
    }

    public String getUnicodeId() {
        return unicodeId;
    }

    public Consumer<User> getAction() {
        return action;
    }
}
