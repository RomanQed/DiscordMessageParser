package com.github.romanqed.DiscordMessageParser;

import com.github.romanqed.DiscordMessageParser.ButtonUtil.ButtonEventList;
import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.BotRuntimeVariables.VariableList;
import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.GuildCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.GuildCommandEvent;
import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.PrivateCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.PrivateCommandEvent;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Command;
import com.github.romanqed.DiscordMessageParser.CommandUtil.CommandList;
import com.github.romanqed.DiscordMessageParser.CommandUtil.CommandType;
import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.ParseResult;
import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.ResultEnum;
import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.Utils;
import com.github.romanqed.DiscordMessageParser.EventUtil.DefaultEventHandler;
import com.github.romanqed.DiscordMessageParser.EventUtil.IParseEventHandler;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.react.PrivateMessageReactionAddEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class DiscordMessageParser {
    private final CommandList commandList;
    private final IParseEventHandler eventHandler;
    private final ConcurrentHashMap<String, VariableList> environmentList;
    private final ButtonEventList buttonEventList;

    public DiscordMessageParser(@NotNull CommandList commandList, @Nullable IParseEventHandler eventHandler) {
        this.commandList = Objects.requireNonNullElse(commandList, new CommandList());
        this.eventHandler = Objects.requireNonNullElse(eventHandler, new DefaultEventHandler());
        environmentList = new ConcurrentHashMap<>();
        buttonEventList = new ButtonEventList();
    }

    public DiscordMessageParser(@NotNull CommandList commandList) {
        this(commandList, null);
    }

    public void processGuildMessage(@NotNull GuildMessageReceivedEvent event) {
        if (event == null || event.getAuthor().isBot()) {
            return;
        }
        StringBuilder prefix = new StringBuilder();
        if (eventHandler.onGuildMessageParsing(event, prefix)) {
            return;
        }
        ParseResult result = Utils.parseCommand(event.getMessage().getContentRaw(), prefix.toString());
        if (result.parseResult == ResultEnum.ERROR) {
            return;
        }
        Command uncheckedCommand = commandList.getCommand(result.commandBody, CommandType.GuildCommand);
        if (uncheckedCommand.isEmpty()) {
            eventHandler.onGuildEmptyCommand(event);
            return;
        }
        GuildCommand command = (GuildCommand) uncheckedCommand;
        if (event.getMember() == null) {
            return;
        }
        if (!Utils.validateCommandRoles(command.getRoles(), event.getMember().getRoles())) {
            eventHandler.onGuildRoleError(event);
            return;
        }
        if (!event.getMember().hasPermission(command.getPermissions())) {
            eventHandler.onGuildPermissionError(event);
            return;
        }
        String guildId = event.getGuild().getId();
        if (!environmentList.containsKey(guildId)) {
            environmentList.put(guildId, new VariableList());
        }
        VariableList variableList = environmentList.get(guildId);
        command.execute(new GuildCommandEvent(event, result.rawArguments, buttonEventList), variableList);
    }

    public void processPrivateMessage(@NotNull PrivateMessageReceivedEvent event) {
        if (event == null || event.getAuthor().isBot()) {
            return;
        }
        StringBuilder prefix = new StringBuilder();
        if (eventHandler.onPrivateMessageParsing(event, prefix)) {
            return;
        }
        ParseResult result = Utils.parseCommand(event.getMessage().getContentRaw(), prefix.toString());
        if (result.parseResult == ResultEnum.ERROR) {
            return;
        }
        Command uncheckedCommand = commandList.getCommand(result.commandBody, CommandType.PrivateCommand);
        if (uncheckedCommand.isEmpty()) {
            eventHandler.onPrivateEmptyCommand(event);
            return;
        }
        PrivateCommand command = (PrivateCommand) uncheckedCommand;
        command.execute(new PrivateCommandEvent(event, result.rawArguments, buttonEventList));
    }

    public void processGuildReaction(@NotNull GuildMessageReactionAddEvent event) {
        if (event == null || event.getUser().isBot()) {
            return;
        }
        MessageReaction.ReactionEmote reactionEmote = event.getReactionEmote();
        if (!reactionEmote.isEmoji()) {
            return;
        }
        String id = event.getChannel().getId() + event.getMessageId() + reactionEmote.getEmoji();
        buttonEventList.execute(id, event.getUser());
    }

    public void processPrivateReaction(@NotNull PrivateMessageReactionAddEvent event) {
        if (event == null || event.getUser() == null || event.getUser().isBot()) {
            return;
        }
        MessageReaction.ReactionEmote reactionEmote = event.getReactionEmote();
        if (!reactionEmote.isEmoji()) {
            return;
        }
        String id = event.getChannel().getId() + event.getMessageId() + reactionEmote.getEmoji();
        buttonEventList.execute(id, event.getUser());
    }

    public void processGuildMessageDelete(@NotNull GuildMessageDeleteEvent event) {
        if (event == null) {
            return;
        }
        buttonEventList.remove(event.getMessageId());
    }

    public void processPrivateMessageDelete(@NotNull PrivateMessageDeleteEvent event) {
        if (event == null) {
            return;
        }
        buttonEventList.remove(event.getMessageId());
    }
}
