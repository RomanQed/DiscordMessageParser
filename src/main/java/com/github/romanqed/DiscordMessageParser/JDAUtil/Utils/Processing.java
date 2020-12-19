package com.github.romanqed.DiscordMessageParser.JDAUtil.Utils;

import com.github.romanqed.DiscordMessageParser.MathUtils.Hashes;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageReaction;

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

    public static long calculateReactionId(MessageReaction reaction) {
        long channelId = reaction.getChannel().getIdLong();
        long messageId = reaction.getMessageIdLong();
        long emojiHash = reaction.getReactionEmote().getEmoji().hashCode();
        return Hashes.calculateReactionId(channelId, messageId, emojiHash);
    }

    public static long countReactions(MessageReaction reaction) {
        try {
            return reaction.retrieveUsers().stream().count();
        } catch (Exception e) {
            return 0;
        }
    }
}
