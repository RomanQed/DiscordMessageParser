package com.github.romanqed;

import com.github.romanqed.DiscordMessageParser.AnnotationUtil.Annotations.Commands.GuildCommandClass;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.GuildCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.Context;
import net.dv8tion.jda.api.Permission;

@GuildCommandClass
public class MyGuildCommand extends GuildCommand {
    public MyGuildCommand() {
        addRoles("MyRole");
        addPermissions(Permission.MANAGE_PERMISSIONS);
    }

    @Override
    public void execute(Context context) {
        context.getJDAWrapper().sendMessage(toString());
    }
}
