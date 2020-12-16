package com.github.romanqed.DiscordMessageParser.JDAUtil.Utils;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GuildUtils {
    public static boolean deny(GuildChannel channel, IPermissionHolder holder, Permission... permissions) {
        try {
            return deny(List.of(channel), holder, permissions);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean allow(GuildChannel channel, IPermissionHolder holder, Permission... permissions) {
        try {
            return allow(List.of(channel), holder, permissions);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean clearPermission(GuildChannel channel, IPermissionHolder holder, Permission... permissions) {
        try {
            return clearPermission(List.of(channel), holder, permissions);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean resetPermission(GuildChannel channel, IPermissionHolder holder) {
        try {
            return resetPermission(List.of(channel), holder);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean deny(List<GuildChannel> channels, IPermissionHolder holder, Permission... permissions) {
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

    public static boolean allow(List<GuildChannel> channels, IPermissionHolder holder, Permission... permissions) {
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

    public static boolean clearPermission(List<GuildChannel> channels, IPermissionHolder holder, Permission... permissions) {
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

    public static boolean resetPermission(List<GuildChannel> channels, IPermissionHolder holder) {
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

    public static Role createMuteRole(Guild guild, String muteRoleName) {
        muteRoleName = Objects.requireNonNullElse(muteRoleName, "");
        if (muteRoleName.isEmpty()) {
            muteRoleName = "MuteRole";
        }
        try {
            Role muteRole = guild.createRole().setName(muteRoleName).complete(true);
            muteRole.getManager().revokePermissions(Permission.MESSAGE_WRITE, Permission.MESSAGE_ADD_REACTION).queue();
            deny(new ArrayList<>(guild.getTextChannels()), muteRole, Permission.MESSAGE_WRITE, Permission.MESSAGE_ADD_REACTION);
            return muteRole;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean clearTextChannel(TextChannel channel) {
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
