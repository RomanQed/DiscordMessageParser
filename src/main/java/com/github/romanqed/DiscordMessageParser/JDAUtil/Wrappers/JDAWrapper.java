package com.github.romanqed.DiscordMessageParser.JDAUtil.Wrappers;

import com.github.romanqed.DiscordMessageParser.JDAUtil.Utils.*;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EmojiEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class JDAWrapper {
    protected final Message body;

    public JDAWrapper(Message body) {
        this.body = Objects.requireNonNull(body);
    }

    public User getAuthor() {
        return body.getAuthor();
    }

    public JDA getJDA() {
        return body.getJDA();
    }

    public Guild getGuild() {
        return body.getGuild();
    }

    public long getGuildIdLong() {
        try {
            return body.getGuild().getIdLong();
        } catch (Exception e) {
            return 0;
        }
    }

    public String getGuildId() {
        try {
            return body.getGuild().getId();
        } catch (Exception e) {
            return "0";
        }
    }

    public Message getMessage() {
        return body;
    }

    public MessageChannel getChannel() {
        return body.getChannel();
    }

    public Message sendMessage(Message message) {
        return MessageUtils.sendMessage(body.getChannel(), message);
    }

    public Message sendMessage(Message message, EmojiEvent event) {
        Message sentMessage = sendMessage(message);
        MessageUtils.addEventToSentMessage(event, sentMessage);
        return sentMessage;
    }

    public Message sendMessage(String message) {
        return MessageUtils.sendMessage(body.getChannel(), message);
    }

    public Message sendMessage(String message, EmojiEvent event) {
        Message sentMessage = sendMessage(message);
        MessageUtils.addEventToSentMessage(event, sentMessage);
        return sentMessage;
    }

    public Message reply(Message message) {
        return MessageUtils.replyToMessage(body, message);
    }

    public Message reply(Message message, EmojiEvent event) {
        Message sentMessage = reply(message);
        MessageUtils.addEventToSentMessage(event, message);
        return sentMessage;
    }

    public Message reply(String message) {
        return MessageUtils.replyToMessage(body, message);
    }

    public Message reply(String message, EmojiEvent event) {
        Message sentMessage = reply(message);
        MessageUtils.addEventToSentMessage(event, sentMessage);
        return sentMessage;
    }

    public User getUserById(long id) {
        try {
            return body.getJDA().retrieveUserById(id).complete();
        } catch (Exception e) {
            return null;
        }
    }

    public User getUserById(String id) {
        try {
            return body.getJDA().retrieveUserById(id).complete();
        } catch (Exception e) {
            return null;
        }
    }

    public User getUserByMention(String userMention) {
        try {
            return body.getJDA().retrieveUserById(Processing.parseUserMention(userMention)).complete();
        } catch (Exception e) {
            return null;
        }
    }

    public TextChannel getTextChannel() {
        return body.getTextChannel();
    }

    public void clearTextChannel() {
        GuildUtils.clearTextChannel(body.getTextChannel());
    }

    public List<Guild.Ban> getGuildBanList() {
        try {
            return body.getGuild().retrieveBanList().complete();
        } catch (Exception e) {
            return null;
        }
    }

    public Member getMemberById(long memberId) {
        try {
            return body.getGuild().retrieveMemberById(memberId).complete();
        } catch (Exception e) {
            return null;
        }
    }

    public Member getMemberById(String memberId) {
        try {
            return getMemberById(Long.parseLong(memberId));
        } catch (Exception e) {
            return null;
        }
    }

    public Member getMemberByMention(String memberMention) {
        return getMemberById(Processing.parseUserMention(memberMention));
    }

    public User getBannedUserById(long userId) {
        try {
            return body.getGuild().retrieveBanById(userId).complete().getUser();
        } catch (Exception e) {
            return null;
        }
    }

    public User getBannedUserById(String userId) {
        try {
            return body.getGuild().retrieveBanById(userId).complete().getUser();
        } catch (Exception e) {
            return null;
        }
    }

    public User getBannedUserByTag(String tag) {
        if (Checks.isUserTag(tag)) {
            try {
                return Processing.getUserBanByUserTag(getGuildBanList(), tag).getUser();
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public boolean clearTextChannel(String channelId) {
        TextChannel channel;
        try {
            channel = body.getGuild().getTextChannelById(channelId);
        } catch (Exception e) {
            return false;
        }
        if (channel != null) {
            return GuildUtils.clearTextChannel(channel);
        }
        return false;
    }

    public void banMember(Member member, int delDays, String reason, Consumer<Void> success, Consumer<? super Throwable> failure) {
        try {
            body.getGuild().ban(member.getUser(), delDays, reason).queue(success, failure);
        } catch (Exception e) {
            if (failure != null) {
                failure.accept(e);
            }
        }
    }

    public void banMember(Member member, int delDays, Consumer<Void> success, Consumer<? super Throwable> failure) {
        banMember(member, delDays, null, success, failure);
    }

    public void banMember(Member member, String reason, Consumer<Void> success, Consumer<? super Throwable> failure) {
        banMember(member, Constants.MAX_DEL_DAYS, reason, success, failure);
    }

    public void banMember(Member member, Consumer<Void> success, Consumer<? super Throwable> failure) {
        banMember(member, Constants.MAX_DEL_DAYS, null, success, failure);
    }

    public void banMember(Member member, int delDays, String reason) {
        banMember(member, delDays, reason, null, null);
    }

    public void banMember(Member member, int delDays) {
        banMember(member, delDays, null);
    }

    public void banMember(Member member, String reason) {
        banMember(member, Constants.MAX_DEL_DAYS, reason);
    }

    public void banMember(Member member) {
        banMember(member, Constants.MAX_DEL_DAYS, null);
    }

    public void unBanUser(User user, Consumer<Void> success, Consumer<? super Throwable> failure) {
        try {
            body.getGuild().unban(user).queue(success, failure);
        } catch (Exception e) {
            if (failure != null) {
                failure.accept(e);
            }
        }
    }

    public void unBanUser(User user) {
        unBanUser(user, null, null);
    }

    public void unBanUser(String tag, Consumer<Void> success, Consumer<? super Throwable> failure) {
        unBanUser(getBannedUserByTag(tag), success, failure);
    }

    public void kickMember(Member member, String reason, Consumer<Void> success, Consumer<? super Throwable> failure) {
        try {
            body.getGuild().kick(member, reason).queue(success, failure);
        } catch (Exception e) {
            if (failure != null) {
                failure.accept(e);
            }
        }
    }

    public void kickMember(Member member, Consumer<Void> success, Consumer<? super Throwable> failure) {
        kickMember(member, null, success, failure);
    }

    public void kickMember(Member member, String reason) {
        kickMember(member, reason, null, null);
    }

    public void kickMember(Member member) {
        kickMember(member, null, null, null);
    }

    public void voiceMuteMember(Member member, Consumer<Void> success, Consumer<? super Throwable> failure) {
        try {
            body.getGuild().mute(member, true).queue(success, failure);
        } catch (Exception e) {
            if (failure != null) {
                failure.accept(e);
            }
        }
    }

    public void voiceMuteMember(Member member) {
        voiceMuteMember(member, null, null);
    }

    public void voiceUnMuteMember(Member member, Consumer<Void> success, Consumer<? super Throwable> failure) {
        try {
            body.getGuild().mute(member, false).queue(success, failure);
        } catch (Exception e) {
            if (failure != null) {
                failure.accept(e);
            }
        }
    }

    public void voiceUnMuteMember(Member member) {
        voiceUnMuteMember(member, null, null);
    }

    public void muteMember(Member member, Role muteRole, Consumer<Void> success, Consumer<? super Throwable> failure) {
        try {
            body.getGuild().addRoleToMember(member, muteRole).queue(success, failure);
        } catch (Exception e) {
            if (failure != null) {
                failure.accept(e);
            }
        }
    }

    public void muteMember(Member member, String roleId, Consumer<Void> success, Consumer<? super Throwable> failure) {
        Role muteRole;
        try {
            muteRole = body.getGuild().getRoleById(roleId);
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

    public void muteMember(Member member, Role muteRole) {
        muteMember(member, muteRole, null, null);
    }

    public void muteMember(Member member, String roleId) {
        muteMember(member, roleId, null, null);
    }

    public void unMuteMember(Member member, Role muteRole, Consumer<Void> success, Consumer<? super Throwable> failure) {
        try {
            body.getGuild().removeRoleFromMember(member, muteRole).queue(success, failure);
        } catch (Exception e) {
            if (failure != null) {
                failure.accept(e);
            }
        }
    }

    public void unMuteMember(Member member, String roleId, Consumer<Void> success, Consumer<? super Throwable> failure) {
        Role muteRole;
        try {
            muteRole = body.getGuild().getRoleById(roleId);
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

    public void unMuteMember(Member member, Role muteRole) {
        unMuteMember(member, muteRole, null, null);
    }

    public void unMuteMember(Member member, String roleId) {
        unMuteMember(member, roleId, null, null);
    }
}
