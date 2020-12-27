package com.github.romanqed;

import com.github.romanqed.DiscordMessageParser.AnnotationUtil.Annotations.Commands.PrivateCommandClass;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.PrivateCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.Context;

@PrivateCommandClass
public class MyPrivateCommand extends PrivateCommand {
    @Override
    public void execute(Context context) {
        context.getJDAWrapper().sendMessage(toString());
    }
}
