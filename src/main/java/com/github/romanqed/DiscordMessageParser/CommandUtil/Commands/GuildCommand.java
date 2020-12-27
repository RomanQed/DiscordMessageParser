package com.github.romanqed.DiscordMessageParser.CommandUtil.Commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GuildCommand extends GenericCommand {
    private final Set<Permission> permissions;
    private final Set<String> roles;

    public GuildCommand(String name, Collection<Permission> permissions, Collection<String> roles) {
        super(name);
        this.permissions = new HashSet<>();
        this.roles = new HashSet<>();
        if (permissions != null) {
            this.permissions.addAll(permissions);
        }
        if (roles != null) {
            this.roles.addAll(roles);
        }
    }

    public GuildCommand(String name, Collection<Permission> permissions) {
        this(name, permissions, null);
    }

    public GuildCommand(String name) {
        this(name, null, null);
    }

    public GuildCommand() {
        this(null, null, null);
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void addRoles(String... roles) {
        Collections.addAll(this.roles, roles);
    }

    public void addPermissions(Permission... permissions) {
        Collections.addAll(this.permissions, permissions);
    }

    public boolean canBeExecutedWithRoles(Collection<Role> roles) {
        if (this.roles.isEmpty()) {
            return true;
        }
        if (this.roles.size() > roles.size()) {
            return false;
        }
        for (Role role : roles) {
            if (!this.roles.contains(role.getName())) {
                return false;
            }
        }
        return true;
    }

    public boolean canBeExecutedWithPermissions(Collection<Permission> permissions) {
        if (this.permissions.isEmpty()) {
            return true;
        }
        if (this.permissions.size() > permissions.size()) {
            return false;
        }
        return permissions.containsAll(this.permissions);
    }

    public boolean canBeExecutedByMember(Member member) {
        return canBeExecutedWithRoles(member.getRoles()) && member.hasPermission(permissions);
    }

    @Override
    public String toString() {
        return "[GuildCommandClass] " + name + " [Permissions] " + permissions.toString() + " [Roles] " + roles.toString();
    }
}
