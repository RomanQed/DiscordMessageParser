package com.github.romanqed.AsyncBot;

import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.ServiceAnnotation.BotCommandAnnotationParser;
import com.github.romanqed.DiscordMessageParser.CommandUtil.CommandList;
import com.github.romanqed.DiscordMessageParser.DiscordMessageParser;
import com.github.romanqed.DiscordMessageParser.EventUtil.IParseEventHandler;
import com.github.romanqed.DiscordMessageParser.ThreadUtil.QueueExecutor;
import com.github.romanqed.DiscordMessageParser.ThreadUtil.Utils;
import com.github.romanqed.ParseEventHandler;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.react.PrivateMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;

public class AsyncListenerAdapter extends ListenerAdapter {
    private final DiscordMessageParser parser;
    private final QueueExecutor collector;
    private final ConcurrentHashMap<String, QueueExecutor> messageQueue;
    private final ConcurrentHashMap<String, QueueExecutor> reactionQueue;

    public AsyncListenerAdapter() throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        CommandList commandList = new CommandList(BotCommandAnnotationParser.getCommandList());
        IParseEventHandler eventHandler = new ParseEventHandler();
        parser = new DiscordMessageParser(commandList, eventHandler);
        collector = new QueueExecutor();
        new Thread(collector).start();
        messageQueue = new ConcurrentHashMap<>();
        reactionQueue = new ConcurrentHashMap<>();
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (!event.getGuild().getSelfMember().hasPermission(Permission.ADMINISTRATOR)) {
            return;
        }
        if (!messageQueue.containsKey(event.getGuild().getId())) {
            QueueExecutor executor = new QueueExecutor();
            new Thread(executor).start();
            messageQueue.put(event.getGuild().getId(), executor);
        }
        QueueExecutor guildExecutor = messageQueue.get(event.getGuild().getId());
        guildExecutor.addToQueue(() -> {
            parser.processGuildMessage(event);
        });
        collector.addToQueue(() -> {
            Utils.safetyCollectExecutors(messageQueue.values(), Utils.DEFAULT_FREEZE_TIME);
        });
    }

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        Thread processMessage = new Thread(() -> {
            parser.processPrivateMessage(event);
        });
        processMessage.start();
    }

    @Override
    public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event) {
        if (!reactionQueue.containsKey(event.getGuild().getId())) {
            QueueExecutor executor = new QueueExecutor();
            new Thread(executor).start();
            reactionQueue.put(event.getGuild().getId(), executor);
        }
        QueueExecutor guildExecutor = reactionQueue.get(event.getGuild().getId());
        guildExecutor.addToQueue(() -> {
            parser.processGuildReaction(event);
        });
        collector.addToQueue(() -> {
            Utils.safetyCollectExecutors(reactionQueue.values(), Utils.DEFAULT_FREEZE_TIME);
        });
    }

    @Override
    public void onPrivateMessageReactionAdd(@NotNull PrivateMessageReactionAddEvent event) {
        Thread processReaction = new Thread(() -> {
            parser.processPrivateReaction(event);
        });
        processReaction.start();
    }
}

