package com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts;

import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.ArgumentParser;
import com.github.romanqed.DiscordMessageParser.JDAUtil.MessageUtils;
import com.github.romanqed.DiscordMessageParser.JDAUtil.Processing;
import com.github.romanqed.DiscordMessageParser.RegexUtil.ArgumentPattern;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class GenericContext {
    protected final ArgumentParser parser;
    protected final Message body;

    public GenericContext(Message body, String arguments) {
        this.body = Objects.requireNonNull(body);
        this.parser = new ArgumentParser(arguments);
    }

    public User getAuthor() {
        return body.getAuthor();
    }

    public JDA getJDA() {
        return body.getJDA();
    }

    public List<String> parseArguments(ArgumentPattern... patterns) {
        return parser.parseArguments(patterns);
    }

    public Message getMessage() {
        return body;
    }

    public MessageChannel getChannel() {
        return body.getChannel();
    }

    public Message sendMessage(Message message) {
        return MessageUtils.sendMessage(body.getChannel(), message);
    }

    public Message sendMessage(String message) {
        return MessageUtils.sendMessage(body.getChannel(), message);
    }

    public @Nullable User getUserById(long id) {
        try {
            return body.getJDA().retrieveUserById(id).complete();
        } catch (Exception e) {
            return null;
        }
    }

    public @Nullable User getUserById(String id) {
        try {
            return body.getJDA().retrieveUserById(id).complete();
        } catch (Exception e) {
            return null;
        }
    }

    public @Nullable User getUserByMention(String userMention) {
        try {
            return body.getJDA().retrieveUserById(Processing.parseUserMention(userMention)).complete();
        } catch (Exception e) {
            return null;
        }
    }
}
