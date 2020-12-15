package com.github.romanqed.DiscordMessageParser.RegexUtil;

import java.util.ArrayList;
import java.util.List;

public class ArgumentPatternList {
    private final List<ArgumentPattern> list;

    public ArgumentPatternList(ArgumentPattern... patterns) {
        if (patterns == null || patterns.length == 0) {
            list = new ArrayList<>();
        } else {
            for (int i = 0; i < patterns.length - 1; ++i) {
                if (!patterns[i].isStrong() && patterns[i + 1].isStrong()) {
                    throw new IllegalArgumentException("Strong parameters cannot be followed after optional!");
                }
            }
            list = List.of(patterns);
        }
    }

    public boolean processArgumentList(List<String> arguments) {
        if (arguments == null) {
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
}
