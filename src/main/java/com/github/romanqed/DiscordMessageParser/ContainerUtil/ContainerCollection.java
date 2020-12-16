package com.github.romanqed.DiscordMessageParser.ContainerUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContainerCollection {
    private final Map<Integer, Container> containers;

    public ContainerCollection() {
        containers = new ConcurrentHashMap<>();
    }

    public Container getContainer(String name) {
        return containers.get(name.hashCode());
    }

    public ContainerCollection getContainersByTag(String tag) {
        ContainerCollection result = new ContainerCollection();
        containers.values().forEach(container -> {
            if (container.getTag().equals(tag)) {
                result.putContainer(container);
            }
        });
        return result;
    }

    public void putContainer(Container container) {
        if (container == null) {
            return;
        }
        containers.put(container.getName().hashCode(), container);
    }

    public void removeContainer(String name) {
        containers.remove(name.hashCode());
    }

    public void removeContainer(Container container) {
        if (container == null) {
            return;
        }
        removeContainer(container.getName());
    }

    public boolean containsContainer(String name) {
        return containers.containsKey(name.hashCode());
    }

    public boolean containsContainer(Container container) {
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
