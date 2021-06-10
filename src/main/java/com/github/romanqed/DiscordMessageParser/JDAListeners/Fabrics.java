package com.github.romanqed.DiscordMessageParser.JDAListeners;

public class Fabrics {
    public static JDAListenerFabric newAsyncFabric() {
        try {
            return new JDAListenerAsyncFabric();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
