package com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil;


import java.util.Objects;

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

    public String getRawArguments() {
        return rawArguments;
    }
}
