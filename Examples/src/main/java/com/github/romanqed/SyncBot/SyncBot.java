package com.github.romanqed.SyncBot;

import net.dv8tion.jda.api.JDABuilder;

public class SyncBot {
    public static void main(String[] args) throws Exception {
        String token = "Your bot token";
        JDABuilder syncBot = JDABuilder.createDefault(token);
        syncBot.addEventListeners(new SyncListenerAdapter());
        syncBot.build();
    }
}
