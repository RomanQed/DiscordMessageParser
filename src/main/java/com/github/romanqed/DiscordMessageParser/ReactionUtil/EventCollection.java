package com.github.romanqed.DiscordMessageParser.ReactionUtil;

import net.dv8tion.jda.api.entities.User;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

// TODO Сделать удаление отживших событий в отдельном потоке
public class EventCollection {
    private final ConcurrentHashMap<Long, EmojiEvent> events;

    public EventCollection() {
        events = new ConcurrentHashMap<>();
    }

    public void execute(long id, User user) {
        if (user == null) {
            return;
        }
        EmojiEvent event = events.get(id);
        if (event == null) {
            return;
        }
        if (event.isFinished()) {
            events.remove(id);
        } else {
            event.call(user);
        }
    }

    public void add(EmojiEvent event) {
        if (event == null) {
            return;
        }
        events.put(event.getId(), event);
    }

    public void add(List<EmojiEvent> eventList) {
        if (eventList == null) {
            return;
        }
        for (EmojiEvent event : eventList) {
            events.put(event.getId(), event);
        }
    }

    public void remove(long messageId) {
        events.values().removeIf(item -> item.getMessageId() == messageId);
    }

    public int size() {
        return events.size();
    }
}
