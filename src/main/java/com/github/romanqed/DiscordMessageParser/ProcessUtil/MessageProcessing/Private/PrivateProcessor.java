package com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.Private;

import com.github.romanqed.DiscordMessageParser.CommandUtil.AnnotationUtil.Processing.Utils;
import com.github.romanqed.DiscordMessageParser.CommandUtil.CommandCollection;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.PrivateCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.ContextImpl;
import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.ProcessedCommand;
import com.github.romanqed.DiscordMessageParser.ContainerUtil.ContainerCollection;
import com.github.romanqed.DiscordMessageParser.JDAUtil.Wrappers.JDAWrapper;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.MessageParseHandler;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.MessageProcessor;
import net.dv8tion.jda.api.entities.Message;

import java.util.concurrent.ExecutorService;

public class PrivateProcessor extends MessageProcessor {
    private static final CommandCollection<PrivateCommand> commands = Utils.getPrivateCommandCollection();

    public PrivateProcessor(ExecutorService service, MessageParseHandler handler) {
        super(service, handler);
        containers.put(0L, new ContainerCollection());
    }

    public PrivateProcessor(MessageParseHandler handler) {
        this(null, handler);
    }

    private void processMessage(Message message) {
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
        command.execute(new ContextImpl(parsedCommand.getRawArguments(), wrapper, containers.get(0L)));
    }

    public void processPrivateCommand(Message message) {
        service.submit(() -> processMessage(message));
    }
}
