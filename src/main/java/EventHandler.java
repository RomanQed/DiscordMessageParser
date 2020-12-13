import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.Contexts.Message.GuildReceivedContext;
import com.github.romanqed.DiscordMessageParser.EventUtil.IParseEventHandler;

public class EventHandler implements IParseEventHandler {
    @Override
    public void onGuildEmptyCommand(GuildReceivedContext context) {
        context.sendMessage("empty_cmd");
    }

    @Override
    public void onGuildPermissionError(GuildReceivedContext context) {
        context.sendMessage("permission_err");
    }

    @Override
    public void onGuildRoleError(GuildReceivedContext context) {
        context.sendMessage("role_err");
    }

    @Override
    public boolean onGuildMessageParsing(GuildReceivedContext context, StringBuilder prefix) {
        prefix.append("!");
        return false;
    }
}
