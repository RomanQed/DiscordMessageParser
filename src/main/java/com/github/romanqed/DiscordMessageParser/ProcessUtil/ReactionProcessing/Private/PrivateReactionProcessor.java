package com.github.romanqed.DiscordMessageParser.ProcessUtil.ReactionProcessing.Private;

import com.github.romanqed.DiscordMessageParser.ProcessUtil.ReactionProcessing.ReactionProcessor;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EmojiEvent;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.LinkedEmojiEvent;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;

import java.util.concurrent.ExecutorService;

public class PrivateReactionProcessor extends ReactionProcessor {
    public PrivateReactionProcessor(ExecutorService service) {
        super(service, LinkedEmojiEvent.COLLECTION);
    }

    public PrivateReactionProcessor() {
        this(null);
    }

    public void queueReaction(MessageReaction reaction, User user) {
        service.submit(() -> {
            EmojiEvent event = processReaction(reaction);
            if (event != null) {
                event.call(user);
            }
        });
    }

    public void queueReactionRemove(long messageId) {
        service.submit(() -> processReactionRemove(messageId));
    }

}
