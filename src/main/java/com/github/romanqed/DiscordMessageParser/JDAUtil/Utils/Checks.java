package com.github.romanqed.DiscordMessageParser.JDAUtil.Utils;

import com.github.romanqed.DiscordMessageParser.RegexUtil.Patterns;
import net.dv8tion.jda.api.entities.PermissionOverride;

public class Checks {
    public static boolean isId(String id) {
        try {
            return id.matches(Patterns.ID.getRegex());
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isUserMention(String userMention) {
        try {
            return userMention.matches(Patterns.USER_MENTION.getRegex());
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isChannelMention(String channelMention) {
        try {
            return channelMention.matches(Patterns.CHANNEL_MENTION.getRegex());
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isUserTag(String rawUserName) {
        try {
            return rawUserName.matches(Patterns.USER_TAG.getRegex());
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isEmptyPermissionOverride(PermissionOverride permissionOverride) {
        try {
            return permissionOverride.getDeniedRaw() == 0 && permissionOverride.getAllowedRaw() == 0;
        } catch (Exception e) {
            return true;
        }
    }
}
