package com.github.romanqed.DiscordMessageParser.JDAUtil;

import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.RegexUtil.ArgumentPattern;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.requests.restaction.pagination.MessagePaginationAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class JDAUtils {
    public static final int MAX_DEL_DAYS = 7;
    public static final int MIN_DEL_DAYS = 0;

    public static @NotNull String parseUserMention(@NotNull String userMention) {
        userMention = Objects.requireNonNullElse(userMention, "");
        return userMention.replace("!", "").replace("<@", "").replace(">", "");
    }

    public static @Nullable Guild.Ban getUserBanById(@NotNull List<Guild.Ban> banList, long id) {
        if (banList == null) {
            return null;
        }
        Optional<Guild.Ban> ban = banList.stream().findFirst().filter(predicate -> {
            return predicate.getUser().getIdLong() == id;
        });
        try {
            return ban.get();
        } catch (Exception e) {
            return null;
        }
    }

    public static @Nullable Guild.Ban getUserBanByUserTag(@NotNull List<Guild.Ban> banList, @NotNull String userTag) {
        if (banList == null) {
            return null;
        }
        Optional<Guild.Ban> ban = banList.stream().findFirst().filter(predicate -> {
            return predicate.getUser().getAsTag().contentEquals(userTag);
        });
        try {
            return ban.get();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isId(@NotNull String id) {
        try {
            return ArgumentPattern.ID.getPattern().matcher(id).matches();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isUserMention(@NotNull String userMention) {
        try {
            return ArgumentPattern.USER_MENTION.getPattern().matcher(userMention).matches();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isUserTag(@NotNull String rawUserName) {
        rawUserName = Objects.requireNonNullElse(rawUserName, "");
        return ArgumentPattern.USER_TAG.getPattern().matcher(rawUserName).matches();
    }

    public static boolean isEmptyPermissionOverride(@NotNull PermissionOverride permissionOverride) {
        try {
            return permissionOverride.getDeniedRaw() == 0 && permissionOverride.getAllowedRaw() == 0;
        } catch (Exception e) {
            return true;
        }
    }

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
                    if (JDAUtils.isEmptyPermissionOverride(permissionOverride)) {
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
        try {
            Boolean result;
            MessagePaginationAction history = channel.getIterableHistory();
            history.queue();
            List<Message> messages = history.getCached();
            channel.deleteMessages(messages).queue();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
