package com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil;

import com.github.romanqed.DiscordMessageParser.RegexUtil.ArgumentPattern;
import com.github.romanqed.DiscordMessageParser.RegexUtil.ArgumentPatternList;
import com.github.romanqed.DiscordMessageParser.RegexUtil.CommandPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;

public class ArgumentParser {
    private final String rawArguments;

    public ArgumentParser(String rawArguments) {
        this.rawArguments = Objects.requireNonNullElse(rawArguments, "");
    }

    public boolean isEmpty() {
        return rawArguments.isEmpty() || rawArguments.isBlank();
    }

    public List<String> getCommandArguments() {
        List<String> list = new ArrayList<>();
        if (isEmpty()) {
            return list;
        }
        Matcher matcher = CommandPattern.COMMAND_ARGUMENTS.getPattern().matcher(rawArguments);
        matcher.results().forEach(matchResult -> {
            String res = matchResult.group().replace("\"", "");
            list.add(res.isEmpty() ? " " : res);
        });
        return list;
    }

    public List<String> parseArguments(ArgumentPattern... patterns) {
        List<String> arguments = getCommandArguments();
        if (patterns != null && patterns.length != 0) {
            ArgumentPatternList patternList = new ArgumentPatternList(patterns);
            if (!patternList.processArgumentList(arguments)) {
                return new ArrayList<>();
            }
        }
        return arguments;
    }
}
