package com.github.romanqed.DiscordMessageParser.EventUtil;

import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.Utils;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

public class DefaultEventHandler implements IParseEventHandler {
    @Override
    public boolean onPrivateMessageParsing(PrivateMessageReceivedEvent event, StringBuilder prefix) {
        prefix.append(Utils.DEFAULT_COMMAND_PREFIX);
        return false;
    }

    @Override
    public boolean onGuildMessageParsing(GuildMessageReceivedEvent event, StringBuilder prefix) {
        prefix.append(Utils.DEFAULT_COMMAND_PREFIX);
        return false;
    }
}
