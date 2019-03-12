package com.nwabear.discord;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Avatar {
    public Avatar(MessageReceivedEvent event) {
        try {
            User user = event.getMessage().getMentionedUsers().get(0);
            event.getChannel().sendMessage(new EmbedBuilder().setImage(user.getAvatarUrl()).build()).queue();
        } catch(Exception e) {
            event.getChannel().sendMessage(new EmbedBuilder().setImage(event.getAuthor().getAvatarUrl()).build()).queue();
        }
    }
}
