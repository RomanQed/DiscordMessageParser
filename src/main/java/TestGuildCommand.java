import com.github.romanqed.DiscordMessageParser.CommandUtil.AnnotationUtil.Annotations.Guild;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.GuildCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.Context;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EmojiEvent;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.LinkedEmojiEvent;

@Guild
public class TestGuildCommand extends GuildCommand {
    public TestGuildCommand() {
        //
    }

    @Override
    public void execute(Context context) {
        EmojiEvent event = new LinkedEmojiEvent(500, user -> {
            context.getJDAWrapper().sendMessage("Reactioned!");
        });
        context.getJDAWrapper().sendMessage("test", event);
    }
}
