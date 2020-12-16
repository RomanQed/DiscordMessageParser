package com.github.romanqed.DiscordMessageParser.JDAUtil;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;

public class MessageUtils {
    public static Message sendMessage(MessageChannel channel, Message message) {
        try {
            return channel.sendMessage(message).complete();
        } catch (Exception e) {
            return null;
        }
    }

    public static Message sendMessage(MessageChannel channel, String message) {
        try {
            return sendMessage(channel, new MessageBuilder(message).build());
        } catch (Exception e) {
            return null;
        }
    }
}
