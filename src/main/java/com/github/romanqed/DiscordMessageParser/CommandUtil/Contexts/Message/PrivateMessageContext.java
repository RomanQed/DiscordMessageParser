package com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.Message;

import com.github.romanqed.DiscordMessageParser.ButtonUtil.ButtonEvent;
import com.github.romanqed.DiscordMessageParser.ButtonUtil.ButtonEventList;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.Base.JDAContext;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PrivateChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PrivateMessageContext extends JDAContext {
    protected final PrivateChannel channel;

    public PrivateMessageContext(PrivateChannel channel, ButtonEventList buttonEventList) {
        super(channel.getJDA(), buttonEventList);
        this.channel = channel;
    }

    public PrivateChannel getChannel() {
        return channel;
    }

    public Message sendMessage(@NotNull Message message, @Nullable ButtonEvent buttonEvent) {
        return sendMessage(channel, message, buttonEvent);
    }

    public Message sendMessage(@NotNull String rawMessage, @Nullable ButtonEvent buttonEvent) {
        Message message;
        try {
            message = new MessageBuilder(rawMessage).build();
        } catch (Exception e) {
            return null;
        }
        return sendMessage(message, buttonEvent);
    }

    public Message sendMessage(@NotNull Message message) {
        return sendMessage(message, null);
    }

    public Message sendMessage(@NotNull String rawMessage) {
        return sendMessage(rawMessage, null);
    }
}
