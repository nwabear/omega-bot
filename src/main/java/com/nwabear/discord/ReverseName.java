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

        // make sure that 1 or more users were inputted
        if (!this.message.getMentionedUsers().isEmpty()) {
            // make sure that the bot can change the users name
            if (!self.hasPermission(Permission.NICKNAME_MANAGE)) {
                this.channel.sendMessage("I don't have permission to change names!").queue();
            // make sure that the author can change the users name
            } else if (!guild.getMember(this.user).hasPermission(Permission.NICKNAME_MANAGE)) {
                this.channel.sendMessage("You don't have permission to use that command!").queue();
            } else {
                for (User user : this.message.getMentionedUsers()) {
                    // set the name to the users nickname
                    // if there is no nickname, set it to the users default name
                    if (guild.getMember(user).getNickname() != null) {
                        name = guild.getMember(user).getNickname();
                    } else {
                        name = user.getName();
                    }
                    try {
                        // make sure that the author can interact with the user
                        if(this.member.canInteract(guild.getMember(user))) {
                            // set the users name to the stored name backwards
                            this.controller.setNickname(guild.getMember(user), new StringBuilder(name).reverse().toString()).queue();
                        } else {
                            this.channel.sendMessage("You do not have permission to use this command!").queue();
                        }
                    } catch (Exception e) {
                        this.channel.sendMessage("I can't modify " + name + "'s name.").queue();
                    }
                }
            }
        }
    }
}
