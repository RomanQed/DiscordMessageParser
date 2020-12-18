package com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.Guild;

import com.github.romanqed.DiscordMessageParser.CommandUtil.CommandCollection;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.GuildCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.ContextImpl;
import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.ProcessedCommand;
import com.github.romanqed.DiscordMessageParser.ContainerUtil.ContainerCollection;
import com.github.romanqed.DiscordMessageParser.JDAUtil.Wrappers.JDAWrapper;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.GuildService;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.MessageParseHandler;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.MessageProcessor;
import net.dv8tion.jda.api.entities.Message;

import java.util.Objects;
import java.util.concurrent.ExecutorService;

public class GuildMessageProcessor extends MessageProcessor {
    private final CommandCollection<GuildCommand> commands;
    private final GuildService guildService;

    public GuildMessageProcessor(CommandCollection<GuildCommand> commands, ExecutorService executor, MessageParseHandler handler) {
        super(executor, handler);
        this.commands = Objects.requireNonNullElse(commands, new CommandCollection<>());
        guildService = new GuildService(service);
    }

    public GuildMessageProcessor(CommandCollection<GuildCommand> commands, MessageParseHandler handler) {
        this(null, null, handler);
    }

    public GuildMessageProcessor(MessageParseHandler handler) {
        this(null, null, handler);
    }

    public void processMessage(Message message) {
        JDAWrapper wrapper = new JDAWrapper(message);
        ProcessedCommand parsedCommand = processMessage(message, wrapper);
        if (parsedCommand == null || !parsedCommand.isSuccess()) {
            return;
        }
        GuildCommand command = commands.findCommand(parsedCommand.getName());
        if (command == null) {
            handler.onUnknownCommand(wrapper);
            return;
        }
        if (!command.canBeExecutedByMember(Objects.requireNonNull(message.getMember()))) {
            handler.onAccessError(wrapper);
            return;
        }
        ContainerCollection collection = containers.get(message.getGuild().getIdLong());
        if (collection == null) {
            collection = new ContainerCollection();
            containers.put(message.getGuild().getIdLong(), collection);
        }
        command.execute(new ContextImpl(parsedCommand.getRawArguments(), wrapper, collection));
    }

    public void queueMessage(Message message) {
        long key = message.getGuild().getIdLong();
        guildService.addToQueue(key, () -> processMessage(message));
    }

    public void dropGuildExecutor(long guildId, boolean safetyDrop) {
        guildService.dropGuildQueue(guildId, safetyDrop);
    }
}
