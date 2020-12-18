package com.github.romanqed.DiscordMessageParser.ProcessUtil;

import com.github.romanqed.DiscordMessageParser.ThreadUtil.QueueExecutor;
import com.github.romanqed.DiscordMessageParser.ThreadUtil.ThreadUtils;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GuildService {
    private final ExecutorService service;
    private final Map<Long, QueueExecutor> guildExecutors;

    public GuildService(ExecutorService service) {
        this.service = Objects.requireNonNullElse(service, Executors.newCachedThreadPool());
        this.guildExecutors = new ConcurrentHashMap<>();
    }

    public GuildService() {
        this(null);
    }

    public void addToQueue(long guildId, Runnable task) {
        QueueExecutor executor = guildExecutors.get(guildId);
        if (executor == null) {
            executor = new QueueExecutor();
            service.submit(executor);
            guildExecutors.put(guildId, executor);
        }
        executor.addToQueue(task);
        service.submit(() -> ThreadUtils.safetyCollectExecutors(guildExecutors.values(), ThreadUtils.DEFAULT_FREEZE_TIME));
    }

    public void dropGuildQueue(long guildId, boolean safetyDrop) {
        QueueExecutor executor = guildExecutors.get(guildId);
        if (executor != null) {
            executor.stop(safetyDrop);
        }
    }
}
