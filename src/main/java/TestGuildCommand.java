import com.github.romanqed.DiscordMessageParser.CommandUtil.AnnotationUtil.Annotations.Guild;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.GuildCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.Context;
import com.github.romanqed.DiscordMessageParser.JDAUtil.Utils.MessageUtils;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EmojiEvent;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.Events;
import net.dv8tion.jda.api.entities.Message;

@Guild
public class TestGuildCommand extends GuildCommand {
    public TestGuildCommand() {
        //
    }

    @Override
    public void execute(Context context) {
        Message sentMessage = context.getJDAWrapper().sendMessage("test");
        EmojiEvent event = Events.newEvent(user -> {
            sentMessage.editMessage(user.toString()).queue();
        });
        MessageUtils.addEventToSentMessage(event, sentMessage);
    }
}
