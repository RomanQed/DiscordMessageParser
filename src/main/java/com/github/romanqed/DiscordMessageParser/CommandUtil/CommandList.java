package com.github.romanqed.DiscordMessageParser.CommandUtil;

import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
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

    public void addCommand(@NotNull Command command) {
        if (command.getType() != CommandType.EmptyCommand) {
            String key = Utils.getKey(command);
            commands.put(key, command);
        }
    }

    public void addCommands(@NotNull List<Command> commandList) {
        for (Command command : commandList) {
            addCommand(command);
        }
    }

    public Command getCommand(@NotNull String command, @NotNull CommandType commandType) {
        String key = Utils.getKey(command, commandType);
        return Objects.requireNonNullElse(commands.get(key), new Command());
    }

    public void removeCommand(@NotNull String command, @NotNull CommandType commandType) {
        String key = Utils.getKey(command, commandType);
        commands.remove(key);
    }

    public void removeCommand(@NotNull Command command) {
        removeCommand(command.getName(), command.getType());
    }

    public boolean containsCommand(@NotNull String command, @NotNull CommandType commandType) {
        return commands.containsKey(Utils.getKey(command, commandType));
    }

    public boolean containsCommand(@NotNull Command command) {
        return containsCommand(command.getName(), command.getType());
    }

    public boolean isEmpty() {
        return commands.isEmpty();
    }

    public int size() {
        return commands.size();
    }
}
