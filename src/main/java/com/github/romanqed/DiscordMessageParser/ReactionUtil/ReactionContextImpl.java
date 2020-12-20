package com.github.romanqed.DiscordMessageParser.ReactionUtil;

import com.github.romanqed.DiscordMessageParser.JDAUtil.Utils.Processing;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;

import java.util.Objects;

public class ReactionContextImpl implements ReactionContext {
    private final MessageReaction reaction;
    private final User sender;
    private final int type;

    public ReactionContextImpl(MessageReaction reaction, User sender, int type) {
        this.reaction = Objects.requireNonNull(reaction);
        this.sender = Objects.requireNonNull(sender);
        this.type = Math.max(type, 0);
    }

    @Override
    public MessageReaction getReaction() {
        return reaction;
    }

    @Override
    public User getSender() {
        return sender;
    }

    @Override
    public long getGuildId() {
        return Objects.requireNonNull(reaction.getGuild()).getIdLong();
    }

    @Override
    public long countReactions() {
        return Processing.countReactions(reaction);
    }

    @Override
    public boolean isAdd() {
        return type == Constants.ADD_REACTION;
    }

    @Override
    public boolean isRemove() {
        return type == Constants.REMOVE_REACTION;
    }

    @Override
    public String toString() {
        return "[Reaction] " + reaction.toString() + " [Sender] " + sender.toString();
    }
}
