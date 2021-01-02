package com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing;

import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.CommandParser;
import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.ProcessedCommand;
import com.github.romanqed.DiscordMessageParser.ContainerUtil.ContainerCollection;
import com.github.romanqed.DiscordMessageParser.JDAUtil.Wrappers.JDAWrapper;
import net.dv8tion.jda.api.entities.Message;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class MessageProcessor {
    protected final CommandParser parser;
    protected final ExecutorService service;
    protected final Map<Long, ContainerCollection> containers;
    protected final MessageParseHandler handler;

    public MessageProcessor(ExecutorService service, MessageParseHandler handler) {
        parser = new CommandParser();
        containers = new ConcurrentHashMap<>();
        this.service = Objects.requireNonNullElse(service, Executors.newCachedThreadPool());
        this.handler = Objects.requireNonNull(handler);
    }

    public MessageProcessor(MessageParseHandler handler) {
        this(null, handler);
    }

    public ProcessedCommand processMessage(Message message, JDAWrapper wrapper) {
        Set<String> prefixes = new HashSet<>();
        if (handler.onMessageParsing(wrapper, prefixes)) {
            return null;
        }
        parser.clearPrefixes();
        parser.addPrefixes(prefixes);
        return parser.parseCommand(message.getContentRaw());
    }
}
