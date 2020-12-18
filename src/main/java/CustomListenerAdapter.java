import com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.Guild.GuildMessageProcessor;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.MessageProcessing.Private.PrivateMessageProcessor;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.ReactionProcessing.Guild.GuildReactionProcessor;
import com.github.romanqed.DiscordMessageParser.ProcessUtil.ReactionProcessing.Private.PrivateReactionProcessor;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveAllEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CustomListenerAdapter extends ListenerAdapter {
    private final GuildMessageProcessor guildMessageProcessor = new GuildMessageProcessor(new Handler());
    private final PrivateMessageProcessor privateMessageProcessor = new PrivateMessageProcessor(new Handler());
    private final GuildReactionProcessor guildReactionProcessor = new GuildReactionProcessor();
    private final PrivateReactionProcessor privateReactionProcessor = new PrivateReactionProcessor();

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        if (!event.getGuild().getSelfMember().hasPermission(Permission.ADMINISTRATOR)) {
            return;
        }
        long start = System.nanoTime();
        guildMessageProcessor.queueMessage(event.getMessage());
        event.getChannel().sendMessage(Long.toString(System.nanoTime() - start)).queue();
    }

    @Override
    public void onGuildMessageUpdate(@NotNull GuildMessageUpdateEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        if (!event.getGuild().getSelfMember().hasPermission(Permission.ADMINISTRATOR)) {
            return;
        }
        long start = System.nanoTime();
        guildMessageProcessor.queueMessage(event.getMessage());
        event.getChannel().sendMessage(Long.toString(System.nanoTime() - start)).queue();
    }

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        privateMessageProcessor.queueMessage(event.getMessage());
    }

    @Override
    public void onPrivateMessageUpdate(@NotNull PrivateMessageUpdateEvent event) {
        privateMessageProcessor.queueMessage(event.getMessage());
    }

    @Override
    public void onGuildMessageDelete(@NotNull GuildMessageDeleteEvent event) {
        long start = System.nanoTime();
        guildReactionProcessor.queueReactionRemove(event.getGuild().getIdLong(), event.getMessageIdLong());
        event.getChannel().sendMessage(Long.toString(System.nanoTime() - start)).queue();
    }

    @Override
    public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event) {
        if (event.getUser().isBot()) {
            return;
        }
        if (!event.getGuild().getSelfMember().hasPermission(Permission.ADMINISTRATOR)) {
            return;
        }
        long start = System.nanoTime();
        guildReactionProcessor.queueReaction(event.getReaction(), event.getUser());
        event.getChannel().sendMessage(Long.toString(System.nanoTime() - start)).queue();
    }

    @Override
    public void onGuildMessageReactionRemove(@NotNull GuildMessageReactionRemoveEvent event) {
        if (event.getUser().isBot()) {
            return;
        }
        if (!event.getGuild().getSelfMember().hasPermission(Permission.ADMINISTRATOR)) {
            return;
        }
        try {
            long start = System.nanoTime();
            guildReactionProcessor.queueReaction(event.getReaction(), event.getUser());
            event.getChannel().sendMessage(Long.toString(System.nanoTime() - start)).queue();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void onGuildMessageReactionRemoveAll(@NotNull GuildMessageReactionRemoveAllEvent event) {
        long start = System.nanoTime();
        guildReactionProcessor.queueReactionRemove(event.getGuild().getIdLong(), event.getMessageIdLong());
        event.getChannel().sendMessage(Long.toString(System.nanoTime() - start)).queue();
    }
}
