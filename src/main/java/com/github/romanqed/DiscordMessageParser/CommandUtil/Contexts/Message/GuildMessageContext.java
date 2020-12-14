package com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.Message;

import com.github.romanqed.DiscordMessageParser.ReactionUtil.EmojiEvent;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EventCollection;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.Base.GuildContext;
import com.github.romanqed.DiscordMessageParser.JDAUtil.JDAUtils;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GuildMessageContext extends GuildContext {
    protected final TextChannel channel;

    public GuildMessageContext(TextChannel channel, EventCollection eventCollection) {
        super(channel.getGuild(), eventCollection);
        this.channel = channel;
    }

    public TextChannel getChannel() {
        return channel;
    }

    public Message sendMessage(@NotNull Message message, @Nullable EmojiEvent emojiEvent) {
        return sendMessage(channel, message, emojiEvent);
    }

    public Message sendMessage(@NotNull String rawMessage, @Nullable EmojiEvent emojiEvent) {
        return sendMessage(channel, rawMessage, emojiEvent);
    }

    public Message sendMessage(@NotNull Message message) {
        return sendMessage(message, null);
    }

    public Message sendMessage(@NotNull String rawMessage) {
        return sendMessage(rawMessage, null);
    }

    public boolean clearThisTextChannel() {
        return JDAUtils.clearTextChannel(channel);
    }
}
