package com.github.romanqed;

import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.BotRuntimeVariables.VariableList;
import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.GuildCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.GuildCommandEvent;
import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.ServiceAnnotation.BotCommand;
import net.dv8tion.jda.api.Permission;

import java.util.List;

@BotCommand
public class MyGuildCommand extends GuildCommand {
    public MyGuildCommand() {
        super("com.github.romanqed.MyGuildCommand", List.of("TestRole"), Permission.MESSAGE_MANAGE);
    }

    @Override
    public void execute(GuildCommandEvent event, VariableList variableList) {
        event.sendMessage("This is my guild command!");
    }
}
