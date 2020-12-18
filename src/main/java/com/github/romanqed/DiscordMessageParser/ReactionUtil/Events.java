package com.github.romanqed.DiscordMessageParser.ReactionUtil;

import net.dv8tion.jda.api.entities.User;

import java.util.function.Consumer;

public class Events {
    public static EmojiEvent timeLimitEvent(int lifeTime, Consumer<User> action) {
        return new LinkedEmojiEvent(lifeTime, action);
    }

    public static EmojiEvent callsLimitEvent(int availableCalls, Consumer<User> action) {
        return new LinkedEmojiEvent(Constants.DEFAULT_LIFE_TIME, availableCalls, action);
    }

    public static EmojiEvent newEvent(int lifeTime, int availableCalls, Consumer<User> action) {
        return new LinkedEmojiEvent(lifeTime, availableCalls, action);
    }

    public static EmojiEvent newEvent(Consumer<User> action) {
        return new LinkedEmojiEvent(action);
    }

    public static EmojiEvent infinityLifeTimeEvent(int availableCalls, Consumer<User> action) {
        return new LinkedEmojiEvent(Integer.MAX_VALUE, availableCalls, action);
    }

    public static EmojiEvent infinityCallsEvent(int lifeTime, Consumer<User> action) {
        return new LinkedEmojiEvent(lifeTime, Integer.MAX_VALUE, action);
    }

    public static EmojiEvent infinityEvent(Consumer<User> action) {
        return new LinkedEmojiEvent(Integer.MAX_VALUE, Integer.MAX_VALUE, action);
    }
}
