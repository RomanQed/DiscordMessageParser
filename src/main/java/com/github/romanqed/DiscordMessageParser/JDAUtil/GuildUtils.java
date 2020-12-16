package com.github.romanqed.DiscordMessageParser.JDAUtil;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GuildUtils {
    public static boolean denyMemberToDo(@NotNull GuildChannel channel, @NotNull IPermissionHolder holder, @NotNull Permission... permissions) {
        try {
            return denyMemberToDo(List.of(channel), holder, permissions);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean allowMemberToDo(@NotNull GuildChannel channel, @NotNull IPermissionHolder holder, @NotNull Permission... permissions) {
        try {
            return allowMemberToDo(List.of(channel), holder, permissions);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean clearMemberPermission(@NotNull GuildChannel channel, @NotNull IPermissionHolder holder, @NotNull Permission... permissions) {
        try {
            return clearMemberPermission(List.of(channel), holder, permissions);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean resetMemberPermission(@NotNull GuildChannel channel, @NotNull IPermissionHolder holder) {
        try {
            return resetMemberPermission(List.of(channel), holder);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean denyMemberToDo(@NotNull List<GuildChannel> channels, @NotNull IPermissionHolder holder, @NotNull Permission... permissions) {
        PermissionOverride permissionOverride;
        try {
            for (GuildChannel channel : channels) {
                permissionOverride = channel.getPermissionOverride(holder);
                if (permissionOverride != null) {
                    permissionOverride.getManager().deny(permissions).queue();
                } else {
                    channel.createPermissionOverride(holder).deny(permissions).queue();
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean allowMemberToDo(@NotNull List<GuildChannel> channels, @NotNull IPermissionHolder holder, @NotNull Permission... permissions) {
        PermissionOverride permissionOverride;
        try {
            for (GuildChannel channel : channels) {
                permissionOverride = channel.getPermissionOverride(holder);
                if (permissionOverride != null) {
                    permissionOverride.getManager().setAllow(permissions).queue();
                } else {
                    channel.createPermissionOverride(holder).setAllow(permissions).queue();
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean clearMemberPermission(@NotNull List<GuildChannel> channels, @NotNull IPermissionHolder holder, @NotNull Permission... permissions) {
        PermissionOverride permissionOverride;
        try {
            for (GuildChannel channel : channels) {
                permissionOverride = channel.getPermissionOverride(holder);
                if (permissionOverride != null) {
                    permissionOverride.getManager().clear(permissions).queue();
                    if (Checks.isEmptyPermissionOverride(permissionOverride)) {
                        permissionOverride.delete().queue();
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean resetMemberPermission(@NotNull List<GuildChannel> channels, @NotNull IPermissionHolder holder) {
        PermissionOverride permissionOverride;
        try {
            for (GuildChannel channel : channels) {
                permissionOverride = channel.getPermissionOverride(holder);
                if (permissionOverride != null) {
                    permissionOverride.delete().queue();
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Role createMuteRole(@NotNull Guild guild, @NotNull String muteRoleName) {
        muteRoleName = Objects.requireNonNullElse(muteRoleName, "");
        if (muteRoleName.isEmpty()) {
            muteRoleName = "MuteRole";
        }
        try {
            Role muteRole = guild.createRole().setName(muteRoleName).complete(true);
            muteRole.getManager().revokePermissions(Permission.MESSAGE_WRITE, Permission.MESSAGE_ADD_REACTION).queue();
            denyMemberToDo(new ArrayList<>(guild.getTextChannels()), muteRole, Permission.MESSAGE_WRITE, Permission.MESSAGE_ADD_REACTION);
            return muteRole;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean clearTextChannel(@NotNull TextChannel channel) {
        OffsetDateTime twoWeeksAgo = OffsetDateTime.now().minus(2, ChronoUnit.WEEKS);
        try {
            List<Message> messages = channel.getIterableHistory().complete();
            messages.removeIf(item -> item.getTimeCreated().isBefore(twoWeeksAgo));
            channel.deleteMessages(messages).queue();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
