package com.nwabear.discord;

import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/*
 * !!!WARNING!!!
 *
 * This is a secret command, and could
 * be dangerous if everyone knew about it
 *
 * If you are seeing this code,
 * please refrain from using this
 * command often, and do not tell
 * anyone else that this command exists
 *
 * Thanks!
 *  - Castillix
 */

public class PM extends Command {
    public PM(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";\\|\\|pm\\|\\| <message>: sends a pm to target user";
    }

    @Override
    public void command() {
        // get user and message to pm
        String[] args = this.message.getContentRaw().substring(8).split(" ");
        User user = this.message.getMentionedUsers().get(0);
        PrivateChannel channel = user.openPrivateChannel().complete();
        String message = "";
        // add message to the pm
        for (int i = 1; i < args.length; i++) {
            message += args[i] + " ";
        }
        // send a pm to the user specified
        channel.sendMessage(message).queue();
        this.message.delete().queue();
    }
}
