package debug;

import com.github.romanqed.DiscordMessageParser.AnnotationUtil.Annotations.Commands.GuildCommandClass;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.GuildCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.ParseUtil.ArgumentParser;
import com.github.romanqed.DiscordMessageParser.JDAUtil.Wrappers.JDAWrapper;
import com.github.romanqed.DiscordMessageParser.RegexUtil.Patterns;

import java.util.List;

@GuildCommandClass
public class Say extends GuildCommand {
    public Say() {
        super("say");
    }

    @Override
    public void execute(JDAWrapper wrapper, String arguments) {
        ArgumentParser parser = new ArgumentParser(arguments);
        List<String> result = parser.parseArguments(Patterns.STRING, Patterns.NUMBER);
        if (result.isEmpty()) {
            wrapper.sendMessage("у тебя аргументы палёные, хаххаха!)0))");
            return;
        }
        wrapper.reply(result.get(0).repeat(Integer.parseInt(result.get(1))));
    }
}
