package com.github.romanqed.DiscordMessageParser.CommandUtil.Commands;

import com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.Message.PrivateReceivedContext;

public class PrivateCommand extends GenericCommand {
    public PrivateCommand(String name) {
        super(name);
    }

    public void execute(PrivateReceivedContext context) {
        context.sendMessage(toString());
    }

    @Override
    public String toString() {
        return "[Private] " + name;
    }
}
