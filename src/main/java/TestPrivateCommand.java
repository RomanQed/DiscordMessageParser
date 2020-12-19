import com.github.romanqed.DiscordMessageParser.AnnotationUtil.Annotations.Commands.Private;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.PrivateCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.Context;
import com.github.romanqed.DiscordMessageParser.JDAUtil.Utils.MessageUtils;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EmojiEvent;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.Events;
import net.dv8tion.jda.api.entities.Message;

@Private
public class TestPrivateCommand extends PrivateCommand {

    @Override
    public void execute(Context context) {
        Message sentMessage = context.getJDAWrapper().sendMessage("test");
        EmojiEvent event = Events.callsLimitEvent(5, ((reaction, user) -> {
            context.getJDAWrapper().sendMessage(user.toString());
        }));
        event.atFinal(() -> {
            sentMessage.editMessage("Я повесился!").queue();
        });
        MessageUtils.addEventToSentMessage(event, sentMessage);
    }
}
