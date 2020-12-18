package com.github.romanqed.DiscordMessageParser.JDAListeners.DefaultJDAListeners;

import com.github.romanqed.DiscordMessageParser.CommandUtil.CommandCollection;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.GuildCommand;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.Guild.GuildMessageProcessor;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.MessageParseHandler;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;

public class GuildMessageListener extends ListenerAdapter {
    private final GuildMessageProcessor processor;

    public GuildMessageListener(CommandCollection<GuildCommand> commands, ExecutorService service, MessageParseHandler handler) {
        processor = new GuildMessageProcessor(commands, service, handler);
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        processor.queueMessage(event.getMessage());
    }

    @Override
    public void onGuildMessageUpdate(@NotNull GuildMessageUpdateEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        processor.queueMessage(event.getMessage());
    }

    @Override
    public void onGuildLeave(@NotNull GuildLeaveEvent event) {
        processor.dropGuildExecutor(event.getGuild().getIdLong(), false);
    }
}
