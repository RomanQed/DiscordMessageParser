package com.github.romanqed.DiscordMessageParser.Utils;

import java.util.Collection;

public class Checks {
    public static boolean inStrictRange(long value, long left, long right) {
        return left < value && right > value;
    }

    public static boolean inRange(long value, long left, long right) {
        return left <= value && right >= value;
    }

    public static boolean inStrictLeftRange(long value, long left, long right) {
        return left < value && right >= value;
    }

    public static boolean inStrictRightRange(long value, long left, long right) {
        return left <= value && right > value;
    }

    public static boolean isEmptyCollection(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
