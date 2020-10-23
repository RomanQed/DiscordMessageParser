package com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil;

import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.GuildCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Command;
import com.github.romanqed.DiscordMessageParser.CommandUtil.CommandType;
import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.RegexUtil.ParsePattern;
import net.dv8tion.jda.api.entities.Role;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.stream.Stream;

public class Utils {
    public static final String DEFAULT_COMMAND_PREFIX = "!";

    public static @NotNull List<String> getCommandArguments(@NotNull String rawArguments) {
        Matcher matcher = ParsePattern.BOT_ARGUMENTS.getPattern().matcher(rawArguments);
        List<String> list = new ArrayList<>();
        matcher.results().forEach(matchResult -> {
            String res = matchResult.group().replace("\"", "");
            list.add(res.isEmpty() ? " " : res);
        });
        return list;
    }

    public static @NotNull String getKey(@NotNull Command command) {
        return command.getType().name() + command.getName();
    }

    public static @NotNull String getKey(@NotNull String command, @NotNull CommandType commandType) {
        return commandType.name() + command;
    }

    public static @NotNull ParseResult parseCommand(@NotNull String rawString, @NotNull String commandPrefix) {
        rawString = Objects.requireNonNullElse(rawString, "");
        commandPrefix = Objects.requireNonNullElse(commandPrefix, "");
        rawString += " ";
        if (!commandPrefix.isEmpty() && rawString.startsWith(commandPrefix)) {
            int commandBodyEnd = rawString.indexOf(' ', commandPrefix.length());
            String commandBody = rawString.substring(commandPrefix.length(), commandBodyEnd);
            if (!commandBody.isEmpty()) {
                return new ParseResult(ResultEnum.SUCCESS, commandBody, rawString.substring(commandBodyEnd + 1));
            }
        }
        return new ParseResult(ResultEnum.ERROR);
    }

    public static boolean validateCommandRoles(Set<String> commandRoles, List<Role> memberRoles){
        if (commandRoles.isEmpty()) {
            return true;
        }
        if (commandRoles.size() > memberRoles.size()){
            return false;
        }
        return roleListToStringList(memberRoles).containsAll(commandRoles);
    }

    public static List<String> roleListToStringList(List<Role> roles){
        List<String> convertedRoles = new ArrayList<>();
        roles.forEach(role -> {
            convertedRoles.add(role.getName());
        });
        return convertedRoles;
    }
}
