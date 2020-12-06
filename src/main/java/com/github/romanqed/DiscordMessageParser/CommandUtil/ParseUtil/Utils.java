package com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil;

import com.github.romanqed.DiscordMessageParser.CommandUtil.Command;
import com.github.romanqed.DiscordMessageParser.CommandUtil.CommandType;
import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.RegexUtil.ParsePattern;
import net.dv8tion.jda.api.entities.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;

public class Utils {
    public static final String DEFAULT_COMMAND_PREFIX = "!";

    public static List<String> getCommandArguments(String rawArguments) {
        if (rawArguments == null) {
            return new ArrayList<>();
        }
        Matcher matcher = ParsePattern.BOT_ARGUMENTS.getPattern().matcher(rawArguments);
        List<String> list = new ArrayList<>();
        matcher.results().forEach(matchResult -> {
            String res = matchResult.group().replace("\"", "");
            list.add(res.isEmpty() ? " " : res);
        });
        return list;
    }

    public static String getKey(Command command) {
        if (command == null) {
            return "";
        }
        return command.getType().name() + command.getName();
    }

    public static String getKey(String command, CommandType commandType) {
        if (command == null || commandType == null) {
            return "";
        }
        return commandType.name() + command;
    }

    public static ParseResult parseCommand(String rawString, String commandPrefix) {
        rawString = Objects.requireNonNullElse(rawString, "");
        commandPrefix = Objects.requireNonNullElse(commandPrefix, "");
        if (!commandPrefix.isEmpty() && rawString.startsWith(commandPrefix)) {
            rawString += " ";
            int commandBodyEnd = rawString.indexOf(' ', commandPrefix.length());
            String commandBody = rawString.substring(commandPrefix.length(), commandBodyEnd);
            if (!commandBody.isEmpty()) {
                return new ParseResult(ResultEnum.SUCCESS, commandBody, rawString.substring(commandBodyEnd + 1));
            }
        }
        return new ParseResult(ResultEnum.ERROR);
    }

    public static boolean validateCommandRoles(Set<String> commandRoles, List<Role> memberRoles) {
        if (commandRoles.isEmpty()) {
            return true;
        }
        if (commandRoles.size() > memberRoles.size()) {
            return false;
        }
        return roleListToStringList(memberRoles).containsAll(commandRoles);
    }

    public static List<String> roleListToStringList(List<Role> roles) {
        List<String> convertedRoles = new ArrayList<>();
        roles.forEach(role -> {
            convertedRoles.add(role.getName());
        });
        return convertedRoles;
    }
}