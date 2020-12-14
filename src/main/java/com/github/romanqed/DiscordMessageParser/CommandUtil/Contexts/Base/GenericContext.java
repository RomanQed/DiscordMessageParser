package com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.Base;

import com.github.romanqed.DiscordMessageParser.ButtonUtil.ButtonEvent;
import com.github.romanqed.DiscordMessageParser.ButtonUtil.ButtonEventList;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GenericContext {
    protected final ButtonEventList buttonEventList;

    public GenericContext(ButtonEventList buttonEventList) {
        this.buttonEventList = buttonEventList;
    }

    public void addEventToSentMessage(Message sentMessage, ButtonEvent event) {
        try {
            sentMessage.addReaction(event.getUnicodeId()).queue();
            event.setChannelId(sentMessage.getChannel().getId());
            event.setMessageId(sentMessage.getId());
            buttonEventList.add(event);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public Message sendMessage(@NotNull MessageChannel channel, @NotNull Message message, @Nullable ButtonEvent buttonEvent) {
        try {
            Message sentMessage = channel.sendMessage(message).complete();
            if (buttonEvent != null) {
                addEventToSentMessage(sentMessage, buttonEvent);
            }
            return sentMessage;
        } catch (Exception e) {
            return null;
        }
    }

    public Message sendMessage(@NotNull MessageChannel channel, @NotNull String rawMessage, @Nullable ButtonEvent buttonEvent) {
        Message message;
        try {
            message = new MessageBuilder(rawMessage).build();
        } catch (Exception e) {
            return null;
        }
        return sendMessage(channel, message, buttonEvent);
    }
}
