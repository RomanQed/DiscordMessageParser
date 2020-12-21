package com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil;

import com.github.romanqed.DiscordMessageParser.RegexUtil.ArgumentPattern;
import com.github.romanqed.DiscordMessageParser.RegexUtil.ArgumentPatterns;
import com.github.romanqed.DiscordMessageParser.RegexUtil.CommandPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;

public class ArgumentParser {
    private final String rawArguments;
    private List<String> notParsedArguments;

    public ArgumentParser(String rawArguments) {
        this.rawArguments = Objects.requireNonNullElse(rawArguments, "");
    }

    public boolean isEmpty() {
        return rawArguments.isEmpty() || rawArguments.isBlank();
    }

    public List<String> getCommandArguments() {
        if (notParsedArguments != null) {
            return notParsedArguments;
        }
        notParsedArguments = new ArrayList<>();
        if (isEmpty()) {
            return notParsedArguments;
        }
        Matcher matcher = CommandPattern.COMMAND_ARGUMENTS.getPattern().matcher(rawArguments);
        matcher.results().forEach(matchResult -> {
            String res = matchResult.group().replace("\"", "");
            notParsedArguments.add(res.isEmpty() ? " " : res);
        });
        return notParsedArguments;
    }

    public List<String> parseArguments(int count, boolean isStrong, ArgumentPattern... patterns) {
        List<String> arguments = getCommandArguments();
        ArgumentPatterns patternList = new ArgumentPatterns(count, isStrong, patterns);
        if (!patternList.processArgumentList(arguments)) {
            return new ArrayList<>();
        }
        return arguments;
    }

    public List<String> parseStrongArguments(int count, ArgumentPattern... patterns) {
        return parseArguments(count, true, patterns);
    }

    public List<String> parseOptionalArguments(int count, ArgumentPattern... patterns) {
        return parseArguments(count, false, patterns);
    }

    public List<String> parseArguments(ArgumentPattern... patterns) {
        return parseArguments(1, false, patterns);
    }
}
