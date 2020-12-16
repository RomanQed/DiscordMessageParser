import com.github.romanqed.DiscordMessageParser.CommandUtil.AnnotationUtil.Annotations.Guild;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.GuildCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.Context;

@Guild
public class TestGuildCommand extends GuildCommand {
    public TestGuildCommand() {
        //addRoles("rofl!");
    }

    @Override
    public void execute(Context context) {
        context.getJDAWrapper().reply(context.getArgumentParser().parseArguments().toString());
    }
}
