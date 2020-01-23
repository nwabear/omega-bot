package com.nwabear.discord;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Random;

public class Flip extends Command {
    public Flip(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";flip: flips a coin";
    }

    @Override
    public void run() {
        Random rand = new Random();
        try {
            // create a random heads or tails number and print it
            this.channel.sendMessage((rand.nextBoolean()) ? "heads" : "tails").queue();
        } catch (Exception e) {
            // if the range was left blank, print a number between 1 and 100
            this.channel.sendMessage((rand.nextInt(100) + 1) + "").queue();
        }
    }
}
