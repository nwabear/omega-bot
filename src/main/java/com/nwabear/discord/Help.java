package com.nwabear.discord;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Help {
    public Help(MessageReceivedEvent event) {
        event.getChannel().sendMessage(
                "Commands: \n" +
                        ";help: display help\n" +
                        ";kick <user(s)>: kicks 1 or more users\n" +
                        ";ban <user(s)>: bans 1 or more users\n" +
                        ";remind <hours> <minutes> <message>: reminds you after ammount of time specified\n" +
                        ";solve <expression>: solves a math equation\n" +
                        ";wiki <query>: provides a wikipedia link to the given search\n" +
                        ";avatar <user>: displays the avatar for a user, will display your avatar if no user is tagged\n" +
                        ";roll <bound>: rolls a number between 1 and the bound, or a number between 1 and 100\n" +
                        ";reverse <text>: reverses text\n" +
                        ";reverseName <user(s)>: reverses the name of 1 or more0 users\n" +
                        ";leet <input>: speak in l33t\n" +
                        ";rps <r, p, s>: play rock paper scissors\n" +
                        ";echo <text>: repeats any text inputted after the command\n" +
                        ";idk/;f/;x: reaction commands"
        ).queue();
    }
}