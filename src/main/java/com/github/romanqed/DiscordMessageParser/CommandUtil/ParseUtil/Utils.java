package com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil;

import com.github.romanqed.DiscordMessageParser.RegexUtil.Patterns;
import net.dv8tion.jda.api.entities.Role;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {
    public static boolean validatePatterns(boolean disableStrong, boolean disableOptional, Patterns... patterns) {
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

    public static boolean validatePatterns(boolean disableOptional, Patterns... patterns) {
        return validatePatterns(false, disableOptional, patterns);
    }

    public static boolean validatePatterns(Patterns... patterns) {
        return validatePatterns(false, false, patterns);
    }

    public static boolean listMatchesPatterns(List<String> rawList, List<Patterns> patterns) {
        if (rawList.size() != patterns.size()) {
            return false;
        }
        for (int i = 0; i < rawList.size(); ++i) {
            if (!rawList.get(i).matches(patterns.get(i).getRegex())) {
                return false;
            }
        }
        return true;
    }

    public static Set<String> rolesToString(Collection<Role> roles) {
        Set<String> ret = new HashSet<>();
        for (Role role : roles) {
            ret.add(role.getName());
        }
        return ret;
    }
}
