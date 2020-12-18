import com.github.romanqed.DiscordMessageParser.AnnotationUtil.Annotations.Commands.Guild;
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
        EmojiEvent event = Events.callsLimitEvent(5, user -> {
            sentMessage.editMessage(user.toString()).queue();
        });
        event.atFinal(() -> {
            sentMessage.editMessage("Я повесился!").queue();
        });
        MessageUtils.addEventToSentMessage(event, sentMessage);
    }
}
