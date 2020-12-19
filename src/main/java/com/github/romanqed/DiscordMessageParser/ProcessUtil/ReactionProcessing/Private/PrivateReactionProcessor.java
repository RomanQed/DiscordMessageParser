package com.github.romanqed.DiscordMessageParser.ProcessUtil.ReactionProcessing.Private;

import com.github.romanqed.DiscordMessageParser.ProcessUtil.ReactionProcessing.ReactionProcessor;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EmojiEvent;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EventCollection;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;

import java.util.concurrent.ExecutorService;

public class PrivateReactionProcessor extends ReactionProcessor {
    public PrivateReactionProcessor(EventCollection events, ExecutorService service) {
        super(events, service);
    }

    public PrivateReactionProcessor(EventCollection events) {
        this(events, null);
    }

    public PrivateReactionProcessor() {
        this(null, null);
    }

    public void queueReaction(MessageReaction reaction, User user) {
        service.submit(() -> {
            EmojiEvent event = processReaction(reaction);
            if (event != null) {
                event.call(reaction, user);
            }
        });
    }

    public void queueReactionRemove(long messageId) {
        service.submit(() -> processReactionRemove(messageId));
    }

    public void queueReactionRemove(MessageReaction reaction) {
        service.submit(() -> processReactionRemove(reaction));
    }
}
