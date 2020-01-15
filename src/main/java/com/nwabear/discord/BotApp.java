package com.nwabear.discord;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class BotApp {
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
        JDA jda = new JDABuilder("NTUzNzI2NTM5OTU5NzYyOTQ1.Xh9LAA.rWnyqdrFjdAfmAi5wGx_niavq1s")
                .addEventListener(new Listener())
                .build();

        jda.awaitReady();
    }
}
