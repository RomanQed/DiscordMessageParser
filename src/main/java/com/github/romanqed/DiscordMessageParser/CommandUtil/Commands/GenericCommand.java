package com.github.romanqed.DiscordMessageParser.CommandUtil.Commands;

import java.util.Objects;

public class GenericCommand {
    public static final String DEFAULT_COMMAND_NAME = "command";
    protected final String name;

    public GenericCommand(String name) {
        this.name = Objects.requireNonNullElse(name, DEFAULT_COMMAND_NAME);
    }

    public String getName() {
        return name;
    }

    public boolean equalToString(String obj) {
        return name.equals(obj);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GenericCommand) {
            return name.equals(((GenericCommand) obj).name);
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}
