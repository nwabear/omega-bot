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

        if(!event.getMessage().getMentionedUsers().isEmpty()) {
            for(Member member : event.getMessage().getMentionedMembers()) {
                if(self.canInteract(member) && self.hasPermission(Permission.BAN_MEMBERS)) {
                    if(event.getMember().canInteract(member) && event.getMember().hasPermission(Permission.BAN_MEMBERS)) {
                        guild.getController().ban(member, 0).queue();
                    } else {
                        event.getChannel().sendMessage("You do not have permission to ban " + member.getNickname()).queue();
                    }
                } else {
                    event.getChannel().sendMessage("I do not have permission to ban " + member.getNickname()).queue();
                }
            }
        } else {
            event.getChannel().sendMessage("You need to input one or more users to ban!").queue();
        }
    }
}
