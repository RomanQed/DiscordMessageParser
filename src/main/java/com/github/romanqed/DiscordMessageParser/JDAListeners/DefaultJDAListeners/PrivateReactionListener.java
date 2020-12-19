package com.github.romanqed.DiscordMessageParser.JDAListeners.DefaultJDAListeners;

import com.github.romanqed.DiscordMessageParser.ProcessUtil.ReactionProcessing.Private.PrivateReactionProcessor;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EventCollection;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.priv.react.PrivateMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.priv.react.PrivateMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;

public class PrivateReactionListener extends ListenerAdapter {
    private final PrivateReactionProcessor processor;

    public PrivateReactionListener(EventCollection events, ExecutorService service) {
        processor = new PrivateReactionProcessor(events, service);
    }

    @Override
    public void onPrivateMessageReactionAdd(@NotNull PrivateMessageReactionAddEvent event) {
        if (event.getUser().isBot()) {
            return;
        }
        processor.queueReaction(event.getReaction(), event.getUser());
    }

    @Override
    public void onPrivateMessageReactionRemove(@NotNull PrivateMessageReactionRemoveEvent event) {
        if (event.getUser().isBot()) {
            return;
        }
        processor.queueReaction(event.getReaction(), event.getUser());
    }

    @Override
    public void onPrivateMessageDelete(@NotNull PrivateMessageDeleteEvent event) {
        processor.queueReactionRemove(event.getMessageIdLong());
    }
}
