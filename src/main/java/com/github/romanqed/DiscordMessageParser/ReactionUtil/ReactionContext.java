package com.github.romanqed.DiscordMessageParser.ReactionUtil;

import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;

public interface ReactionContext {
    MessageReaction getReaction();

    User getSender();

    long getGuildId();

    long countReactions();

    boolean isAdd();

    boolean isRemove();
}
