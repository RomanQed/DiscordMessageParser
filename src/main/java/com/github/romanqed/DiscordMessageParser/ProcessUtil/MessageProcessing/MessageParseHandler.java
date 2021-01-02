package com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing;

import com.github.romanqed.DiscordMessageParser.JDAUtil.Wrappers.JDAWrapper;

import java.util.Set;

public abstract class MessageParseHandler {
    public boolean onMessageParsing(JDAWrapper wrapper, Set<String> prefixes) {
        return false;
    }

    public void onUnknownCommand(JDAWrapper wrapper) {
    }

    public void onAccessError(JDAWrapper wrapper) {
    }
}
