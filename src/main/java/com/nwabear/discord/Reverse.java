package com.nwabear.discord;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Reverse {
    public Reverse(MessageReceivedEvent event) {
        StringBuilder sb = new StringBuilder(event.getMessage().getContentRaw().substring(9)).reverse();

        event.getChannel().sendMessage(sb.toString()).queue();
    }
}
