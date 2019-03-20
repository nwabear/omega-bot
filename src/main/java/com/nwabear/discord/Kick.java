package com.nwabear.discord;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Kick extends Command {
    public Kick(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";kick <user(s)>: kicks all users put into field";
    }
    
    @Override
    public void command() {
        // make sure that at least 1 user is mentioned
        if(!this.message.getMentionedUsers().isEmpty()) {
            for(Member memberToKick : this.message.getMentionedMembers()) {
                // make sure the bot has permission to kick the user
                if(guild.getSelfMember().canInteract(member) && guild.getSelfMember().hasPermission(Permission.KICK_MEMBERS)) {
                    // make sure the author has permission to kick the user
                    if(member.canInteract(memberToKick) && member.hasPermission(Permission.KICK_MEMBERS)) {
                        this.controller.kick(memberToKick).queue();
                    } else {
                        event.getChannel().sendMessage("You do not have permission to kick " + memberToKick.getNickname()).queue();
                    }
                } else {
                    event.getChannel().sendMessage("I do not have permission to kick " + memberToKick.getNickname()).queue();
                }
            }
        } else {
            event.getChannel().sendMessage("You need to input one or more users to kick!").queue();
        }
    }
}
