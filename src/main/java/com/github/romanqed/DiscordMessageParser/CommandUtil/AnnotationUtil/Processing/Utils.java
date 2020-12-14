package com.github.romanqed.DiscordMessageParser.CommandUtil.AnnotationUtil.Processing;

import com.github.romanqed.DiscordMessageParser.CommandUtil.AnnotationUtil.Annotations.Guild;
import com.github.romanqed.DiscordMessageParser.CommandUtil.AnnotationUtil.Annotations.Private;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.GenericCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.GuildCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.PrivateCommand;
import org.atteo.classindex.ClassIndex;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public class Utils {
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

    public static <T extends GenericCommand> Set<T> getAnnotatedCommands(Class<T> command, Class<? extends Annotation> annotation) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Iterable<Class<?>> classes = ClassIndex.getAnnotated(annotation);
        Set<T> commands = new HashSet<>();
        for (Class<?> clazz : classes) {
            commands.add(command.cast(clazz.getDeclaredConstructors()[0].newInstance()));
        }
        return commands;
    }
}
