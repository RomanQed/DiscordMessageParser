package com.github.romanqed.DiscordMessageParser.JDAListeners;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

public interface JDAListenerFabric {
    ListenerAdapter newGuildMessageListener();

    ListenerAdapter newPrivateMessageListener();

    ListenerAdapter newGuildReactionListener();

    ListenerAdapter newPrivateReactionListener();
}
