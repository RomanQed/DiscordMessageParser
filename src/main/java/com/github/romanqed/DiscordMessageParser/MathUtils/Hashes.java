package com.github.romanqed.DiscordMessageParser.MathUtils;

public class Hashes {
    public static long combineNumbers(long left, long right) {
        return (left << 32) | right;
    }
}
