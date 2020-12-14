package com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.Message;

import com.github.romanqed.DiscordMessageParser.ButtonUtil.ButtonEventList;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GuildReceivedContext extends GuildMessageContext {
    private final Message message;

    public GuildReceivedContext(Message message, ButtonEventList buttonEventList) {
        super(message.getTextChannel(), buttonEventList);
        this.message = message;
    }

    public @Nullable Member getAuthor() {
        return message.getMember();
    }

    public @NotNull String getAuthorMention() {
        return message.getAuthor().getAsMention();
    }

    public @NotNull String getAuthorTag() {
        return message.getAuthor().getAsTag();
    }
}
