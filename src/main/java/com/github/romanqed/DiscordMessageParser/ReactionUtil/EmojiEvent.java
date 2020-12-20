package com.github.romanqed.DiscordMessageParser.ReactionUtil;

public interface EmojiEvent {
    long getId();

    long getMessageId();

    void setMessageId(long messageId);

    long getChannelId();

    void setChannelId(long channelIdId);

    String getEmoji();

    void setEmoji(String emoji);

    void call(ReactionContext context);

    void finalEvent();

    void atFinal(Runnable action);

    int getRemainingLifeTime();

    int getRemainingCalls();

    boolean isFinished();
}
