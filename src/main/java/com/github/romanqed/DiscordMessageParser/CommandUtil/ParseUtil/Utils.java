package com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil;

import com.github.romanqed.DiscordMessageParser.RegexUtil.ArgumentPattern;

public class Utils {
    public static boolean validatePatterns(boolean disableStrong, boolean disableOptional, ArgumentPattern... patterns) {
        if (disableStrong && disableOptional) {
            return false;
        }
        if (disableStrong && patterns[patterns.length - 1].isStrong()) {
            return false;
        }
        if (disableOptional && !patterns[patterns.length - 1].isStrong()) {
            return false;
        }
        for (int i = 0; i < patterns.length - 1; ++i) {
            if (disableStrong && patterns[i].isStrong()) {
                return false;
            }
            if (disableOptional && !patterns[i].isStrong()) {
                return false;
            }
            if (!patterns[i].isStrong() && patterns[i + 1].isStrong()) {
                return false;
            }
        }
        return true;
    }

    public static boolean validatePatterns(boolean disableOptional, ArgumentPattern... patterns) {
        return validatePatterns(false, disableOptional, patterns);
    }

    public static boolean validatePatterns(ArgumentPattern... patterns) {
        return validatePatterns(false, false, patterns);
    }
}
