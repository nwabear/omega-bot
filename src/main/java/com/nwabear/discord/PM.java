package com.nwabear.discord;

import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class PM extends Command {
    public PM(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";\\|\\|pm\\|\\| <message>: sends a pm to target user";
    }

    @Override
    public void command() {
        String[] args = this.message.getContentRaw().substring(8).split(" ");
        User user = this.message.getMentionedUsers().get(0);
        PrivateChannel channel = user.openPrivateChannel().complete();
        String message = "";
        for (int i = 1; i < args.length; i++) {
            message += args[i] + " ";
        }
        channel.sendMessage(message).queue();
        this.message.delete().queue();
    }
}
