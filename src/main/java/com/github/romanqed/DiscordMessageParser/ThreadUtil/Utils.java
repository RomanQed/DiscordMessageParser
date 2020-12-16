package com.github.romanqed.DiscordMessageParser.ThreadUtil;

import java.util.Collection;

public class Utils {
    public static final long MIN_FREEZE_TIME = 60000;
    public static final long DEFAULT_FREEZE_TIME = 300000;
    public static final long EXTENDED_FREEZE_TIME = 600000;
    public static final long MAX_FREEZE_TIME = 1800000;

    public static void unSafetyCollectExecutors(Collection<QueueExecutor> collection, long freezingTime) {
        collectExecutors(collection, freezingTime, false);
    }

    public static void safetyCollectExecutors(Collection<QueueExecutor> collection, long freezingTime) {
        collectExecutors(collection, freezingTime, true);
    }

    public static void collectExecutors(Collection<QueueExecutor> collection, long freezingTime, boolean needToWaitEmptyQueue) {
        if (collection == null) {
            return;
        }
        collection.removeIf(predicate -> {
            if (predicate.getFreezingTime() > freezingTime) {
                predicate.stop(needToWaitEmptyQueue);
                return true;
            }
            return false;
        });
    }
}
