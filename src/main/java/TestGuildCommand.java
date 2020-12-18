import com.github.romanqed.DiscordMessageParser.CommandUtil.AnnotationUtil.Annotations.Guild;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Commands.GuildCommand;
import com.github.romanqed.DiscordMessageParser.CommandUtil.Contexts.Context;
import com.github.romanqed.DiscordMessageParser.JDAUtil.Utils.MessageUtils;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.EmojiEvent;
import com.github.romanqed.DiscordMessageParser.ReactionUtil.LinkedEmojiEvent;
import net.dv8tion.jda.api.entities.Message;

@Guild
public class TestGuildCommand extends GuildCommand {
    public TestGuildCommand() {
    }

    @Override
    public void execute(Context context) {
        Message message = context.getJDAWrapper().sendMessage("Самое обычное сообщение");
        EmojiEvent event = new LinkedEmojiEvent(60000, 5, user -> context.getJDAWrapper().sendMessage(user.toString()));
        event.atFinal(() -> {
            context.getJDAWrapper().sendMessage("Я умираю!");
            message.delete().complete();
        });
        MessageUtils.addEventToSentMessage(event, message);
    }
}
