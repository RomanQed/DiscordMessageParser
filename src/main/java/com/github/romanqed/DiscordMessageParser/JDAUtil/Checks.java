package com.github.romanqed.DiscordMessageParser.JDAUtil;

import com.github.romanqed.DiscordMessageParser.RegexUtil.ArgumentPattern;
import net.dv8tion.jda.api.entities.PermissionOverride;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Checks {
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
}
