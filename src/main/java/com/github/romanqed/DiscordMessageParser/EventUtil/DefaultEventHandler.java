package com.github.romanqed.DiscordMessageParser.EventUtil;

import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.Contexts.Message.GuildReceivedContext;
import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.Contexts.Message.PrivateReceivedContext;
import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.Utils;

public class DefaultEventHandler implements IParseEventHandler {
    @Override
    public boolean onPrivateMessageParsing(PrivateReceivedContext context, StringBuilder prefix) {
        prefix.append(Utils.DEFAULT_COMMAND_PREFIX);
        return false;
    }

    @Override
    public boolean onGuildMessageParsing(GuildReceivedContext context, StringBuilder prefix) {
        prefix.append(Utils.DEFAULT_COMMAND_PREFIX);
        return false;
    }
}
