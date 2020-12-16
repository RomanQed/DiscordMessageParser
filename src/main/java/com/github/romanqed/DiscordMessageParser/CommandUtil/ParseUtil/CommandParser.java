package com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil;

import java.util.Objects;

public class CommandParser {
    public static final String DEFAULT_COMMAND_PREFIX = "!";
    private String commandPrefix;

    public CommandParser(String commandPrefix) {
        setPrefix(commandPrefix);
    }

    public CommandParser() {
        this(null);
    }

    public void setPrefix(String commandPrefix) {
        this.commandPrefix = Objects.requireNonNullElse(commandPrefix, DEFAULT_COMMAND_PREFIX);
    }

    public ProcessedCommand parseCommand(String rawString) {
        rawString = Objects.requireNonNullElse(rawString, "");
        if (!rawString.isEmpty() && rawString.startsWith(commandPrefix)) {
            rawString += " ";
            int commandBodyEnd = rawString.indexOf(' ', commandPrefix.length());
            String commandBody = rawString.substring(commandPrefix.length(), commandBodyEnd);
            return new ProcessedCommand(commandBody, rawString.substring(commandBodyEnd + 1));
        }
        return new ProcessedCommand(null, null);
    }
}
