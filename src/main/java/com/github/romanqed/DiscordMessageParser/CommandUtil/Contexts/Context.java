package com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts;

import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.ArgumentParser;
import com.github.romanqed.DiscordMessageParser.ContainerUtil.ContainerCollection;
import com.github.romanqed.DiscordMessageParser.JDAUtil.Wrappers.JDAWrapper;

public interface Context {
    ArgumentParser getArgumentParser();

    ContainerCollection getContainers();

    JDAWrapper getJDAWrapper();
}
