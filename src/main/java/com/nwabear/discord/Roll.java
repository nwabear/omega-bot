package com.nwabear.discord;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Random;

public class Roll extends Command {
    public Roll(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";roll <bound>: rolls a number between 1 and the bound, bound is automatically 100 if left blank";
    }

    @Override
    public void run() {
        Random rand = new Random();
        try {
            // create a random number and print it to the channel
            int range = Integer.parseInt(this.message.getContentRaw().split(" ")[1]);
            this.channel.sendMessage((rand.nextInt(range) + 1) + "").queue();
        } catch (Exception e) {
            // if the range was left blank, print a number between 1 and 100
            this.channel.sendMessage((rand.nextInt(100) + 1) + "").queue();
        }
    }
}
