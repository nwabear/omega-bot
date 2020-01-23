package com.nwabear.discord;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Reverse extends Command {
    public Reverse(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";reverse <input>: reverses inputted text";
    }

    @Override
    public void run() {
        // get the text after the command call and reverse it
        StringBuilder sb = new StringBuilder(this.message.getContentRaw().substring(9)).reverse();

        // print the reversed text
        this.channel.sendMessage(sb.toString()).queue();
    }
}
