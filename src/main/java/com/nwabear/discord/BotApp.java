package com.nwabear.discord;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class BotApp {
    public static void main(String[] args) throws Exception {
        JDA jda = new JDABuilder("NTUzNzI2NTM5OTU5NzYyOTQ1.D2mMfA.Pgay1kBCi69p3WE3MB43dhH498g")
                .addEventListener(new Listener())
                .build();

        jda.awaitReady();
    }
}
