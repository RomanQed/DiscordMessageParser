package com.github.romanqed.DiscordMessageParser.JDAUtil.Utils;

import com.github.romanqed.DiscordMessageParser.ReactionUtil.EmojiEvent;
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

    public static Message replyToMessage(Message message, Message content) {
        try {
            return message.reply(content).complete();
        } catch (Exception e) {
            return null;
        }
    }

    public static Message replyToMessage(Message message, String content) {
        try {
            return replyToMessage(message, new MessageBuilder(content).build());
        } catch (Exception e) {
            return null;
        }
    }

    public static void addEventToSentMessage(EmojiEvent event, Message sentMessage) {
        try {
            sentMessage.addReaction(event.getEmoji()).queue();
            event.setChannelId(sentMessage.getChannel().getIdLong());
            event.setMessageId(sentMessage.getIdLong());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
