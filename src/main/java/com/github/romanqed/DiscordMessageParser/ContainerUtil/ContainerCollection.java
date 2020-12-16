package com.github.romanqed.DiscordMessageParser.ContainerUtil;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;

public class ContainerCollection {
    private final HashMap<String, Container> containers;

    public ContainerCollection() {
        containers = new HashMap<>();
    }

    public Container getContainer(@NotNull String name) {
        return Objects.requireNonNullElse(containers.get(name), new Container(name));
    }

    public ContainerCollection getContainersByTag(@NotNull String tag) {
        ContainerCollection result = new ContainerCollection();
        containers.values().forEach(container -> {
            if (container.getTag().contentEquals(tag)) {
                result.putContainer(container);
            }
        });
        return result;
    }

    public void putContainer(@NotNull Container container) {
        if (container == null) {
            return;
        }
        containers.put(container.getName(), container);
    }

    public void removeContainer(@NotNull String name) {
        containers.remove(name);
    }

    public void removeContainer(@NotNull Container container) {
        if (container == null) {
            return;
        }
        removeContainer(container.getName());
    }

    public boolean containsContainer(@NotNull String name) {
        return containers.containsKey(name);
    }

    public boolean containsContainer(@NotNull Container container) {
        if (container == null) {
            return false;
        }
        return containsContainer(container.getName());
    }

    public boolean isEmpty() {
        return containers.isEmpty();
    }

    public int size() {
        return containers.size();
    }
}
