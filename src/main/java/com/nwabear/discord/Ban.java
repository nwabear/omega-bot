package com.nwabear.discord;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Ban {
    public Ban(MessageReceivedEvent event) {
        Guild guild = event.getGuild();
        Member self = guild.getSelfMember();

        if (!event.getMessage().getMentionedUsers().isEmpty()) {
            if (!self.hasPermission(Permission.BAN_MEMBERS)) {
                event.getChannel().sendMessage("I don't have permission to ban members!").queue();
            } else if (!guild.getMember(event.getAuthor()).hasPermission(Permission.BAN_MEMBERS)) {
                event.getChannel().sendMessage("You don't have permission to use that command!").queue();
            } else {
                for (User user : event.getMessage().getMentionedUsers()) {
                    if (!self.canInteract(guild.getMember(user))) {
                        event.getChannel().sendMessage("I am unable to ban " + user.getName()).queue();
                    } else if(event.getMember().canInteract(guild.getMember(user))) {
                        guild.getController().ban(guild.getMember(user), 0).queue();
                    } else {
                        event.getChannel().sendMessage("You do not have permission to use this command!").queue();
                    }
                }
            }
        } else {
            event.getChannel().sendMessage("You need to input one or more users to ban!").queue();
        }
    }
}
