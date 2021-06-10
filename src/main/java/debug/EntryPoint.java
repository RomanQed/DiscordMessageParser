package debug;

import com.github.romanqed.DiscordMessageParser.JDAListeners.Fabrics;
import com.github.romanqed.DiscordMessageParser.JDAListeners.JDAListenerFabric;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class EntryPoint {
    public static void main(String[] args) throws LoginException, InterruptedException {
        JDABuilder builder = JDABuilder.createDefault(System.getenv("BOT_TOKEN"));
        JDAListenerFabric fabric = Fabrics.newAsyncFabric();
        if (fabric == null) {
            System.err.println("Bot initialize error!");
            return;
        }
        builder
                .addEventListeners(
                        fabric.newPrivateReactionListener(),
                        fabric.newPrivateMessageListener(),
                        fabric.newGuildReactionListener(),
                        fabric.newGuildMessageListener()
                )
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .enableIntents(GatewayIntent.GUILD_PRESENCES);
        JDA bot = builder.build();
        bot.awaitReady();
        System.out.println("Bot started successfully!");
    }
}
