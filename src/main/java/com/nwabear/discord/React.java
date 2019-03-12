package com.nwabear.discord;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class React {
    public React(MessageReceivedEvent event, String type) {
        switch(type) {
            case "idk":
                event.getChannel().sendMessage("¯\\\\\\_(ツ)\\_/¯").queue();
                break;

            case "x":
                event.getChannel().sendMessage("doubt").queue();
                break;

            case "f":
                event.getChannel().sendMessage("F").queue();
                break;
        }
    }
}
