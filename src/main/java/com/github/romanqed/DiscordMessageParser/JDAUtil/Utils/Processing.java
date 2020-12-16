package com.github.romanqed.DiscordMessageParser.JDAUtil.Utils;

import net.dv8tion.jda.api.entities.Guild;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Processing {
    public static String parseUserMention(String userMention) {
        userMention = Objects.requireNonNullElse(userMention, "");
        return userMention.replace("!", "").replace("<@", "").replace(">", "");
    }

    public static Guild.Ban getUserBanByUserTag(List<Guild.Ban> banList, String userTag) {
        if (banList == null) {
            return null;
        }
        Optional<Guild.Ban> ban = banList.stream().findFirst().filter(predicate -> predicate.getUser().getAsTag().contentEquals(userTag));
        return ban.orElse(null);
    }
}
