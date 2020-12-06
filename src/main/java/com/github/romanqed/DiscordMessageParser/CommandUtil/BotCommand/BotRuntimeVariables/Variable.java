package com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.BotRuntimeVariables;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Variable {
    private final String name;
    private Object value;
    private String tag;

    public <T> Variable(@NotNull String name, T value, @NotNull String tag) {
        this.name = Objects.requireNonNullElse(name, "");
        this.value = value;
        this.tag = Objects.requireNonNullElse(tag, "");
    }

    public <T> Variable(@NotNull String name, @Nullable T value) {
        this(name, value, "");
    }

    public Variable(@NotNull String name) {
        this(name, null, "");
    }

    public Variable() {
        this("", null, "");
    }

    public @NotNull String getName() {
        return name;
    }

    @SuppressWarnings("unchecked")
    public <T> @Nullable T getValue() {
        try {
            return (T) value;
        } catch (ClassCastException e) {
            return null;
        }
    }

    public <T> void setValue(@Nullable T value) {
        try {
            this.value = value;
        } catch (ClassCastException e) {
            this.value = null;
        }
    }

    public @NotNull String getTag() {
        return tag;
    }

    public void setTag(@NotNull String tag) {
        this.tag = Objects.requireNonNullElse(tag, "");
    }

    public boolean isEmpty() {
        return (value == null) && (name.contentEquals(""));
    }
}