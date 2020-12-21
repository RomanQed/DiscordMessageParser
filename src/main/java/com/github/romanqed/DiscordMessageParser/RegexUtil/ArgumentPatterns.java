package com.github.romanqed.DiscordMessageParser.RegexUtil;

import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.Utils;
import com.github.romanqed.DiscordMessageParser.Utils.Lists;

import java.util.ArrayList;
import java.util.List;

public class ArgumentPatterns {
    private final List<ArgumentPattern> list;

    public ArgumentPatterns(int count, boolean isStrong, ArgumentPattern... patterns) {
        if (patterns == null || patterns.length == 0) {
            list = new ArrayList<>();
            return;
        }
        boolean checkResult = Utils.validatePatterns(count > 1 && !isStrong, count > 1 && isStrong, patterns);
        if (!checkResult) {
            throw new IllegalArgumentException("Invalid pattern list!");
        }
        if (count > 1) {
            list = Lists.multiplyList(List.of(patterns), count);
        } else {
            list = List.of(patterns);
        }
    }

    public ArgumentPatterns(ArgumentPattern... patterns) {
        this(1, false, patterns);
    }

    public boolean processArgumentList(List<String> arguments) {
        if (arguments == null || list.isEmpty()) {
            return false;
        }
        if (arguments.size() < getStrongArgumentsCount() || arguments.size() > getSize()) {
            return false;
        }
        int size = Math.min(arguments.size(), list.size());
        for (int i = 0; i < size; ++i) {
            if (!list.get(i).getPattern().matcher(arguments.get(i)).matches()) {
                return false;
            }
        }
        for (int i = 0; i < list.size() - arguments.size(); ++i) {
            arguments.add("");
        }
        return true;
    }

    public ArgumentPattern get(int index) {
        return list.get(index);
    }

    public int getOptionalArgumentsCount() {
        return (int) list.stream().filter(argumentPattern -> !argumentPattern.isStrong()).count();
    }

    public int getStrongArgumentsCount() {
        return getSize() - getOptionalArgumentsCount();
    }

    public int getSize() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
