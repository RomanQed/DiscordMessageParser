package com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand;

import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.Contexts.Message.GuildReceivedContext;
import org.jetbrains.annotations.NotNull;

public class GuildCommandEvent extends BotCommandEvent {
    private final GuildReceivedContext context;

    public GuildCommandEvent(@NotNull GuildReceivedContext context, @NotNull String rawArguments) {
        super(rawArguments);
        this.context = context;
    }

    public @NotNull GuildReceivedContext getContext() {
        return context;
    }
}
