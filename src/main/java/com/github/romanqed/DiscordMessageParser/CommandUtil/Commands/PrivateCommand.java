package com.github.romanqed.DiscordMessageParser.CommandUtil.Commands;

public class PrivateCommand extends GenericCommand {
    public PrivateCommand(String name) {
        super(name);
    }

    public PrivateCommand() {
        super(null);
    }

    @Override
    public String toString() {
        return "[Private] " + name;
    }
}
