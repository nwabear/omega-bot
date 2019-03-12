package com.nwabear.discord;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Kick {
    public Kick(MessageReceivedEvent event) {
        Guild guild = event.getGuild();
        Member self = guild.getSelfMember();

        if(!event.getMessage().getMentionedUsers().isEmpty()) {
            for(Member member : event.getMessage().getMentionedMembers()) {
                if(self.canInteract(member) && self.hasPermission(Permission.KICK_MEMBERS)) {
                    if(event.getMember().canInteract(member) && event.getMember().hasPermission(Permission.KICK_MEMBERS)) {
                        guild.getController().kick(member).queue();
                    } else {
                        event.getChannel().sendMessage("You do not have permission to kick " + member.getNickname()).queue();
                    }
                } else {
                    event.getChannel().sendMessage("I do not have permission to kick " + member.getNickname()).queue();
                }
            }
        } else {
            event.getChannel().sendMessage("You need to input one or more users to kick!").queue();
        }
    }
}
