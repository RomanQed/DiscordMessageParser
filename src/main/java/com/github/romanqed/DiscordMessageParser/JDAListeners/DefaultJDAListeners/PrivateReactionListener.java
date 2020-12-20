package com.github.romanqed.DiscordMessageParser.JDAListeners.DefaultJDAListeners;

import com.github.romanqed.DiscordMessageParser.ProcessUtil.ReactionProcessing.Private.PrivateReactionProcessor;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.Constants;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EventCollection;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.ReactionContextImpl;
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
        processor.queueReaction(new ReactionContextImpl(event.getReaction(), sender, Constants.ADD_REACTION));
    }

    @Override
    public void onPrivateMessageReactionRemove(@NotNull PrivateMessageReactionRemoveEvent event) {
        User sender = event.getUser();
        if (sender == null || sender.isBot()) {
            return;
        }
        processor.queueReaction(new ReactionContextImpl(event.getReaction(), sender, Constants.REMOVE_REACTION));
    }

    @Override
    public void onPrivateMessageDelete(@NotNull PrivateMessageDeleteEvent event) {
        processor.queueReactionRemove(event.getMessageIdLong());
    }
}
