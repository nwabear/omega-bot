package com.nwabear.discord;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Avatar extends Command {
    public Avatar(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";avatar <user>: displays the avatar for a user, or will display your avatar if paramaters are left blank";
    }

    @Override
    public void command() {
        try {
            // get the first mentioned user and return their avatar
            User user = this.message.getMentionedUsers().get(0);
            this.channel.sendMessage(new EmbedBuilder().setImage(user.getEffectiveAvatarUrl()).build()).queue();
        } catch(Exception e) {
            // if no user was mentioned, return the authors avatar
            this.channel.sendMessage(new EmbedBuilder().setImage(this.user.getAvatarUrl()).build()).queue();
        }
    }
}
