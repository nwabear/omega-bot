package com.nwabear.discord;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Random;

public class Chance extends Command {
    public Chance(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";chance: rolls a number between 1 and the 100";
    }

    @Override
    public void run() {
        Random rand = new Random();
        // create a random number and print it to the channel
        this.channel.sendMessage((rand.nextInt(101)) + "").queue();
    }
}
