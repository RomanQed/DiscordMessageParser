package com.github.romanqed.DiscordMessageParser.CommandUtil;

import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.Utils;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class CommandList {
    private final ConcurrentHashMap<String, Command> commands;

    public CommandList() {
        commands = new ConcurrentHashMap<>();
    }

    public CommandList(List<Command> commandList) {
        this();
        addCommands(commandList);
    }

    public void addCommand(Command command) {
        if (command == null || command.getType() == CommandType.EmptyCommand) {
            return;
        }
        String key = Utils.getKey(command);
        commands.put(key, command);
    }

    public void addCommands(List<Command> commandList) {
        if (commandList == null) {
            return;
        }
        for (Command command : commandList) {
            addCommand(command);
        }
    }

    public Command getCommand(String command, CommandType commandType) {
        String key = Utils.getKey(command, commandType);
        return Objects.requireNonNullElse(commands.get(key), new Command());
    }

    public void removeCommand(String command, CommandType commandType) {
        String key = Utils.getKey(command, commandType);
        commands.remove(key);
    }

    public void removeCommand(Command command) {
        removeCommand(command.getName(), command.getType());
    }

    public boolean containsCommand(String command, CommandType commandType) {
        return commands.containsKey(Utils.getKey(command, commandType));
    }

    public boolean containsCommand(Command command) {
        return command != null && containsCommand(command.getName(), command.getType());
    }

    public boolean isEmpty() {
        return commands.isEmpty();
    }

    public int size() {
        return commands.size();
    }
}
