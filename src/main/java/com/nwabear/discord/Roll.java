package com.nwabear.discord;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Random;

public class Roll {
    public Roll(MessageReceivedEvent event) {
        Random rand = new Random();
        try {
            int range = Integer.parseInt(event.getMessage().getContentRaw().split(" ")[1]);
            event.getChannel().sendMessage((rand.nextInt(range) + 1) + "").queue();
        } catch (Exception e) {
            event.getChannel().sendMessage((rand.nextInt(100) + 1) + "").queue();
        }
    }
}
