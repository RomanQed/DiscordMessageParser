package com.github.romanqed.DiscordMessageParser.ProcessUtil.ReactionProcessing.Private;

import com.github.romanqed.DiscordMessageParser.ProcessUtil.ReactionProcessing.ReactionProcessor;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EmojiEvent;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EventCollection;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.ReactionContext;

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

    public void queueReaction(ReactionContext context) {
        service.submit(() -> {
            EmojiEvent event = processReaction(context.getReaction());
            if (event != null) {
                event.call(context);
            }
        });
    }
}
