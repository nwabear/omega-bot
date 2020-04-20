package com.nwabear.discord;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import java.util.List;

public class BotApp {
    private static JDA jda;

    public static void main(String[] args) throws Exception {
        // runs bot using bot token
        /*
         * *** Note ***
         *
         * when using the bot, either replace
         * args[0] with the token string, or
         * pass the token as the arguments
         */

        // runs bot using bot token
        Listener listener = new Listener();
        jda = new JDABuilder(args[0])
                .addEventListener(listener)
                .build();
        
        jda.awaitReady();

        // sleep for 10s to allow time for webapp to initialize
        Thread.sleep(10000);
        listener.startup();
    }

    public static JDA getJda() {
        return jda;
    }
}
