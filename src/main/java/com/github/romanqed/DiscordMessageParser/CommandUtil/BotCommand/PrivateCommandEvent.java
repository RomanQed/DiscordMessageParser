package com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand;

import com.github.romanqed.DiscordMessageParser.ButtonUtil.ButtonEvent;
import com.github.romanqed.DiscordMessageParser.ButtonUtil.ButtonEventList;
import com.github.romanqed.DiscordMessageParser.JDAUtil.JDAUtils;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PrivateCommandEvent extends BotCommandEvent {
    private final PrivateMessageReceivedEvent event;

    public PrivateCommandEvent(@NotNull PrivateMessageReceivedEvent event, @NotNull String rawArguments, @NotNull ButtonEventList buttonEventList) {
        super(rawArguments, buttonEventList);
        this.event = event;
    }

    public PrivateMessageReceivedEvent getEvent() {
        return event;
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

    public Message sendMessage(@NotNull Message message, @Nullable ButtonEvent buttonEvent) {
        try {
            Message sentMessage = event.getChannel().sendMessage(message).complete();
            if (buttonEvent != null) {
                addEventToSentMessage(sentMessage, buttonEvent);
            }
            return sentMessage;
        } catch (Exception e) {
            return null;
        }
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

    public @NotNull User getAuthor() {
        return event.getAuthor();
    }

    public @NotNull String getAuthorMention() {
        return event.getAuthor().getAsTag();
    }

    public @NotNull String getTaggedAuthorName() {
        return event.getAuthor().getAsTag();
    }

    public @NotNull PrivateChannel getChannel() {
        return event.getChannel();
    }

    public @Nullable User getUserById(long id) {
        try {
            return event.getJDA().retrieveUserById(id).complete();
        } catch (Exception e) {
            return null;
        }
    }

    public @Nullable User getUserById(String id) {
        try {
            return event.getJDA().retrieveUserById(id).complete();
        } catch (Exception e) {
            return null;
        }
    }

    public @Nullable User getUserByMention(String userMention) {
        try {
            return event.getJDA().retrieveUserById(JDAUtils.parseUserMention(userMention)).complete();
        } catch (Exception e) {
            return null;
        }
    }
}
