package com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.Private;

import com.github.romanqed.DiscordMessageParser.CommandUtil.CommandCollection;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.PrivateCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.ProcessedCommand;
import com.github.romanqed.DiscordMessageParser.ContainerUtil.ContainerCollection;
import com.github.romanqed.DiscordMessageParser.JDAUtil.Wrappers.JDAWrapper;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.MessageParseHandler;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.MessageProcessor;
import net.dv8tion.jda.api.entities.Message;

import java.util.Objects;
import java.util.concurrent.ExecutorService;

public class PrivateMessageProcessor extends MessageProcessor {
    private final CommandCollection<PrivateCommand> commands;

    public PrivateMessageProcessor(CommandCollection<PrivateCommand> commands, ExecutorService service, MessageParseHandler handler) {
        super(service, handler);
        this.commands = Objects.requireNonNullElse(commands, new CommandCollection<>());
        containers.put(0L, new ContainerCollection());
    }

    public PrivateMessageProcessor(CommandCollection<PrivateCommand> commands, MessageParseHandler handler) {
        this(commands, null, handler);
    }

    public PrivateMessageProcessor(MessageParseHandler handler) {
        this(null, null, handler);
    }

    public void processMessage(Message message) {
        JDAWrapper wrapper = new JDAWrapper(message);
        ProcessedCommand parsedCommand = processMessage(message, wrapper);
        if (parsedCommand == null || !parsedCommand.isSuccess()) {
            return;
        }
        PrivateCommand command = commands.findCommand(parsedCommand.getName());
        if (command == null) {
            handler.onUnknownCommand(wrapper);
            return;
        }
        command.execute(wrapper, parsedCommand.getRawArguments());
    }

    public void queueMessage(Message message) {
        service.submit(() -> {
            try {
                processMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
