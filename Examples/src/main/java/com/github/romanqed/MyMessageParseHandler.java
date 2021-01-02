package com.github.romanqed;

import com.github.romanqed.DiscordMessageParser.AnnotationUtil.Annotations.MessageProcessing.GuildHandler;
import com.github.romanqed.DiscordMessageParser.AnnotationUtil.Annotations.MessageProcessing.PrivateHandler;
import com.github.romanqed.DiscordMessageParser.JDAUtil.Wrappers.JDAWrapper;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.MessageParseHandler;

import java.util.Set;

@GuildHandler
@PrivateHandler
public class MyMessageParseHandler extends MessageParseHandler {
    @Override
    public boolean onMessageParsing(JDAWrapper wrapper, Set<String> prefixes) {
        prefixes.add("!");
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
