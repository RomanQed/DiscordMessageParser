package com.github.romanqed.DiscordMessageParser.EventUtil;

import Legacy.Message.GuildReceivedContext;
import Legacy.Message.PrivateReceivedContext;

public interface IParseEventHandler {
    default boolean onPrivateMessageParsing(PrivateReceivedContext context, StringBuilder prefix) {
        return false;
    }

    default void onPrivateEmptyCommand(PrivateReceivedContext context) {
    }

    default boolean onGuildMessageParsing(GuildReceivedContext context, StringBuilder prefix) {
        return false;
    }

    default void onGuildEmptyCommand(GuildReceivedContext context) {
    }

    default void onGuildPermissionError(GuildReceivedContext context) {
    }

    default void onGuildRoleError(GuildReceivedContext context) {
    }
}
