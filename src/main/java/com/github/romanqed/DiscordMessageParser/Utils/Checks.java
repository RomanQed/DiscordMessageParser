package com.github.romanqed.DiscordMessageParser.Utils;

import java.util.Collection;
import java.util.concurrent.Callable;

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

    public static <T> T requireNonExcept(Callable<T> expression, T def) {
        try {
            return expression.call();
        } catch (Exception e) {
            return def;
        }
    }
}
