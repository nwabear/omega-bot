package com.nwabear.discord;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ReverseName extends Command {
    public ReverseName(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";reverseName <user(s)>: reverses the nickname of the users specified";
    }

    @Override
    public void command() {
        Member self = guild.getSelfMember();
        String name;

        if (!this.message.getMentionedUsers().isEmpty()) {
            if (!self.hasPermission(Permission.NICKNAME_MANAGE)) {
                this.channel.sendMessage("I don't have permission to change names!").queue();
            } else if (!guild.getMember(this.user).hasPermission(Permission.NICKNAME_MANAGE)) {
                this.channel.sendMessage("You don't have permission to use that command!").queue();
            } else {
                for (User user : this.message.getMentionedUsers()) {
                    if (guild.getMember(user).getNickname() != null) {
                        name = guild.getMember(user).getNickname();
                    } else {
                        name = user.getName();
                    }
                    if (self.hasPermission(Permission.NICKNAME_MANAGE)) {
                        try {
                            if(this.member.canInteract(guild.getMember(user))) {
                                this.controller.setNickname(guild.getMember(user), new StringBuilder(name).reverse().toString()).queue();
                            } else {
                                this.channel.sendMessage("You do not have permission to use this command!").queue();
                            }
                        } catch (Exception e) {
                            this.channel.sendMessage("I can't modify " + name + "'s name.").queue();
                        }
                    } else {
                        this.channel.sendMessage("I can't modify " + name + "'s name.").queue();
                    }
                }
            }
        }
    }
}
