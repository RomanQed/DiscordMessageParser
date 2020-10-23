package com.github.romanqed.DiscordMessageParser.CommandUtil;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Command {
    protected final String name;
    protected final CommandType type;

    public Command() {
        this("", CommandType.EmptyCommand);
    }

    public Command(@NotNull String name, @NotNull CommandType type) {
        this.name = Objects.requireNonNullElse(name, "");
        this.type = Objects.requireNonNullElse(type, CommandType.EmptyCommand);
    }

    public String getName() {
        return name;
    }

    public CommandType getType() {
        return type;
    }

    public boolean isEmpty() {
        return type == CommandType.EmptyCommand;
    }

    public boolean equalsToCommand(Command command) {
        return name.contentEquals(command.name) && type.equals(command.type);
    }
}
