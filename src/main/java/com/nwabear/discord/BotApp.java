package com.nwabear.discord;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class BotApp {
    public static void main(String[] args) throws Exception {
        // runs bot using bot token
        /*
         * *** Note ***
         *
         * when using the bot, replace
         * "new Token().getToken()"
         * with the token that you are given from the
         * discord developer portal
         */
        String token = new Token().getToken();

        // runs bot using bot token
        JDA jda = new JDABuilder(new Token().getToken())
                .addEventListener(new Listener())
                .build();

        jda.awaitReady();
    }
}
