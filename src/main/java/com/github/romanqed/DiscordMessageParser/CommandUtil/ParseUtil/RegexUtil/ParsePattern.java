package com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.RegexUtil;

import java.util.regex.Pattern;

public enum ParsePattern {
    BOT_ARGUMENTS("\"([^\"]+)\"|\\S+");
    private final Pattern pattern;

    ParsePattern(String regex) {
        this.pattern = Pattern.compile(regex, Pattern.MULTILINE);
    }

    public Pattern getPattern() {
        return pattern;
    }
}
