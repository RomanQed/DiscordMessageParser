package com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts;

import com.github.romanqed.DiscordMessageParser.ReactionUtil.EmojiEvent;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EventCollection;
import net.dv8tion.jda.api.entities.Message;

public class DefaultContext extends GenericContext {
    protected final EventCollection events;

    public DefaultContext(Message body, String arguments, EventCollection events) {
        super(body, arguments);
        this.events = events;
    }

    public DefaultContext(Message body, String arguments) {
        this(body, arguments, null);
    }

    public void addEventToSentMessage(EmojiEvent event, Message sentMessage) {
        if (events == null) {
            return;
        }
        try {
            sentMessage.addReaction(event.getUnicodeId()).queue();
            event.setChannelId(sentMessage.getChannel().getId());
            event.setMessageId(sentMessage.getId());
            events.add(event);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public Message sendMessage(Message message, EmojiEvent event) {
        Message sentMessage = sendMessage(message);
        addEventToSentMessage(event, sentMessage);
        return sentMessage;
    }

    public Message sendMessage(String message, EmojiEvent event) {
        Message sentMessage = sendMessage(message);
        addEventToSentMessage(event, sentMessage);
        return sentMessage;
    }
}
