package com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.BotRuntimeVariables;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;

public class VariableList {
    private final HashMap<String, Variable> variables;

    public VariableList() {
        variables = new HashMap<>();
    }

    public Variable getVariable(@NotNull String name) {
        return Objects.requireNonNullElse(variables.get(name), new Variable(name));
    }

    public VariableList getVariablesByTag(@NotNull String tag) {
        VariableList result = new VariableList();
        variables.values().forEach(variable -> {
            if (variable.getTag().contentEquals(tag)) {
                result.putVariable(variable);
            }
        });
        return result;
    }

    public void putVariable(@NotNull Variable variable) {
        variables.put(variable.getName(), variable);
    }

    public void removeVariable(@NotNull String name) {
        variables.remove(name);
    }

    public void removeVariable(@NotNull Variable variable) {
        removeVariable(variable.getName());
    }

    public boolean containsVariable(@NotNull String name) {
        return variables.containsKey(name);
    }

    public boolean containsVariable(@NotNull Variable variable) {
        return containsVariable(variable.getName());
    }

    public boolean isEmpty() {
        return variables.isEmpty();
    }

    public int size() {
        return variables.size();
    }
}
