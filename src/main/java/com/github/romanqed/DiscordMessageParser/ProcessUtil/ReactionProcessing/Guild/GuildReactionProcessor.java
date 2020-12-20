package com.github.romanqed.DiscordMessageParser.ProcessUtil.ReactionProcessing.Guild;

import com.github.romanqed.DiscordMessageParser.ProcessUtil.GuildService;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.ReactionProcessing.ReactionProcessor;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EmojiEvent;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EventCollection;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.ReactionContext;

import java.util.concurrent.ExecutorService;

public class GuildReactionProcessor extends ReactionProcessor {
    private final GuildService guildService;

    public GuildReactionProcessor(EventCollection events, ExecutorService executor) {
        super(events, executor);
        guildService = new GuildService(service);
    }

    public GuildReactionProcessor(EventCollection events) {
        this(events, null);
    }

    public GuildReactionProcessor() {
        this(null, null);
    }

    public void queueReaction(ReactionContext context) {
        guildService.addToQueue(context.getGuildId(), () -> {
            EmojiEvent event = processReaction(context.getReaction());
            if (event != null) {
                event.call(context);
            }
        });
    }

    public void dropGuildExecutor(long guildId, boolean safetyDrop) {
        guildService.dropGuildQueue(guildId, safetyDrop);
    }
}
