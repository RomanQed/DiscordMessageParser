package com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.Base;

import com.github.romanqed.DiscordMessageParser.JDAUtil.JDAUtils;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EventCollection;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.Nullable;

public class JDAContext extends GenericContext {
    protected final JDA jda;

    public JDAContext(JDA jda, EventCollection eventCollection) {
        super(eventCollection);
        this.jda = jda;
    }

    public @Nullable User getUserById(long id) {
        try {
            return jda.retrieveUserById(id).complete();
        } catch (Exception e) {
            return null;
        }
    }

    public @Nullable User getUserById(String id) {
        try {
            return jda.retrieveUserById(id).complete();
        } catch (Exception e) {
            return null;
        }
    }

    public @Nullable User getUserByMention(String userMention) {
        try {
            return jda.retrieveUserById(JDAUtils.parseUserMention(userMention)).complete();
        } catch (Exception e) {
            return null;
        }
    }
}
