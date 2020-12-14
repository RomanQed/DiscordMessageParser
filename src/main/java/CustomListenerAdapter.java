import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.CommandParser;
import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.ProcessedCommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CustomListenerAdapter extends ListenerAdapter {
    private final CommandParser parser = new CommandParser("!");

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        ProcessedCommand result = parser.parseCommand(event.getMessage().getContentRaw());
        System.out.println(result.isSuccess());
        System.out.println(result.getName());
        System.out.println(result.getCommandArguments());
    }
}
