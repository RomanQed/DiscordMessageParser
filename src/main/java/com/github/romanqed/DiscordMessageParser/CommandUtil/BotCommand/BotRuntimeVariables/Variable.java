package com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.BotRuntimeVariables;

import org.jetbrains.annotations.NotNull;

public class Variable {
    private final String name;
    private Object value;
    private String tag;

    public <T> Variable(@NotNull String name, T value, @NotNull String tag) {
        this.name = name;
        this.value = value;
        this.tag = tag;
    }

    public <T> Variable(@NotNull String name, T value) {
        this(name, value, "");
    }

    public Variable(@NotNull String name) {
        this(name, null, "");
    }

    public Variable() {
        this("", null, "");
    }

    public String getName() {
        return name;
    }

    @SuppressWarnings("unchecked")
    public <T> T getValue() {
        try {
            return (T) value;
        } catch (ClassCastException e) {
            return null;
        }
    }

    public <T> void setValue(T value) {
        try {
            this.value = value;
        } catch (ClassCastException e) {
            this.value = null;
        }
    }

    public String getTag() {
        return tag;
    }

    public void setTag(@NotNull String tag) {
        this.tag = tag;
    }

    public boolean isEmpty() {
        return (value == null) && (name.contentEquals(""));
    }
}
