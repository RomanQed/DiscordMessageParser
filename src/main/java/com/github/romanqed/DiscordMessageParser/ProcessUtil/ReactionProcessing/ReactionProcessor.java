package com.github.romanqed.DiscordMessageParser.ProcessUtil.ReactionProcessing;

import com.github.romanqed.DiscordMessageParser.ReactionUtil.EmojiEvent;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EventCollection;
import net.dv8tion.jda.api.entities.MessageReaction;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class ReactionProcessor {
    protected final EventCollection events;
    protected final ExecutorService service;

    protected ReactionProcessor(ExecutorService service, EventCollection events) {
        this.service = Objects.requireNonNullElse(service, Executors.newCachedThreadPool());
        this.events = Objects.requireNonNullElse(events, new EventCollection());
    }

    public EmojiEvent processReaction(MessageReaction reaction) {
        MessageReaction.ReactionEmote emote = reaction.getReactionEmote();
        if (!emote.isEmoji()) {
            return null;
        }
        return events.findByReaction(reaction);
    }

    public void processReactionRemove(long messageId) {
        events.remove(messageId);
    }
}
