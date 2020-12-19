package com.github.romanqed;

import com.github.romanqed.DiscordMessageParser.JDAListeners.Fabrics;
import com.github.romanqed.DiscordMessageParser.JDAListeners.JDAListenerFabric;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Bot {
    public static void main(String[] args) throws Exception {
        String token = "your token";
        JDABuilder bot = JDABuilder.createDefault(token);
        JDAListenerFabric fabric = Fabrics.newAsyncFabric();
        if (fabric == null) {
            System.err.println("Bot initialize error!");
            return;
        }
        bot.addEventListeners(fabric.newGuildMessageListener(), fabric.newGuildReactionListener(),
                fabric.newPrivateMessageListener(), fabric.newPrivateReactionListener());
        JDA jda = bot.build();
        jda.awaitReady();
        System.out.println("Bot started successfully!");
    }
}
