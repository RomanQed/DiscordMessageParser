import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.CommandParser;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CustomListenerAdapter extends ListenerAdapter {
    private final CommandParser parser = new CommandParser("!");

//    @Override
//    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
//        ProcessedCommand result = parser.parseCommand(event.getMessage().getContentRaw());
//        System.out.println(result.isSuccess());
//        System.out.println(result.getName());
//        System.out.println(result.getCommandArguments());
//    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        //System.out.println(event.getMember());
    }

    @Override
    public void onGuildMessageDelete(@NotNull GuildMessageDeleteEvent event) {
    }
}
