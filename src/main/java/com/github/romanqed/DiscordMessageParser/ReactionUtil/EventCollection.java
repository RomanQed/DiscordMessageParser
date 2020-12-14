package com.github.romanqed.DiscordMessageParser.ReactionUtil;

import net.dv8tion.jda.api.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

// TODO Сделать удаление отживших событий
public class EventCollection {
    private final ConcurrentHashMap<String, EmojiEvent> events;

    public EventCollection() {
        events = new ConcurrentHashMap<>();
    }

    public void execute(String id, User user) {
        if (id == null || user == null) {
            return;
        }
        EmojiEvent event = events.get(id);
        if (event == null) {
            return;
        }
        if (event.getRemainingLifeTime() == 0) {
            events.remove(id);
        }
        else {
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
            add(event);
        }
    }

    public void remove(String messageId) {
        Optional<String> found = events.keySet().stream().findFirst().filter(item -> item.contains(messageId));
        found.ifPresent(events::remove);
    }

    public int size() {
        return events.size();
    }
}
