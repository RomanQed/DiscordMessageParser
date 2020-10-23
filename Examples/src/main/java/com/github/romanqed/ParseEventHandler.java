package com.github.romanqed;

import com.github.romanqed.DiscordMessageParser.EventUtil.IParseEventHandler;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

public class ParseEventHandler implements IParseEventHandler {
    @Override
    public boolean onPrivateMessageParsing(PrivateMessageReceivedEvent event, StringBuilder prefix) {
        prefix.append("pls private ");
        return false;
    }

    @Override
    public void onPrivateEmptyCommand(PrivateMessageReceivedEvent event) {
        event.getChannel().sendMessage("Unknown private command!").queue();
    }

    @Override
    public boolean onGuildMessageParsing(GuildMessageReceivedEvent event, StringBuilder prefix) {
        prefix.append("pls guild ");
        return false;
    }

    @Override
    public void onGuildEmptyCommand(GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage("Unknown guild command!").queue();
    }

    @Override
    public void onGuildPermissionError(GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage("You don't have some permissions!").queue();
    }

    @Override
    public void onGuildRoleError(GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage("You don't have some roles!").queue();
    }
}
