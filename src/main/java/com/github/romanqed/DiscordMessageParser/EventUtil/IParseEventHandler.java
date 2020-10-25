package com.github.romanqed.DiscordMessageParser.EventUtil;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

public interface IParseEventHandler {
    default boolean onPrivateMessageParsing(PrivateMessageReceivedEvent event, StringBuilder prefix) {
        return false;
    }

    default void onPrivateEmptyCommand(PrivateMessageReceivedEvent event) {
    }

    default boolean onGuildMessageParsing(GuildMessageReceivedEvent event, StringBuilder prefix) {
        return false;
    }

    default void onGuildEmptyCommand(GuildMessageReceivedEvent event) {
    }

    default void onGuildPermissionError(GuildMessageReceivedEvent event) {
    }

    default void onGuildRoleError(GuildMessageReceivedEvent event) {
    }
}
