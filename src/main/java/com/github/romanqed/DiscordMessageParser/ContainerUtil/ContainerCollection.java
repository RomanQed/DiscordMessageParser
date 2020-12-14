package com.github.romanqed.DiscordMessageParser.ContainerUtil;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;

public class ContainerCollection {
    private final HashMap<String, Container> variables;

    public ContainerCollection() {
        variables = new HashMap<>();
    }

    public Container getVariable(@NotNull String name) {
        return Objects.requireNonNullElse(variables.get(name), new Container(name));
    }

    public ContainerCollection getVariablesByTag(@NotNull String tag) {
        ContainerCollection result = new ContainerCollection();
        variables.values().forEach(container -> {
            if (container.getTag().contentEquals(tag)) {
                result.putVariable(container);
            }
        });
        return result;
    }

    public void putVariable(@NotNull Container container) {
        if (container == null) {
            return;
        }
        variables.put(container.getName(), container);
    }

    public void removeVariable(@NotNull String name) {
        variables.remove(name);
    }

    public void removeVariable(@NotNull Container container) {
        if (container == null) {
            return;
        }
        removeVariable(container.getName());
    }

    public boolean containsVariable(@NotNull String name) {
        return variables.containsKey(name);
    }

    public boolean containsVariable(@NotNull Container container) {
        if (container == null) {
            return false;
        }
        return containsVariable(container.getName());
    }

    public boolean isEmpty() {
        return variables.isEmpty();
    }

    public int size() {
        return variables.size();
    }
}
