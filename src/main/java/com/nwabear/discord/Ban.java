package com.nwabear.discord;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Ban extends Command {
    public Ban(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";ban <user(s)>: bans all users put into field";
    }

    @Override
    public void command() {
        // make sure that at least 1 user is mentioned
        if(!this.message.getMentionedUsers().isEmpty()) {
            for(Member memberToBan : this.message.getMentionedMembers()) {
                // make sure the bot has permission to ban the user
                if(guild.getSelfMember().canInteract(member) && guild.getSelfMember().hasPermission(Permission.BAN_MEMBERS)) {
                    // make sure the author has permission to ban the user
                    if(member.canInteract(memberToBan) && member.hasPermission(Permission.BAN_MEMBERS)) {
                        this.controller.ban(memberToBan, 0).queue();
                    } else {
                        event.getChannel().sendMessage("You do not have permission to ban " + memberToBan.getNickname()).queue();
                    }
                } else {
                    event.getChannel().sendMessage("I do not have permission to ban " + memberToBan.getNickname()).queue();
                }
            }
        } else {
            event.getChannel().sendMessage("You need to input one or more users to ban!").queue();
        }
    }
}
