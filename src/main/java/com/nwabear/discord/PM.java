package com.nwabear.discord;

import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class PM {
    public PM(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().substring(8).split(" ");
        User user = event.getMessage().getMentionedUsers().get(0);
        PrivateChannel channel = user.openPrivateChannel().complete();
        String message = "";
        for (int i = 1; i < args.length; i++) {
            message += args[i] + " ";
        }
        channel.sendMessage(message).queue();
        event.getMessage().delete().queue();
    }
}
