package com.github.romanqed.DiscordMessageParser.ReactionUtil;

import java.util.function.Consumer;

public interface EmojiEvent {
    long getId();

    long getMessageId();

    void setMessageId(long messageId);

    long getChannelId();

    void setChannelId(long channelIdId);

    String getEmoji();

    void setEmoji(String emoji);

    void setAction(Consumer<ReactionContext> action);

    void call(ReactionContext context);

    void finalEvent();

    void atFinal(Runnable action);

    void stopFollow();

    int getRemainingLifeTime();

    int getRemainingCalls();

    boolean isFinished();
}
