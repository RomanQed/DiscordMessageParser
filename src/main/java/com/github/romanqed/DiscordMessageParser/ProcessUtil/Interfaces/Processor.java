package com.github.romanqed.DiscordMessageParser.ProcessUtil.Interfaces;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;

public interface Processor {
    void processMessage(Message message);

    void processReaction(User author, MessageReaction reaction);
}
