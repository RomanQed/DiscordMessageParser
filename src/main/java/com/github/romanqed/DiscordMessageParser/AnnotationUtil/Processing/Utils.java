package com.github.romanqed.DiscordMessageParser.AnnotationUtil.Processing;

import com.github.romanqed.DiscordMessageParser.AnnotationUtil.Annotations.Commands.Guild;
import com.github.romanqed.DiscordMessageParser.AnnotationUtil.Annotations.Commands.Private;
import com.github.romanqed.DiscordMessageParser.AnnotationUtil.Annotations.MessageProcessing.GuildHandler;
import com.github.romanqed.DiscordMessageParser.AnnotationUtil.Annotations.MessageProcessing.PrivateHandler;
import com.github.romanqed.DiscordMessageParser.CommandUtil.CommandCollection;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.GenericCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.GuildCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.PrivateCommand;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.MessageParseHandler;
import org.atteo.classindex.ClassIndex;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public class Utils {
    public static <T extends GenericCommand> Set<T> getAnnotatedCommands(Class<T> command, Class<? extends Annotation> annotation) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Iterable<Class<?>> classes = ClassIndex.getAnnotated(annotation);
        Set<T> commands = new HashSet<>();
        for (Class<?> clazz : classes) {
            commands.add(command.cast(clazz.getDeclaredConstructors()[0].newInstance()));
        }
        return commands;
    }

    public static Set<PrivateCommand> getPrivateCommands() {
        try {
            return getAnnotatedCommands(PrivateCommand.class, Private.class);
        } catch (Exception e) {
            return new HashSet<>();
        }
    }

    public static Set<GuildCommand> getGuildCommands() {
        try {
            return getAnnotatedCommands(GuildCommand.class, Guild.class);
        } catch (Exception e) {
            return new HashSet<>();
        }
    }

    public static CommandCollection<GuildCommand> getGuildCommandCollection() {
        return new CommandCollection<>(getGuildCommands());
    }

    public static CommandCollection<PrivateCommand> getPrivateCommandCollection() {
        return new CommandCollection<>(getPrivateCommands());
    }

    public static <T extends MessageParseHandler> T getAnnotatedHandler(Class<T> handler, Class<? extends Annotation> annotation) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Iterable<Class<?>> classes = ClassIndex.getAnnotated(annotation);
        return handler.cast(classes.iterator().next().getDeclaredConstructors()[0].newInstance());
    }

    public static MessageParseHandler getGuildHandler() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        return getAnnotatedHandler(MessageParseHandler.class, GuildHandler.class);
    }

    public static MessageParseHandler getPrivateHandler() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        return getAnnotatedHandler(MessageParseHandler.class, PrivateHandler.class);
    }
}
