package Legacy.Base;

import com.github.romanqed.DiscordMessageParser.ReactionUtil.EmojiEvent;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EventCollection;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GenericContext {
    protected final EventCollection eventCollection;

    public GenericContext(EventCollection eventCollection) {
        this.eventCollection = eventCollection;
    }

    public void addEventToSentMessage(Message sentMessage, EmojiEvent event) {
        try {
            sentMessage.addReaction(event.getUnicodeId()).queue();
            event.setChannelId(sentMessage.getChannel().getId());
            event.setMessageId(sentMessage.getId());
            eventCollection.add(event);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public Message sendMessage(@NotNull MessageChannel channel, @NotNull Message message, @Nullable EmojiEvent emojiEvent) {
        try {
            Message sentMessage = channel.sendMessage(message).complete();
            if (emojiEvent != null) {
                addEventToSentMessage(sentMessage, emojiEvent);
            }
            return sentMessage;
        } catch (Exception e) {
            return null;
        }
    }

    public Message sendMessage(@NotNull MessageChannel channel, @NotNull String rawMessage, @Nullable EmojiEvent emojiEvent) {
        Message message;
        try {
            message = new MessageBuilder(rawMessage).build();
        } catch (Exception e) {
            return null;
        }
        return sendMessage(channel, message, emojiEvent);
    }
}
