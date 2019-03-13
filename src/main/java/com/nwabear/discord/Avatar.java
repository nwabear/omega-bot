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
            User user = this.message.getMentionedUsers().get(0);
            this.channel.sendMessage(new EmbedBuilder().setImage(user.getAvatarUrl()).build()).queue();
        } catch(Exception e) {
            this.channel.sendMessage(new EmbedBuilder().setImage(this.user.getAvatarUrl()).build()).queue();
        }
    }
}
