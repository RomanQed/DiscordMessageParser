package com.github.romanqed.DiscordMessageParser.ReactionUtil;

import net.dv8tion.jda.api.entities.User;

public interface EmojiEvent {
    long getId();

    long getMessageId();

    void setMessageId(long messageId);

    long getChannelId();

    void setChannelId(long channelIdId);

    String getEmoji();

    void setEmoji(String emoji);

    void call(User sender);

    int getRemainingLifeTime();

    int getRemainingCalls();

    boolean isFinished();
}
