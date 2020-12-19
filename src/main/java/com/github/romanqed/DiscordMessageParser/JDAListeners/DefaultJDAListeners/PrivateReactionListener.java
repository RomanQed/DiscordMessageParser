package com.github.romanqed.DiscordMessageParser.JDAListeners.DefaultJDAListeners;

import com.github.romanqed.DiscordMessageParser.JDAUtil.Utils.Processing;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.ReactionProcessing.Private.PrivateReactionProcessor;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EventCollection;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;
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
        User sender = event.getUser();
        if (sender == null || sender.isBot()) {
            return;
        }
        processor.queueReaction(event.getReaction(), sender);
    }

    @Override
    public void onPrivateMessageReactionRemove(@NotNull PrivateMessageReactionRemoveEvent event) {
        User sender = event.getUser();
        if (sender == null || sender.isBot()) {
            return;
        }
        MessageReaction reaction = event.getReaction();
        if (Processing.countReactions(reaction) == 0) {
            processor.queueReactionRemove(reaction);
        } else {
            processor.queueReaction(reaction, sender);
        }
    }

    @Override
    public void onPrivateMessageDelete(@NotNull PrivateMessageDeleteEvent event) {
        processor.queueReactionRemove(event.getMessageIdLong());
    }
}
