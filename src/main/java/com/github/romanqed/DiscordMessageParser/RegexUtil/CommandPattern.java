package com.github.romanqed.DiscordMessageParser.RegexUtil;

import java.util.regex.Pattern;

public enum CommandPattern {
    COMMAND_ARGUMENTS("\"([^\"]+)\"|\\S+");
    private final Pattern pattern;

    CommandPattern(String regex) {
        this.pattern = Pattern.compile(regex, Pattern.MULTILINE);
    }

    public Pattern getPattern() {
        return pattern;
    }
}
