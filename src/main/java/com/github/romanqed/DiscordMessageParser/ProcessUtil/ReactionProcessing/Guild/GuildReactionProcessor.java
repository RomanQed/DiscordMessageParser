package com.github.romanqed.DiscordMessageParser.ProcessUtil.ReactionProcessing.Guild;

import com.github.romanqed.DiscordMessageParser.ProcessUtil.GuildService;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.ReactionProcessing.ReactionProcessor;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EmojiEvent;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EventCollection;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;

import java.util.Objects;
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

    public void queueReaction(MessageReaction reaction, User user) {
        guildService.addToQueue(Objects.requireNonNull(reaction.getGuild()).getIdLong(), () -> {
            EmojiEvent event = processReaction(reaction);
            if (event != null) {
                event.call(user);
            }
        });
    }

    public void queueReactionRemove(long guildId, long messageId) {
        guildService.addToQueue(guildId, () -> processReactionRemove(messageId));
    }

    public void dropGuildExecutor(long guildId, boolean safetyDrop) {
        guildService.dropGuildQueue(guildId, safetyDrop);
    }
}
