package com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CommandParser {
    public static final String DEFAULT_COMMAND_PREFIX = "!";
    private final Set<String> prefixes;

    public CommandParser(String prefix) {
        prefixes = new HashSet<>();
        addPrefix(prefix);
    }

    public CommandParser() {
        this(null);
    }

    public void addPrefix(String prefix) {
        prefixes.add(Objects.requireNonNullElse(prefix, DEFAULT_COMMAND_PREFIX));
    }

    public void addPrefixes(Set<String> prefixes) {
        if (prefixes != null) {
            this.prefixes.addAll(prefixes);
        }
    }

    public void clearPrefixes() {
        prefixes.clear();
    }

    public ProcessedCommand parseCommand(String rawString) {
        rawString = Objects.requireNonNullElse(rawString, "");
        if (!rawString.isEmpty()) {
            String commandPrefix = findPrefix(rawString);
            if (commandPrefix != null) {
                rawString += " ";
                int commandBodyEnd = rawString.indexOf(' ', commandPrefix.length());
                String commandBody = rawString.substring(commandPrefix.length(), commandBodyEnd);
                return new ProcessedCommand(commandBody, rawString.substring(commandBodyEnd + 1));
            }
        }
        return new ProcessedCommand(null, null);
    }

    private String findPrefix(String rawString) {
        return prefixes.stream()
                .filter(rawString::startsWith)
                .findFirst()
                .orElse(null);
    }
}
