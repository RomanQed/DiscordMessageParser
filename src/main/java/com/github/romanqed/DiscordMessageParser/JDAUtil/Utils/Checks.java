package com.github.romanqed.DiscordMessageParser.JDAUtil.Utils;

import com.github.romanqed.DiscordMessageParser.RegexUtil.ArgumentPattern;
import net.dv8tion.jda.api.entities.PermissionOverride;

import java.util.Objects;

public class Checks {
    public static boolean isId(String id) {
        try {
            return ArgumentPattern.ID.getPattern().matcher(id).matches();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isUserMention(String userMention) {
        try {
            return ArgumentPattern.USER_MENTION.getPattern().matcher(userMention).matches();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isUserTag(String rawUserName) {
        rawUserName = Objects.requireNonNullElse(rawUserName, "");
        return ArgumentPattern.USER_TAG.getPattern().matcher(rawUserName).matches();
    }

    public static boolean isEmptyPermissionOverride(PermissionOverride permissionOverride) {
        try {
            return permissionOverride.getDeniedRaw() == 0 && permissionOverride.getAllowedRaw() == 0;
        } catch (Exception e) {
            return true;
        }
    }
}
