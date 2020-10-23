package com.github.romanqed.SyncBot;

import net.dv8tion.jda.api.JDABuilder;

public class SyncBot {
    public static void main(String[] args) throws Exception{
        String token = "Your bot token";
        JDABuilder asyncBot = JDABuilder.createDefault(token);
        asyncBot.addEventListeners(new SyncListenerAdapter());
        asyncBot.build();
    }
}
