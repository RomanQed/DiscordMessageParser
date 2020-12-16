package com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.Guild;

import com.github.romanqed.DiscordMessageParser.CommandUtil.AnnotationUtil.Processing.Utils;
import com.github.romanqed.DiscordMessageParser.CommandUtil.CommandCollection;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.GuildCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.ContextImpl;
import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.ProcessedCommand;
import com.github.romanqed.DiscordMessageParser.ContainerUtil.ContainerCollection;
import com.github.romanqed.DiscordMessageParser.JDAUtil.Wrappers.JDAWrapper;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.MessageParseHandler;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.MessageProcessor;
import com.github.romanqed.DiscordMessageParser.ThreadUtil.QueueExecutor;
import com.github.romanqed.DiscordMessageParser.ThreadUtil.ThreadUtils;
import net.dv8tion.jda.api.entities.Message;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

public class GuildProcessor extends MessageProcessor {
    private static final CommandCollection<GuildCommand> commands = Utils.getGuildCommandCollection();
    private final Map<Long, QueueExecutor> guildExecutors;

    public GuildProcessor(ExecutorService executor, MessageParseHandler handler) {
        super(executor, handler);
        guildExecutors = new ConcurrentHashMap<>();
    }

    public GuildProcessor(MessageParseHandler handler) {
        this(null, handler);
    }

    private void processMessage(Message message) {
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

    public void processGuildCommand(Message message) {
        long key = message.getGuild().getIdLong();
        QueueExecutor executor = guildExecutors.get(key);
        if (executor == null) {
            executor = new QueueExecutor();
            service.submit(executor);
            guildExecutors.put(key, executor);
        }
        executor.addToQueue(() -> processMessage(message));
        service.submit(() -> ThreadUtils.safetyCollectExecutors(guildExecutors.values(), ThreadUtils.DEFAULT_FREEZE_TIME));
    }
}
