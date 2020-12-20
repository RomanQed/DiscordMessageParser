package com.github.romanqed.DiscordMessageParser.JDAListeners.DefaultJDAListeners;

import com.github.romanqed.DiscordMessageParser.ProcessUtil.ReactionProcessing.Guild.GuildReactionProcessor;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.Constants;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EventCollection;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.ReactionContextImpl;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveAllEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.ExecutorService;

public class GuildReactionListener extends ListenerAdapter {
    private final GuildReactionProcessor processor;

    public GuildReactionListener(EventCollection events, ExecutorService service) {
        this.processor = new GuildReactionProcessor(events, service);
    }

    @Override
    public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event) {
        if (event.getUser().isBot()) {
            return;
        }
        processor.queueReaction(new ReactionContextImpl(event.getReaction(), event.getUser(), Constants.ADD_REACTION));
    }

    @Override
    public void onGuildMessageReactionRemove(@NotNull GuildMessageReactionRemoveEvent event) {
        User sender = Objects.requireNonNullElse(event.getUser(), event.retrieveUser().complete());
        if (sender.isBot()) {
            return;
        }
        processor.queueReaction(new ReactionContextImpl(event.getReaction(), sender, Constants.REMOVE_REACTION));
    }

    @Override
    public void onGuildMessageReactionRemoveAll(@NotNull GuildMessageReactionRemoveAllEvent event) {
        processor.queueReactionRemove(event.getMessageIdLong());
    }

    @Override
    public void onGuildMessageDelete(@NotNull GuildMessageDeleteEvent event) {
        processor.queueReactionRemove(event.getMessageIdLong());
    }

    @Override
    public void onGuildLeave(@NotNull GuildLeaveEvent event) {
        processor.dropGuildExecutor(event.getGuild().getIdLong(), false);
    }
}
