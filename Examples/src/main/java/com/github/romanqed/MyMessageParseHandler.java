package com.github.romanqed;

import com.github.romanqed.DiscordMessageParser.AnnotationUtil.Annotations.MessageProcessing.GuildHandler;
import com.github.romanqed.DiscordMessageParser.AnnotationUtil.Annotations.MessageProcessing.PrivateHandler;
import com.github.romanqed.DiscordMessageParser.JDAUtil.Wrappers.JDAWrapper;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.MessageParseHandler;

@GuildHandler
@PrivateHandler
public class MyMessageParseHandler extends MessageParseHandler {
    @Override
    public boolean onMessageParsing(JDAWrapper wrapper, StringBuilder prefix) {
        prefix.append("!");
        return false;
    }

    @Override
    public void onUnknownCommand(JDAWrapper wrapper) {
        wrapper.reply("Unknown command!");
    }

    @Override
    public void onAccessError(JDAWrapper wrapper) {
        wrapper.reply("Don't have access!");
    }
}
