import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Bot {
    public static void main(String[] args) throws Exception {
        String token = "NjkwMTgwMzc4MzQ2MTI3NDcy.XnNqkQ.c4ZwxdJb-ZRz4jPD5eLc-mWW6pk";
        JDABuilder bot = JDABuilder.createDefault(token);
        bot.addEventListeners(new CustomListenerAdapter());
        bot.build();
    }
}
