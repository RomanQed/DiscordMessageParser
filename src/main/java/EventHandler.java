import com.github.romanqed.DiscordMessageParser.EventUtil.IParseEventHandler;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class EventHandler implements IParseEventHandler {
    @Override
    public void onGuildEmptyCommand(GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage("empty_cmd").queue();
    }

    @Override
    public void onGuildPermissionError(GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage("permission_err").queue();
    }

    @Override
    public void onGuildRoleError(GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage("role_err").queue();
    }

    @Override
    public boolean onGuildMessageParsing(GuildMessageReceivedEvent event, StringBuilder prefix) {
        prefix.append("!");
        return false;
    }
}
