package com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil;


public class ParseResult {
    public final String commandBody;
    public final String rawArguments;
    public final ResultEnum parseResult;

    public ParseResult(ResultEnum parseResult, String commandBody, String rawArguments) {
        this.parseResult = parseResult;
        this.commandBody = commandBody;
        this.rawArguments = rawArguments;
    }

    public ParseResult(ResultEnum parseResult) {
        this(parseResult, "", "");
        if (parseResult == ResultEnum.SUCCESS) {
            throw new IllegalArgumentException();
        }
    }
}
