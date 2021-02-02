package com.github.romanqed.DiscordMessageParser.ReactionUtil;

import java.util.function.Consumer;

public class Events {
    public static EmojiEvent timeLimitEvent(int lifeTime, Consumer<ReactionContext> action) {
        return new LinkedEmojiEvent(lifeTime, action);
    }

    public static EmojiEvent timeLimitEvent(int lifeTime) {
        return timeLimitEvent(lifeTime, null);
    }

    public static EmojiEvent callsLimitEvent(int availableCalls, Consumer<ReactionContext> action) {
        return new LinkedEmojiEvent(Constants.DEFAULT_LIFE_TIME, availableCalls, action);
    }

    public static EmojiEvent callsLimitEvent(int availableCalls) {
        return callsLimitEvent(availableCalls, null);
    }

    public static EmojiEvent newEvent(int lifeTime, int availableCalls, Consumer<ReactionContext> action) {
        return new LinkedEmojiEvent(lifeTime, availableCalls, action);
    }

    public static EmojiEvent newEvent(Consumer<ReactionContext> action) {
        return new LinkedEmojiEvent(action);
    }

    public static EmojiEvent newEvent() {
        return new LinkedEmojiEvent();
    }

    public static EmojiEvent infinityLifeTimeEvent(int availableCalls, Consumer<ReactionContext> action) {
        return new LinkedEmojiEvent(Integer.MAX_VALUE, availableCalls, action);
    }

    public static EmojiEvent infinityLifeTimeEvent(int availableCalls) {
        return infinityLifeTimeEvent(availableCalls, null);
    }

    public static EmojiEvent infinityCallsEvent(int lifeTime, Consumer<ReactionContext> action) {
        return new LinkedEmojiEvent(lifeTime, Integer.MAX_VALUE, action);
    }

    public static EmojiEvent infinityCallsEvent(int lifeTime) {
        return infinityCallsEvent(lifeTime, null);
    }

    public static EmojiEvent infinityEvent(Consumer<ReactionContext> action) {
        return new LinkedEmojiEvent(Integer.MAX_VALUE, Integer.MAX_VALUE, action);
    }

    public static EmojiEvent infinityEvent() {
        return infinityEvent(null);
    }
}
