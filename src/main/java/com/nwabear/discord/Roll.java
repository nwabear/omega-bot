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
    public void command() {
        Random rand = new Random();
        try {
            int range = Integer.parseInt(this.message.getContentRaw().split(" ")[1]);
            this.channel.sendMessage((rand.nextInt(range) + 1) + "").queue();
        } catch (Exception e) {
            this.channel.sendMessage((rand.nextInt(100) + 1) + "").queue();
        }
    }
}
