package com.github.romanqed.AsyncBot;

import net.dv8tion.jda.api.JDABuilder;

public class AsyncBot {
    public static void main(String[] args) throws Exception {
        String token = "Your bot token";
        JDABuilder asyncBot = JDABuilder.createDefault(token);
        asyncBot.addEventListeners(new AsyncListenerAdapter());
        asyncBot.build();
    }
}
