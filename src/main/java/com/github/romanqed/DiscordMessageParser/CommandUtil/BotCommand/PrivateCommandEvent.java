package com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand;

import com.github.romanqed.DiscordMessageParser.ButtonUtil.ButtonEvent;
import com.github.romanqed.DiscordMessageParser.ButtonUtil.ButtonEventList;
import com.github.romanqed.DiscordMessageParser.JDAUtil.JDAUtils;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
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

    public void sendMessage(@NotNull Message message, @Nullable ButtonEvent buttonEvent) {
        MessageAction action;
        try {
            action = event.getChannel().sendMessage(message);
        } catch (Exception e) {
            return;
        }
        if (buttonEvent != null) {
            action.queue(sentMessage -> {
                sentMessage.addReaction(buttonEvent.getUnicodeId()).queue();
                buttonEvent.setChannelId(event.getChannel().getId());
                buttonEvent.setMessageId(sentMessage.getId());
                buttonEventList.add(buttonEvent);
            });
        } else {
            action.queue();
        }
    }

    public void sendMessage(@NotNull String rawMessage, @Nullable ButtonEvent buttonEvent) {
        Message message;
        try {
            message = new MessageBuilder(rawMessage).build();
        } catch (Exception e) {
            return;
        }
        sendMessage(message, buttonEvent);
    }

    public void sendMessage(@NotNull Message message) {
        sendMessage(message, null);
    }

    public void sendMessage(@NotNull String rawMessage) {
        sendMessage(rawMessage, null);
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
