package com.nwabear.discord;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Kick {
    public Kick(MessageReceivedEvent event) {
        Guild guild = event.getGuild();
        Member self = guild.getSelfMember();

        if (!event.getMessage().getMentionedUsers().isEmpty()) {
            if (!self.hasPermission(Permission.KICK_MEMBERS)) {
                event.getChannel().sendMessage("I don't have permission to kick members!").queue();
            } else if (!guild.getMember(event.getAuthor()).hasPermission(Permission.KICK_MEMBERS)) {
                event.getChannel().sendMessage("You don't have permission to use that command!").queue();
            } else {
                for (User user : event.getMessage().getMentionedUsers()) {
                    if (!self.canInteract(guild.getMember(user))) {
                        event.getChannel().sendMessage("I am unable to kick " + user.getName()).queue();
                    } else if(event.getMember().canInteract(guild.getMember(user))) {
                        guild.getController().kick(guild.getMember(user)).queue();
                    } else {
                        event.getChannel().sendMessage("You do not have permission to use this command!").queue();
                    }
                }
            }
        } else {
            event.getChannel().sendMessage("You need to input one or more users to kick!").queue();
        }
    }
}
