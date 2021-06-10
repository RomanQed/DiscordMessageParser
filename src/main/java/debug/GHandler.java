package debug;

import com.github.romanqed.DiscordMessageParser.AnnotationUtil.Annotations.MessageProcessing.GuildHandler;
import com.github.romanqed.DiscordMessageParser.JDAUtil.Wrappers.JDAWrapper;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.MessageParseHandler;

import java.util.Set;

@GuildHandler
public class GHandler extends MessageParseHandler {
    @Override
    public boolean onMessageParsing(JDAWrapper wrapper, Set<String> prefixes) {
        prefixes.add("!");
        return false;
    }

    @Override
    public void onUnknownCommand(JDAWrapper wrapper) {
        wrapper.reply("Ты че, дурак?");
    }
}
