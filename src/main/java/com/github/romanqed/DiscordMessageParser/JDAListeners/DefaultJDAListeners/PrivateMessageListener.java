package com.github.romanqed.DiscordMessageParser.JDAListeners.DefaultJDAListeners;

import com.github.romanqed.DiscordMessageParser.CommandUtil.CommandCollection;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.PrivateCommand;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.MessageParseHandler;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.Private.PrivateMessageProcessor;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;

public class PrivateMessageListener extends ListenerAdapter {
    private final PrivateMessageProcessor processor;

    public PrivateMessageListener(CommandCollection<PrivateCommand> commands, ExecutorService service, MessageParseHandler handler) {
        this.processor = new PrivateMessageProcessor(commands, service, handler);
    }

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        processor.queueMessage(event.getMessage());
    }

    @Override
    public void onPrivateMessageUpdate(@NotNull PrivateMessageUpdateEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        processor.queueMessage(event.getMessage());
    }
}
