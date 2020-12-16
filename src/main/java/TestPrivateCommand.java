import com.github.romanqed.DiscordMessageParser.CommandUtil.AnnotationUtil.Annotations.Private;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.PrivateCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.Context;

@Private
public class TestPrivateCommand extends PrivateCommand {
    @Override
    public void execute(Context context) {
        context.getJDAWrapper().reply("Hi from " + toString());
    }
}
