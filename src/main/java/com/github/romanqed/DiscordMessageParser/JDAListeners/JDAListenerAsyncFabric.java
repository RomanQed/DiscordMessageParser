package com.github.romanqed.DiscordMessageParser.JDAListeners;

import com.github.romanqed.DiscordMessageParser.AnnotationUtil.Processing.Utils;
import com.github.romanqed.DiscordMessageParser.CommandUtil.CommandCollection;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.GuildCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.PrivateCommand;
import com.github.romanqed.DiscordMessageParser.JDAListeners.DefaultJDAListeners.GuildMessageListener;
import com.github.romanqed.DiscordMessageParser.JDAListeners.DefaultJDAListeners.GuildReactionListener;
import com.github.romanqed.DiscordMessageParser.JDAListeners.DefaultJDAListeners.PrivateMessageListener;
import com.github.romanqed.DiscordMessageParser.JDAListeners.DefaultJDAListeners.PrivateReactionListener;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.MessageParseHandler;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EventCollection;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.LinkedEmojiEvent;
import com.github.romanqed.DiscordMessageParser.Utils.Checks;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Executors;

public class JDAListenerAsyncFabric implements JDAListenerFabric {
    private final CommandCollection<GuildCommand> guildCommands;
    private final CommandCollection<PrivateCommand> privateCommands;
    private final MessageParseHandler guildHandler;
    private final MessageParseHandler privateHandler;
    private final EventCollection emojiEvents;

    public JDAListenerAsyncFabric() {
        guildCommands = Utils.getGuildCommandCollection();
        privateCommands = Utils.getPrivateCommandCollection();
        guildHandler = Checks.requireNonExcept(Utils::getGuildHandler, new MessageParseHandler() {
        });
        privateHandler = Checks.requireNonExcept(Utils::getPrivateHandler, new MessageParseHandler() {
        });
        emojiEvents = LinkedEmojiEvent.COLLECTION;
    }

    @Override
    public ListenerAdapter newGuildMessageListener() {
        return new GuildMessageListener(guildCommands, Executors.newCachedThreadPool(), guildHandler);
    }

    @Override
    public ListenerAdapter newPrivateMessageListener() {
        return new PrivateMessageListener(privateCommands, Executors.newCachedThreadPool(), privateHandler);
    }

    @Override
    public ListenerAdapter newGuildReactionListener() {
        return new GuildReactionListener(emojiEvents, Executors.newCachedThreadPool());
    }

    @Override
    public ListenerAdapter newPrivateReactionListener() {
        return new PrivateReactionListener(emojiEvents, Executors.newCachedThreadPool());
    }
}
