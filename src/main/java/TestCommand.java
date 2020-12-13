import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.BotRuntimeVariables.VariableList;
import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.GuildCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.GuildCommandEvent;
import com.github.romanqed.DiscordMessageParser.CommandUtil.BotCommand.ServiceAnnotation.BotCommand;

@BotCommand
public class TestCommand extends GuildCommand {
    public TestCommand() {
        super("test");
    }

    @Override
    public void execute(GuildCommandEvent event, VariableList variableList) {
        event.getContext().sendMessage("Shutka)");
    }
}
