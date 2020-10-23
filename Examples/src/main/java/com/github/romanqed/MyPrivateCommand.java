package com.github.romanqed;

import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.PrivateCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.PrivateCommandEvent;
import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.ServiceAnnotation.BotCommand;

@BotCommand
public class MyPrivateCommand extends PrivateCommand {
    public MyPrivateCommand(){
        super("com.github.romanqed.MyPrivateCommand");
    }

    @Override
    public void execute(PrivateCommandEvent event) {
        event.sendMessage("This is my private command!");
    }
}
