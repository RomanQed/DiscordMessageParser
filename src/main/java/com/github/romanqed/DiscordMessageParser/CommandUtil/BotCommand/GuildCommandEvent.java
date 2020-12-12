package com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand;

import com.github.romanqed.DiscordMessageParser.ButtonUtil.ButtonEventList;
import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.Contexts.Message.GuildMessageContext;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GuildCommandEvent extends BotCommandEvent {
    private final GuildMessageContext context;
    private final GuildMessageReceivedEvent event;

    public GuildCommandEvent(@NotNull GuildMessageReceivedEvent event, @NotNull String rawArguments, @NotNull ButtonEventList buttonEventList) {
        super(rawArguments);
        context = new GuildMessageContext(event.getChannel(), buttonEventList);
        this.event = event;
    }

    public @NotNull GuildMessageReceivedEvent getEvent() {
        return event;
    }

    public @NotNull GuildMessageContext getContext() {
        return context;
    }

    public @Nullable Member getAuthor() {
        return event.getMember();
    }

    public @NotNull String getAuthorMention() {
        return event.getAuthor().getAsMention();
    }

    public @NotNull String getAuthorTag() {
        return event.getAuthor().getAsTag();
    }
}
