package com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.ServiceAnnotation;

import com.github.romanqed.DiscordMessageParser.CommandUtil.Command;
import org.atteo.classindex.ClassIndex;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class BotCommandAnnotationParser {
    public static List<Command> getCommandList() throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Command> result = new ArrayList<>();
        Iterable<Class<?>> iterable = ClassIndex.getAnnotated(BotCommand.class);
        for (Class<?> clazz : iterable) {
            result.add((Command) Class.forName(clazz.getCanonicalName()).getDeclaredConstructors()[0].newInstance());
        }
        return result;
    }
}
