package com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand;

import com.github.romanqed.DiscordMessageParser.ButtonUtil.ButtonEventList;
import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.Contexts.Message.PrivateMessageContext;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

public class PrivateCommandEvent extends BotCommandEvent {
    private final PrivateMessageReceivedEvent event;
    private final PrivateMessageContext context;

    public PrivateCommandEvent(@NotNull PrivateMessageReceivedEvent event, @NotNull String rawArguments, @NotNull ButtonEventList buttonEventList) {
        super(rawArguments);
        this.event = event;
        this.context = new PrivateMessageContext(event.getChannel(), buttonEventList);
    }

    public PrivateMessageReceivedEvent getEvent() {
        return event;
    }

    public PrivateMessageContext getContext() {
        return context;
    }

    public @NotNull User getAuthor() {
        return event.getAuthor();
    }

    public @NotNull String getAuthorMention() {
        return event.getAuthor().getAsTag();
    }

    public @NotNull String getAuthorTag() {
        return event.getAuthor().getAsTag();
    }

    public @NotNull PrivateChannel getChannel() {
        return event.getChannel();
    }
}
