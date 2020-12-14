package com.github.romanqed.DiscordMessageParser.RegexUtil;

import java.util.regex.Pattern;

public enum ArgumentPattern {
    ID("\\d{18}"),
    OPTIONAL_ID("\\d{18}", false),
    USER_MENTION("<@!{0,1}\\d{18}>"),
    OPTIONAL_USER_MENTION("<@!{0,1}\\d{18}>", false),
    USER_TAG("[^@]{2,32}#\\d{4}"),
    OPTIONAL_USER_TAG("[^@]{2,32}#\\d{4}", false),
    NUMBER("[-+]?([0-9]*\\.[0-9]+|[0-9]+)"),
    OPTIONAL_NUMBER("[-+]?([0-9]*\\.[0-9]+|[0-9]+)", false),
    STRING(".{0,}"),
    OPTIONAL_STRING(".{0,}", false);
    private final Pattern pattern;
    private final boolean isStrong;

    ArgumentPattern(String regex, boolean isStrong) {
        this.pattern = Pattern.compile(regex, Pattern.MULTILINE);
        this.isStrong = isStrong;
    }

    ArgumentPattern(String regex) {
        this(regex, true);
    }

    public Pattern getPattern() {
        return pattern;
    }

    public boolean isStrong() {
        return isStrong;
    }
}
