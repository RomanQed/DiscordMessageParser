package com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.Contexts.Message;

import com.github.romanqed.DiscordMessageParser.ButtonUtil.ButtonEvent;
import com.github.romanqed.DiscordMessageParser.ButtonUtil.ButtonEventList;
import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.Contexts.Base.GuildContext;
import com.github.romanqed.DiscordMessageParser.JDAUtil.JDAUtils;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GuildMessageContext extends GuildContext {
    private final TextChannel channel;

    public GuildMessageContext(TextChannel channel, ButtonEventList buttonEventList) {
        super(channel.getGuild(), buttonEventList);
        this.channel = channel;
    }

    public Message sendMessage(@NotNull Message message, @Nullable ButtonEvent buttonEvent) {
        return sendMessage(channel, message, buttonEvent);
    }

    public Message sendMessage(@NotNull String rawMessage, @Nullable ButtonEvent buttonEvent) {
        return sendMessage(channel, rawMessage, buttonEvent);
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
