package com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand;

import com.github.romanqed.DiscordMessageParser.CommandUtil.Command;
import com.github.romanqed.DiscordMessageParser.CommandUtil.CommandType;
import org.jetbrains.annotations.NotNull;

public class PrivateCommand extends Command {
    public PrivateCommand(@NotNull String name) {
        super(name, CommandType.PrivateCommand);
    }

    public void execute(PrivateCommandEvent event) {
    }
}
