package com.nwabear.discord;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ReverseName {
    public ReverseName(MessageReceivedEvent event) {
        Guild guild = event.getGuild();
        Member self = guild.getSelfMember();
        String name;

        if (!event.getMessage().getMentionedUsers().isEmpty()) {
            if (!self.hasPermission(Permission.NICKNAME_MANAGE)) {
                event.getChannel().sendMessage("I don't have permission to change names!").queue();
            } else if (!guild.getMember(event.getAuthor()).hasPermission(Permission.NICKNAME_MANAGE)) {
                event.getChannel().sendMessage("You don't have permission to use that command!").queue();
            } else {
                for (User user : event.getMessage().getMentionedUsers()) {
                    if (guild.getMember(user).getNickname() != null) {
                        name = guild.getMember(user).getNickname();
                    } else {
                        name = user.getName();
                    }
                    if (self.hasPermission(Permission.NICKNAME_MANAGE)) {
                        try {
                            if(event.getMember().canInteract(guild.getMember(user))) {
                                guild.getController().setNickname(guild.getMember(user), new StringBuilder(name).reverse().toString()).queue();
                            } else {
                                event.getChannel().sendMessage("You do not have permission to use this command!").queue();
                            }
                        } catch (Exception e) {
                            event.getChannel().sendMessage("I can't modify " + name + "'s name.").queue();
                        }
                    } else {
                        event.getChannel().sendMessage("I can't modify " + name + "'s name.").queue();
                    }
                }
            }
        }
    }
}
