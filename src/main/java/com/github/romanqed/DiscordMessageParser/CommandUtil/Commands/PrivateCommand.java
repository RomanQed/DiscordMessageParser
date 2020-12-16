package com.github.romanqed.DiscordMessageParser.CommandUtil.Commands;

import com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.DefaultContext;

public class PrivateCommand extends GenericCommand {
    public PrivateCommand(String name) {
        super(name);
    }

    public PrivateCommand() {
        super(null);
    }

    public void execute(DefaultContext context) {
        context.sendMessage(toString());
    }

    @Override
    public String toString() {
        return "[Private] " + name;
    }
}
