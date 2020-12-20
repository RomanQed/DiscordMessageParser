package com.github.romanqed.DiscordMessageParser.JDAUtil.Utils;

import com.github.romanqed.DiscordMessageParser.ReactionUtil.EmojiEvent;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.LinkedEmojiEvent;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

public class MessageUtils {
    public static Message completeMessage(MessageChannel channel, Message message) {
        try {
            return channel.sendMessage(message).complete();
        } catch (Exception e) {
            return null;
        }
    }

    public static Message completeMessage(MessageChannel channel, String message) {
        try {
            return completeMessage(channel, new MessageBuilder(message).build());
        } catch (Exception e) {
            return null;
        }
    }

    public static Message completeReplyToMessage(Message message, Message content) {
        try {
            return message.reply(content).complete();
        } catch (Exception e) {
            return null;
        }
    }

    public static Message completeReplyToMessage(Message message, String content) {
        try {
            return completeReplyToMessage(message, new MessageBuilder(content).build());
        } catch (Exception e) {
            return null;
        }
    }

    public static void addEventToSentMessage(EmojiEvent event, Message sentMessage) {
        if (event == null) {
            return;
        }
        sentMessage.addReaction(event.getEmoji()).queue();
        event.setChannelId(sentMessage.getChannel().getIdLong());
        event.setMessageId(sentMessage.getIdLong());
        LinkedEmojiEvent.COLLECTION.add(event);
    }

    public static boolean addEventToMessageAction(EmojiEvent event, MessageAction action) {
        if (event == null) {
            return false;
        }
        action.queue(sentMessage -> {
            sentMessage.addReaction(event.getEmoji()).queue();
            event.setChannelId(sentMessage.getChannel().getIdLong());
            event.setMessageId(sentMessage.getIdLong());
            LinkedEmojiEvent.COLLECTION.add(event);
        });
        return true;
    }
}
