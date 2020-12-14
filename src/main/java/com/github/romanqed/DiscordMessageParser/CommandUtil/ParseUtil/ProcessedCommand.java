package com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil;


import com.github.romanqed.DiscordMessageParser.RegexUtil.CommandPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;

public class ProcessedCommand {
    private final String commandBody;
    private final String rawArguments;
    private final boolean isSuccess;

    public ProcessedCommand(String commandBody, String rawArguments) {
        this.commandBody = Objects.requireNonNullElse(commandBody, "");
        this.rawArguments = Objects.requireNonNullElse(rawArguments, "");
        this.isSuccess = !this.commandBody.isEmpty();
    }

    public String getName() {
        return commandBody;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public List<String> getCommandArguments() {
        List<String> list = new ArrayList<>();
        if (!isSuccess || rawArguments.isEmpty()) {
            return list;
        }
        Matcher matcher = CommandPattern.COMMAND_ARGUMENTS.getPattern().matcher(rawArguments);
        matcher.results().forEach(matchResult -> {
            String res = matchResult.group().replace("\"", "");
            list.add(res.isEmpty() ? " " : res);
        });
        return list;
    }
}
