package com.github.romanqed.DiscordMessageParser.ButtonUtil;

import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ButtonEventList {
    private final ConcurrentHashMap<String, ButtonEvent> events;

    public ButtonEventList() {
        events = new ConcurrentHashMap<>();
    }

    public void execute(@NotNull String id, @NotNull User user) {
        ButtonEvent event = events.get(id);
        if (event != null) {
            event.getAction().accept(user);
            if (event.getLifeTime() == ButtonEventLifeTime.DISPOSABLE) {
                events.remove(id);
            }
        }
    }

    public void add(@NotNull ButtonEvent event) {
        String id = event.getId();
        events.put(id, event);
    }

    public void add(@NotNull List<ButtonEvent> eventList) {
        for (ButtonEvent event : eventList) {
            add(event);
        }
    }

    public int size() {
        return events.size();
    }
}
