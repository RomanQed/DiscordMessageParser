package Legacy.Message;

import com.github.romanqed.DiscordMessageParser.ReactionUtil.EventCollection;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;

public class PrivateReceivedContext extends PrivateMessageContext {
    private final Message message;

    public PrivateReceivedContext(Message message, EventCollection eventCollection) {
        super(message.getPrivateChannel(), eventCollection);
        this.message = message;
    }

    public @NotNull User getAuthor() {
        return message.getAuthor();
    }

    public @NotNull String getAuthorMention() {
        return message.getAuthor().getAsTag();
    }

    public @NotNull String getAuthorTag() {
        return message.getAuthor().getAsTag();
    }

    public @NotNull PrivateChannel getChannel() {
        return channel;
    }
}
