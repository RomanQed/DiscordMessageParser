package com.github.romanqed;

import com.github.romanqed.DiscordMessageParser.AnnotationUtil.Annotations.Commands.Private;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.PrivateCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.Context;

@Private
public class MyPrivateCommand extends PrivateCommand {
    @Override
    public void execute(Context context) {
        context.getJDAWrapper().sendMessage(toString());
    }
}
