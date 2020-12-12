package com.github.romanqed.DiscordMessageParser.ThreadUtil;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class QueueExecutor implements Runnable {
    private final ConcurrentLinkedQueue<Runnable> queue;
    private final AtomicBoolean needInterrupt;
    private long startFreezingTime = -1;

    public QueueExecutor() {
        this.queue = new ConcurrentLinkedQueue<>();
        this.needInterrupt = new AtomicBoolean(false);
    }

    @Override
    public void run() {
        needInterrupt.set(false);
        Runnable action;
        while (!needInterrupt.get()) {
            synchronized (queue) {
                if (queue.isEmpty()) {
                    try {
                        startFreezingTime = System.currentTimeMillis();
                        queue.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                if (queue.isEmpty()) {
                    continue;
                }
                action = queue.remove();
            }
            action.run();
            synchronized (this) {
                if (queue.isEmpty()) {
                    notify();
                }
            }
        }
    }

    public synchronized void stop(boolean needToWaitEmptyQueue) {
        if (!queue.isEmpty() && needToWaitEmptyQueue) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        needInterrupt.set(true);
        synchronized (queue) {
            queue.notify();
        }
    }

    public void addToQueue(@NotNull Runnable runnable) {
        runnable = Objects.requireNonNullElse(runnable, () -> {
        });
        synchronized (queue) {
            queue.add(runnable);
            startFreezingTime = -1;
            queue.notify();
        }
    }

    public boolean isFinished() {
        return needInterrupt.get();
    }

    public long getFreezingTime() {
        if (startFreezingTime < 0) {
            return 0;
        }
        return System.currentTimeMillis() - startFreezingTime;
    }
}
