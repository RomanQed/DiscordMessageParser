package com.github.romanqed.DiscordMessageParser.RegexUtil;

import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.Utils;
import com.github.romanqed.DiscordMessageParser.Utils.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArgumentPatterns {
    private final List<Patterns> list;

    public ArgumentPatterns(int count, boolean isStrong, Patterns... patterns) {
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

    public ArgumentPatterns(Patterns... patterns) {
        this(1, false, patterns);
    }

    public List<String> processArguments(List<String> arguments) {
        List<String> ret = new ArrayList<>();
        if (arguments == null || list.isEmpty()) {
            return ret;
        }
        int size = getStrongArgumentsCount();
        if (arguments.size() < size || arguments.size() > list.size()) {
            return ret;
        }
        if (size != 0) {
            List<String> rawList = arguments.subList(0, size);
            if (!Utils.listMatchesPatterns(rawList, list.subList(0, size))) {
                return ret;
            }
            ret.addAll(rawList);
        }
        for (int i = size; i < arguments.size(); ++i) {
            String rawArg = arguments.get(i);
            if (!rawArg.matches(list.get(i).getRegex())) {
                ret.add("");
            } else {
                ret.add(rawArg);
            }
        }
        ret.addAll(Collections.nCopies(list.size() - ret.size(), ""));
        return ret;
    }

    public int getOptionalArgumentsCount() {
        return (int) list.stream().filter(argumentPattern -> !argumentPattern.isStrong()).count();
    }

    public int getStrongArgumentsCount() {
        return list.size() - getOptionalArgumentsCount();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
