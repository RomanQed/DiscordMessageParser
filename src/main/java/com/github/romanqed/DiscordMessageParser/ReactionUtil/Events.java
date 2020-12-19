package com.github.romanqed.DiscordMessageParser.ReactionUtil;

import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;

import java.util.function.BiConsumer;

public class Events {
    public static EmojiEvent timeLimitEvent(int lifeTime, BiConsumer<MessageReaction, User> action) {
        return new LinkedEmojiEvent(lifeTime, action);
    }

    public static EmojiEvent callsLimitEvent(int availableCalls, BiConsumer<MessageReaction, User> action) {
        return new LinkedEmojiEvent(Constants.DEFAULT_LIFE_TIME, availableCalls, action);
    }

    public static EmojiEvent newEvent(int lifeTime, int availableCalls, BiConsumer<MessageReaction, User> action) {
        return new LinkedEmojiEvent(lifeTime, availableCalls, action);
    }

    public static EmojiEvent newEvent(BiConsumer<MessageReaction, User> action) {
        return new LinkedEmojiEvent(action);
    }

    public static EmojiEvent infinityLifeTimeEvent(int availableCalls, BiConsumer<MessageReaction, User> action) {
        return new LinkedEmojiEvent(Integer.MAX_VALUE, availableCalls, action);
    }

    public static EmojiEvent infinityCallsEvent(int lifeTime, BiConsumer<MessageReaction, User> action) {
        return new LinkedEmojiEvent(lifeTime, Integer.MAX_VALUE, action);
    }

    public static EmojiEvent infinityEvent(BiConsumer<MessageReaction, User> action) {
        return new LinkedEmojiEvent(Integer.MAX_VALUE, Integer.MAX_VALUE, action);
    }
}
