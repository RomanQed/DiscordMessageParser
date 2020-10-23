package com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand;

import com.github.romanqed.DiscordMessageParser.ButtonUtil.ButtonEventList;
import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.RegexUtil.ArgumentPattern;
import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.RegexUtil.ArgumentPatternList;
import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.Utils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BotCommandEvent {
    protected final ButtonEventList buttonEventList;
    protected final String rawArguments;

    public BotCommandEvent(@NotNull String rawArguments, @Nullable ButtonEventList buttonEventList) {
        this.rawArguments = Objects.requireNonNullElse(rawArguments, "");
        this.buttonEventList = Objects.requireNonNullElse(buttonEventList, new ButtonEventList());
    }

    public @NotNull List<String> parseArguments(@NotNull ArgumentPattern... patterns) {
        List<String> arguments = Utils.getCommandArguments(rawArguments);
        if (patterns != null && patterns.length != 0) {
            ArgumentPatternList patternList = new ArgumentPatternList(patterns);
            if (!patternList.validateArgumentList(arguments)) {
                return new ArrayList<>();
            }
        }
        return arguments;
    }
}
