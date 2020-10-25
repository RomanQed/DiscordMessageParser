package com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand;

import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.BotRuntimeVariables.VariableList;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Command;
import com.github.romanqed.DiscordMessageParser.CommandUtil.CommandType;
import net.dv8tion.jda.api.Permission;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GuildCommand extends Command {
    private final Set<Permission> permissions;
    private final Set<String> roles;

    public GuildCommand(@NotNull String name, @Nullable List<String> roles, @Nullable Permission... permissions) {
        super(name, CommandType.GuildCommand);
        if (roles == null) {
            this.roles = new HashSet<>();
        } else {
            this.roles = new HashSet<>(roles);
        }
        if (permissions == null) {
            this.permissions = new HashSet<>();
        } else {
            this.permissions = new HashSet<>(Arrays.asList(permissions));
        }
    }

    public GuildCommand(@NotNull String name, @Nullable Permission... permissions) {
        this(name, null, permissions);
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void execute(GuildCommandEvent event, VariableList variableList) {
    }
}
