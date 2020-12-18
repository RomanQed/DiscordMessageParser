package com.github.romanqed.DiscordMessageParser.ProcessUtil.ReactionProcessing.Guild;

import com.github.romanqed.DiscordMessageParser.ProcessUtil.GuildService;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.ReactionProcessing.ReactionProcessor;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EmojiEvent;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.LinkedEmojiEvent;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;

import java.util.Objects;
import java.util.concurrent.ExecutorService;

public class GuildReactionProcessor extends ReactionProcessor {
    private final GuildService guildService;

    public GuildReactionProcessor(ExecutorService executor) {
        super(executor, LinkedEmojiEvent.COLLECTION);
        guildService = new GuildService(service);
    }

    public GuildReactionProcessor() {
        this(null);
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
}
