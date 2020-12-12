package com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.Contexts.Base;

import com.github.romanqed.DiscordMessageParser.ButtonUtil.ButtonEventList;
import com.github.romanqed.DiscordMessageParser.JDAUtil.JDAUtils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.Nullable;

public class JDAContext extends BaseContext {
    protected final JDA jda;

    public JDAContext(JDA jda, ButtonEventList buttonEventList) {
        super(buttonEventList);
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
