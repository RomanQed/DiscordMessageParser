package com.github.romanqed.DiscordMessageParser.CommandUtil;

import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.GenericCommand;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandCollection <T extends GenericCommand> {
    private final Map<String, T> collection;

    public CommandCollection(Collection<T> commands) {
        collection = new ConcurrentHashMap<>();
        addCommands(commands);
    }

    public CommandCollection() {
        this(null);
    }

    public void addCommands(Collection<T> commands) {
        if (commands == null) {
            return;
        }
        commands.forEach(command -> {
            collection.put(command.getName(), command);
        });
    }

    public void addCommand(T command) {
        if (command != null) {
            collection.put(command.getName(), command);
        }
    }

    public T findCommand(String name) {
        if (name != null) {
            return collection.get(name);
        }
        return null;
    }

    public void removeCommand(String name) {
        if (name != null) {
            collection.remove(name);
        }
    }

    public void removeCommand(T command) {
        if (command != null) {
            removeCommand(command.getName());
        }
    }
}
