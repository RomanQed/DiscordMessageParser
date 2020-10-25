package com.github.romanqed.DiscordMessageParser.ButtonUtil;

import net.dv8tion.jda.api.entities.User;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ButtonEventList {
    private final ConcurrentHashMap<String, ButtonEvent> events;

    public ButtonEventList() {
        events = new ConcurrentHashMap<>();
    }

    public void execute(String id, User user) {
        if (id == null || user == null) {
            return;
        }
        ButtonEvent event = events.get(id);
        if (event == null) {
            return;
        }
        event.getAction().accept(user);
        if (event.getLifeTime() == ButtonEventLifeTime.DISPOSABLE) {
            events.remove(id);
        }
    }

    public void add(ButtonEvent event) {
        if (event == null) {
            return;
        }
        events.put(event.getId(), event);
    }

    public void add(List<ButtonEvent> eventList) {
        if (eventList == null) {
            return;
        }
        for (ButtonEvent event : eventList) {
            add(event);
        }
    }

    public int size() {
        return events.size();
    }
}
