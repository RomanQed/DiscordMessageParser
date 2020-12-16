package com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing;

import com.github.romanqed.DiscordMessageParser.JDAUtil.Wrappers.JDAWrapper;

public abstract class MessageParseHandler {
    public boolean onMessageParsing(JDAWrapper wrapper, StringBuilder prefix) {
        // Need break
        return false;
    }

    public void onUnknownCommand(JDAWrapper wrapper) {
    }

    public void onAccessError(JDAWrapper wrapper) {
    }
}
