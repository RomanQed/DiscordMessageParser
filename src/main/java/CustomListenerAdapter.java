import com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.Guild.GuildProcessor;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.Private.PrivateProcessor;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CustomListenerAdapter extends ListenerAdapter {
    private final GuildProcessor guildProcessor = new GuildProcessor(new Handler());
    private final PrivateProcessor privateProcessor = new PrivateProcessor(new Handler());

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        if (!event.getGuild().getSelfMember().hasPermission(Permission.ADMINISTRATOR)) {
            return;
        }
        guildProcessor.processGuildCommand(event.getMessage());
    }

    @Override
    public void onGuildMessageUpdate(@NotNull GuildMessageUpdateEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        if (!event.getGuild().getSelfMember().hasPermission(Permission.ADMINISTRATOR)) {
            return;
        }
        guildProcessor.processGuildCommand(event.getMessage());
    }

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        privateProcessor.processPrivateCommand(event.getMessage());
    }

    @Override
    public void onPrivateMessageUpdate(@NotNull PrivateMessageUpdateEvent event) {
        privateProcessor.processPrivateCommand(event.getMessage());
    }
}
