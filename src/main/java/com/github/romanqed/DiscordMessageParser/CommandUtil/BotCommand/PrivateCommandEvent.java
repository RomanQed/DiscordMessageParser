package com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand;

import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.Contexts.Message.PrivateReceivedContext;
import org.jetbrains.annotations.NotNull;

public class PrivateCommandEvent extends BotCommandEvent {
    private final PrivateReceivedContext context;

    public PrivateCommandEvent(@NotNull PrivateReceivedContext context, @NotNull String rawArguments) {
        super(rawArguments);
        this.context = context;
    }

    public PrivateReceivedContext getContext() {
        return context;
    }
}
