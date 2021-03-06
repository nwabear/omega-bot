package com.nwabear.discord;

import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class Echo extends Command {
    public Echo(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";echo <input>: repeats whatever is inputted";
    }

    @Override
    public void run() {
        // print the message into terminal and back into the channel
        System.out.println(this.message.getContentRaw());
        this.channel.sendMessage(this.message.getContentRaw().substring(6)).queue();
    }
}
