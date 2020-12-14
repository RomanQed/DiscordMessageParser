package com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.Message;

import com.github.romanqed.DiscordMessageParser.ButtonUtil.ButtonEventList;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;

public class PrivateReceivedContext extends PrivateMessageContext {
    private final Message message;

    public PrivateReceivedContext(Message message, ButtonEventList buttonEventList) {
        super(message.getPrivateChannel(), buttonEventList);
        this.message = message;
    }

    public @NotNull User getAuthor() {
        return message.getAuthor();
    }

    public @NotNull String getAuthorMention() {
        return message.getAuthor().getAsTag();
    }

    public @NotNull String getAuthorTag() {
        return message.getAuthor().getAsTag();
    }

    public @NotNull PrivateChannel getChannel() {
        return channel;
    }
}
