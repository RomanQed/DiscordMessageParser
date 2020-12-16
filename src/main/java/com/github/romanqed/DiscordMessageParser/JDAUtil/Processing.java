package com.github.romanqed.DiscordMessageParser.JDAUtil;

import net.dv8tion.jda.api.entities.Guild;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Processing {
    public static @NotNull String parseUserMention(@NotNull String userMention) {
        userMention = Objects.requireNonNullElse(userMention, "");
        return userMention.replace("!", "").replace("<@", "").replace(">", "");
    }

    public static @Nullable Guild.Ban getUserBanByUserTag(@NotNull List<Guild.Ban> banList, @NotNull String userTag) {
        if (banList == null) {
            return null;
        }
        Optional<Guild.Ban> ban = banList.stream().findFirst().filter(predicate -> {
            return predicate.getUser().getAsTag().contentEquals(userTag);
        });
        try {
            return ban.get();
        } catch (Exception e) {
            return null;
        }
    }
}
