import com.github.romanqed.DiscordMessageParser.JDAUtil.Wrappers.JDAWrapper;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.MessageParseHandler;

public class Handler extends MessageParseHandler {
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
