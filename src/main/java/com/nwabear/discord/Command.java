package com.nwabear.discord;

import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.GuildController;

public class Command {
    User user;
    Member member;
    MessageChannel channel;
    GuildController controller;
    Guild guild;
    Message message;
    MessageReceivedEvent event;
    String description;

    public Command(MessageReceivedEvent event) {
        this.event = event;
        this.message = event.getMessage();
        this.guild = event.getGuild();
        this.channel = event.getChannel();
        this.member = event.getMember();
        this.user = event.getAuthor();
        this.controller = guild.getController();
    }

    public void command() {
        this.channel.sendMessage("This command is broken, sorry for the inconvenience...").queue();
    }

    public String getDescription() {
        return this.description;
    }
}
