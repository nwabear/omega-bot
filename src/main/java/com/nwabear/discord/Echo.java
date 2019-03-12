package com.nwabear.discord;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Echo {
    public Echo(MessageReceivedEvent event) {
        System.out.println(event.getMessage().getContentRaw());
        event.getChannel().sendMessage(event.getMessage().getContentRaw().substring(6)).queue();
    }
}
