package com.nwabear.discord;

import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.GuildController;
import net.dv8tion.jda.core.requests.Request;
import net.dv8tion.jda.core.requests.Response;
import net.dv8tion.jda.core.requests.RestAction;

import javax.annotation.meta.Exclusive;

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
        // declare useful variables for commands
        this.event = event;
        this.message = event.getMessage();
        this.guild = event.getGuild();
        this.channel = event.getChannel();
        this.member = event.getMember();
        this.user = event.getAuthor();
        this.controller = guild.getController();
    }

    public void command() {
        // if this method was not overridden, print that the command is broken
        this.channel.sendMessage("This command is broken, sorry for the inconvenience...").queue();
    }

    public String getDescription() {
        return this.description;
    }
}
