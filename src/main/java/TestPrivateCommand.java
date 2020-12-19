import com.github.romanqed.DiscordMessageParser.AnnotationUtil.Annotations.Commands.Private;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.PrivateCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.Context;

@Private
public class TestPrivateCommand extends PrivateCommand {
    public TestPrivateCommand() {
        super("Ya");
    }

    @Override
    public void execute(Context context) {
        context.getJDAWrapper().reply("Hi from " + toString());
    }
}
