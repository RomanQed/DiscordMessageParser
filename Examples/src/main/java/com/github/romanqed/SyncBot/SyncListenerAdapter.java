package com.github.romanqed.SyncBot;

import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.ServiceAnnotation.BotCommandAnnotationParser;
import com.github.romanqed.DiscordMessageParser.CommandUtil.CommandList;
import com.github.romanqed.DiscordMessageParser.DiscordMessageParser;
import com.github.romanqed.DiscordMessageParser.EventUtil.IParseEventHandler;
import com.github.romanqed.ParseEventHandler;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.react.PrivateMessageReactionAddEvent;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

public class SyncListenerAdapter extends net.dv8tion.jda.api.hooks.ListenerAdapter {
    private final DiscordMessageParser parser;

    public SyncListenerAdapter() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        CommandList commandList = new CommandList(BotCommandAnnotationParser.getCommandList());
        IParseEventHandler eventHandler = new ParseEventHandler();
        parser = new DiscordMessageParser(commandList, eventHandler);
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (!event.getGuild().getSelfMember().hasPermission(Permission.ADMINISTRATOR)) {
            return;
        }
        parser.processGuildMessage(event);
    }

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        parser.processPrivateMessage(event);
    }

    @Override
    public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event) {
        parser.processGuildReaction(event);
    }

    @Override
    public void onPrivateMessageReactionAdd(@NotNull PrivateMessageReactionAddEvent event) {
        parser.processPrivateReaction(event);
    }
}

