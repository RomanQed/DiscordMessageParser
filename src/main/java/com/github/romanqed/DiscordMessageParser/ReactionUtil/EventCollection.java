package com.github.romanqed.DiscordMessageParser.ReactionUtil;

import com.github.romanqed.DiscordMessageParser.JDAUtil.Utils.Processing;
import net.dv8tion.jda.api.entities.MessageReaction;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EventCollection {
    private final ConcurrentHashMap<Long, EmojiEvent> events;
    private final ConcurrentHashMap<Long, Future<?>> tasks;
    private final ExecutorService executors;

    public EventCollection() {
        events = new ConcurrentHashMap<>();
        tasks = new ConcurrentHashMap<>();
        executors = Executors.newCachedThreadPool();
    }

    public void add(EmojiEvent event) {
        if (event == null) {
            return;
        }
        long id = event.getId();
        Future<?> task = executors.submit(() -> {
            try {
                Thread.sleep(event.getRemainingLifeTime());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            event.finalEvent();
            events.remove(id);
        });
        tasks.put(id, task);
        events.put(id, event);
    }

    public void add(List<EmojiEvent> eventList) {
        if (eventList == null) {
            return;
        }
        for (EmojiEvent event : eventList) {
            add(event);
        }
    }

    public EmojiEvent findByReaction(MessageReaction reaction) {
        if (reaction == null) {
            return null;
        }
        long id = Processing.calculateReactionId(reaction);
        return events.get(id);
    }

    public void remove(long id) {
        Future<?> task = tasks.get(id);
        if (task != null) {
            task.cancel(true);
            tasks.remove(id);
        }
        events.remove(id);
    }

    public void removeByMessageId(long messageId) {
        events.values().forEach(event -> {
            if (event.getMessageId() == messageId) {
                remove(event.getId());
            }
        });
    }

    public int size() {
        return events.size();
    }
}
