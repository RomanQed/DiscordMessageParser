package com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil;


import java.util.Objects;

public class ParseResult {
    public final String commandBody;
    public final String rawArguments;
    public ResultEnum parseResult;

    public ParseResult(ResultEnum parseResult, String commandBody, String rawArguments) {
        this.parseResult = Objects.requireNonNullElse(parseResult, ResultEnum.ERROR);
        this.commandBody = Objects.requireNonNullElseGet(commandBody, () -> {
            this.parseResult = ResultEnum.ERROR;
            return "";
        });
        this.rawArguments = Objects.requireNonNullElse(rawArguments, "");
    }

    public ParseResult(ResultEnum parseResult) {
        this(parseResult, "", "");
        if (parseResult == ResultEnum.SUCCESS) {
            throw new IllegalArgumentException();
        }
    }
}
