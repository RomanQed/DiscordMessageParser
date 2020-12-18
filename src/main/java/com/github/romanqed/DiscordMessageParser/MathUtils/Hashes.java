package com.github.romanqed.DiscordMessageParser.MathUtils;

public class Hashes {
    public static long combineNumbers(long left, long right) {
        return (left << 32) | right;
    }

    public static long calculateReactionId(long channelId, long messageId, long emojiHash) {
        long combine = Hashes.combineNumbers(channelId, messageId);
        return Hashes.combineNumbers(combine, emojiHash);
    }
}
