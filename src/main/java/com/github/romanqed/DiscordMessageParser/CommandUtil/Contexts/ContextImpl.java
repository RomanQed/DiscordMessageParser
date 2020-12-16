package com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts;

import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.ArgumentParser;
import com.github.romanqed.DiscordMessageParser.ContainerUtil.ContainerCollection;
import com.github.romanqed.DiscordMessageParser.JDAUtil.Wrappers.JDAWrapper;

import java.util.Objects;

public class ContextImpl implements Context {
    private final ArgumentParser parser;
    private final ContainerCollection containers;
    private final JDAWrapper wrapper;

    public ContextImpl(String arguments, JDAWrapper wrapper, ContainerCollection containers) {
        parser = new ArgumentParser(arguments);
        this.containers = Objects.requireNonNull(containers);
        this.wrapper = Objects.requireNonNull(wrapper);
    }

    @Override
    public ArgumentParser getArgumentParser() {
        return parser;
    }

    @Override
    public ContainerCollection getContainers() {
        return containers;
    }

    @Override
    public JDAWrapper getJDAWrapper() {
        return wrapper;
    }
}
