package com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.Message;

import com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.Base.JDAContext;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EmojiEvent;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EventCollection;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PrivateChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PrivateMessageContext extends JDAContext {
    protected final PrivateChannel channel;

    public PrivateMessageContext(PrivateChannel channel, EventCollection eventCollection) {
        super(channel.getJDA(), eventCollection);
        this.channel = channel;
    }

    public PrivateChannel getChannel() {
        return channel;
    }

    public Message sendMessage(@NotNull Message message, @Nullable EmojiEvent emojiEvent) {
        return sendMessage(channel, message, emojiEvent);
    }

    public Message sendMessage(@NotNull String rawMessage, @Nullable EmojiEvent emojiEvent) {
        Message message;
        try {
            message = new MessageBuilder(rawMessage).build();
        } catch (Exception e) {
            return null;
        }
        return sendMessage(message, emojiEvent);
    }

    public Message sendMessage(@NotNull Message message) {
        return sendMessage(message, null);
    }

    public Message sendMessage(@NotNull String rawMessage) {
        return sendMessage(rawMessage, null);
    }
}
