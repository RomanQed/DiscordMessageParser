package Legacy.Base;

import com.github.romanqed.DiscordMessageParser.JDAUtil.Checks;
import com.github.romanqed.DiscordMessageParser.JDAUtil.Constants;
import com.github.romanqed.DiscordMessageParser.JDAUtil.GuildUtils;
import com.github.romanqed.DiscordMessageParser.JDAUtil.Processing;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EmojiEvent;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EventCollection;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GuildContext extends GenericContext {
    private final Guild guild;

    public GuildContext(Guild guild, EventCollection eventCollection) {
        super(eventCollection);
        this.guild = guild;
    }

    public @NotNull Guild getGuild() {
        return guild;
    }

    public @NotNull String getGuildId() {
        return guild.getId();
    }

    public Message sendMessage(@NotNull String channelId, @NotNull Message message, @Nullable EmojiEvent emojiEvent) {
        TextChannel channel;
        try {
            channel = guild.getTextChannelById(channelId);
            return sendMessage(channel, message, emojiEvent);
        } catch (Exception e) {
            return null;
        }
    }

    public Message sendMessage(@NotNull String channelId, @NotNull String rawMessage, @Nullable EmojiEvent emojiEvent) {
        Message message;
        try {
            message = new MessageBuilder(rawMessage).build();
        } catch (Exception e) {
            return null;
        }
        return sendMessage(channelId, message, emojiEvent);
    }

    public @Nullable List<Guild.Ban> getGuildBanList() {
        try {
            return guild.retrieveBanList().complete();
        } catch (Exception e) {
            return null;
        }
    }

    public @Nullable Member getMemberById(long memberId) {
        try {
            return guild.retrieveMemberById(memberId).complete();
        } catch (Exception e) {
            return null;
        }
    }

    public @Nullable Member getMemberById(@NotNull String memberId) {
        try {
            return getMemberById(Long.parseLong(memberId));
        } catch (Exception e) {
            return null;
        }
    }

    public @Nullable Member getMemberByMention(@NotNull String memberMention) {
        return getMemberById(Processing.parseUserMention(memberMention));
    }

    public @Nullable User getBannedUserById(long userId) {
        try {
            return guild.retrieveBanById(userId).complete().getUser();
        } catch (Exception e) {
            return null;
        }
    }

    public @Nullable User getBannedUserById(@NotNull String userId) {
        try {
            return guild.retrieveBanById(userId).complete().getUser();
        } catch (Exception e) {
            return null;
        }
    }

    public @Nullable User getBannedUserByTag(@NotNull String tag) {
        if (Checks.isUserTag(tag)) {
            try {
                return Processing.getUserBanByUserTag(getGuildBanList(), tag).getUser();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public boolean clearTextChannel(@NotNull String channelId) {
        TextChannel channel;
        try {
            channel = guild.getTextChannelById(channelId);
        } catch (Exception e) {
            return false;
        }
        if (channel != null) {
            return GuildUtils.clearTextChannel(channel);
        }
        return false;
    }

    public void banMember(@NotNull Member member, int delDays, @Nullable String reason, @Nullable Consumer<Void> success, @Nullable Consumer<? super Throwable> failure) {
        try {
            guild.ban(member.getUser(), delDays, reason).queue(success, failure);
        } catch (Exception e) {
            if (failure != null) {
                failure.accept(e);
            }
        }
    }

    public void banMember(@NotNull Member member, int delDays, @Nullable Consumer<Void> success, @Nullable Consumer<? super Throwable> failure) {
        banMember(member, delDays, null, success, failure);
    }

    public void banMember(@NotNull Member member, @Nullable String reason, @Nullable Consumer<Void> success, @Nullable Consumer<? super Throwable> failure) {
        banMember(member, Constants.MAX_DEL_DAYS, reason, success, failure);
    }

    public void banMember(@NotNull Member member, @Nullable Consumer<Void> success, @Nullable Consumer<? super Throwable> failure) {
        banMember(member, Constants.MAX_DEL_DAYS, null, success, failure);
    }

    public void banMember(@NotNull Member member, int delDays, @Nullable String reason) {
        banMember(member, delDays, reason, null, null);
    }

    public void banMember(@NotNull Member member, int delDays) {
        banMember(member, delDays, null);
    }

    public void banMember(@NotNull Member member, @Nullable String reason) {
        banMember(member, Constants.MAX_DEL_DAYS, reason);
    }

    public void banMember(@NotNull Member member) {
        banMember(member, Constants.MAX_DEL_DAYS, null);
    }

    public void unBanUser(@NotNull User user, @Nullable Consumer<Void> success, @Nullable Consumer<? super Throwable> failure) {
        try {
            guild.unban(user).queue(success, failure);
        } catch (Exception e) {
            if (failure != null) {
                failure.accept(e);
            }
        }
    }

    public void unBanUser(@NotNull User user) {
        unBanUser(user, null, null);
    }

    public void kickMember(@NotNull Member member, @NotNull String reason, @Nullable Consumer<Void> success, @Nullable Consumer<? super Throwable> failure) {
        try {
            guild.kick(member, reason).queue(success, failure);
        } catch (Exception e) {
            if (failure != null) {
                failure.accept(e);
            }
        }
    }

    public void kickMember(@NotNull Member member, @Nullable Consumer<Void> success, @Nullable Consumer<? super Throwable> failure) {
        kickMember(member, null, success, failure);
    }

    public void kickMember(@NotNull Member member, @NotNull String reason) {
        kickMember(member, reason, null, null);
    }

    public void kickMember(@NotNull Member member) {
        kickMember(member, null, null, null);
    }

    public void voiceMuteMember(@NotNull Member member, @Nullable Consumer<Void> success, @Nullable Consumer<? super Throwable> failure) {
        try {
            guild.mute(member, true).queue(success, failure);
        } catch (Exception e) {
            if (failure != null) {
                failure.accept(e);
            }
        }
    }

    public void voiceMuteMember(@NotNull Member member) {
        voiceMuteMember(member, null, null);
    }

    public void voiceUnMuteMember(@NotNull Member member, @Nullable Consumer<Void> success, @Nullable Consumer<? super Throwable> failure) {
        try {
            guild.mute(member, false).queue(success, failure);
        } catch (Exception e) {
            if (failure != null) {
                failure.accept(e);
            }
        }
    }

    public void voiceUnMuteMember(@NotNull Member member) {
        voiceUnMuteMember(member, null, null);
    }

    public void muteMember(@NotNull Member member, @NotNull Role muteRole, @Nullable Consumer<Void> success, @Nullable Consumer<? super Throwable> failure) {
        try {
            guild.addRoleToMember(member, muteRole).queue(success, failure);
        } catch (Exception e) {
            if (failure != null) {
                failure.accept(e);
            }
        }
    }

    public void muteMember(@NotNull Member member, @NotNull String roleId, @Nullable Consumer<Void> success, @Nullable Consumer<? super Throwable> failure) {
        Role muteRole;
        try {
            muteRole = guild.getRoleById(roleId);
        } catch (Exception e) {
            if (failure != null) {
                failure.accept(e);
            }
            return;
        }
        if (muteRole != null) {
            muteMember(member, muteRole, success, failure);
        }
    }

    public void muteMember(@NotNull Member member, @NotNull Role muteRole) {
        muteMember(member, muteRole, null, null);
    }

    public void muteMember(@NotNull Member member, @NotNull String roleId) {
        muteMember(member, roleId, null, null);
    }

    public void unMuteMember(@NotNull Member member, @NotNull Role muteRole, @Nullable Consumer<Void> success, @Nullable Consumer<? super Throwable> failure) {
        try {
            guild.removeRoleFromMember(member, muteRole).queue(success, failure);
        } catch (Exception e) {
            if (failure != null) {
                failure.accept(e);
            }
        }
    }

    public void unMuteMember(@NotNull Member member, @NotNull String roleId, @Nullable Consumer<Void> success, @Nullable Consumer<? super Throwable> failure) {
        Role muteRole;
        try {
            muteRole = guild.getRoleById(roleId);
        } catch (Exception e) {
            if (failure != null) {
                failure.accept(e);
            }
            return;
        }
        if (muteRole != null) {
            unMuteMember(member, muteRole, success, failure);
        }
    }

    public void unMuteMember(@NotNull Member member, @NotNull Role muteRole) {
        unMuteMember(member, muteRole, null, null);
    }

    public void unMuteMember(@NotNull Member member, @NotNull String roleId) {
        unMuteMember(member, roleId, null, null);
    }

    public boolean denyMemberToDoInTextChannels(@NotNull IPermissionHolder holder, @NotNull Permission... permissions) {
        return GuildUtils.denyMemberToDo(new ArrayList<>(guild.getTextChannels()), holder, permissions);
    }

    public boolean allowMemberToDoInTextChannels(@NotNull IPermissionHolder holder, @NotNull Permission... permissions) {
        return GuildUtils.allowMemberToDo(new ArrayList<>(guild.getTextChannels()), holder, permissions);
    }

    public boolean clearMemberPermissionInTextChannels(@NotNull IPermissionHolder holder, @NotNull Permission... permissions) {
        return GuildUtils.clearMemberPermission(new ArrayList<>(guild.getTextChannels()), holder, permissions);
    }

    public boolean resetMemberPermissionInTextChannels(@NotNull IPermissionHolder holder) {
        return GuildUtils.resetMemberPermission(new ArrayList<>(guild.getTextChannels()), holder);
    }

    public boolean denyMemberToDoInVoiceChannels(@NotNull IPermissionHolder holder, @NotNull Permission... permissions) {
        return GuildUtils.denyMemberToDo(new ArrayList<>(guild.getVoiceChannels()), holder, permissions);
    }

    public boolean allowMemberToDoInVoiceChannels(@NotNull IPermissionHolder holder, @NotNull Permission... permissions) {
        return GuildUtils.allowMemberToDo(new ArrayList<>(guild.getVoiceChannels()), holder, permissions);
    }

    public boolean clearMemberPermissionInVoiceChannels(@NotNull IPermissionHolder holder, @NotNull Permission... permissions) {
        return GuildUtils.clearMemberPermission(new ArrayList<>(guild.getVoiceChannels()), holder, permissions);
    }

    public boolean resetMemberPermissionInVoiceChannels(@NotNull IPermissionHolder holder) {
        return GuildUtils.resetMemberPermission(new ArrayList<>(guild.getVoiceChannels()), holder);
    }

    public boolean resetMemberPermissionOnGuild(@NotNull IPermissionHolder holder) {
        return GuildUtils.resetMemberPermission(guild.getChannels(), holder);
    }

    public boolean denyReadMessage(@NotNull IPermissionHolder holder) {
        return denyMemberToDoInTextChannels(holder, Permission.MESSAGE_READ);
    }

    public boolean allowReadMessage(@NotNull IPermissionHolder holder) {
        return clearMemberPermissionInTextChannels(holder, Permission.MESSAGE_READ);
    }
}
