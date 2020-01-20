package com.nwabear.discord;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;

public class Help {
    public Help(MessageReceivedEvent event) {
        if(event.getMessage().getContentRaw().length() == 5) {
            // if there was no command specified, output simple help list
            event.getChannel().sendMessage(
                    new EmbedBuilder().addField( new MessageEmbed.Field("Commands",
                    "Commands: \n" +
                            ";help\n" +
                            ";kick <user(s)>\n" +
                            ";ban <user(s)>\n" +
                            ";remind <hours> <minutes> <message>\n" +
                            ";wikipedia <query>\n" +
                            ";gimage <query>\n" +
                            ";join <voice channel>\n" +
                            ";leave\n" +
                            ";play <youtube link>\n" +
                            ";stop\n" +
                            ";avatar <user>\n" +
                            ";wavy\n" +
                            ";roll <bound>\n" +
                            ";roulette\n" +
                            ";flip\n" +
                            ";reverse <text>\n" +
                            ";leet <input>\n" +
                            ";echo <text>\n" +
                            ";idk/;f/;x\n\n" +
                            "For help with a specific command, type ;help <command>\n\n" +
                            "Bot Creator: Castillix\n" +
                            "Bot Source Code: https://github.com/nwabear/omega-bot"
                                    , true)
                    ).setColor(Color.RED).build()
            ).queue();
        } else {
            // if there was a command specified,
            // output the description of the requested command
            String[] args = event.getMessage().getContentRaw().substring(6).split(" ");
            String response;
            switch(args[0]) {
                case "help":
                    response = ";help <command>: displays list of commands, or information about a specific command";
                    break;

                case "kick":
                    response = new Kick(event).getDescription();
                    break;

                case "ban":
                    response = new Ban(event).getDescription();
                    break;

                case "remind":
                    response = new RemindStarter(event).getDescription();
                    break;

                case "wikipedia":
                    response = new Wikipedia(event).getDescription();
                    break;

                case "gimage":
                    response = new GImage(event).getDescription();
                    break;

                case "avatar":
                    response = new Avatar(event).getDescription();
                    break;

                case "roll":
                    response = new Roll(event).getDescription();
                    break;

                case "roulette":
                    response = new Roulette(event).getDescription();
                    break;

                case "flip":
                    response = new Flip(event).getDescription();
                    break;

                case "reverse":
                    response = new Reverse(event).getDescription();
                    break;

                case "wavy":
                    response = new Wavy(event).getDescription();
                    break;

                case "join":
                    response = new Join(event).getDescription();
                    break;

                case "leave":
                    response = new Leave(event).getDescription();
                    break;

                case "play":
                    response = ";play <youtube link>: plays youtube video in current voice channel";
                    break;

                case "stop":
                    response = ";stop: stops audio in current channel";
                    break;

                case "leet":
                    response = new Leet(event).getDescription();
                    break;

                case "echo":
                    response = new Echo(event).getDescription();
                    break;

                default:
                    response = "that command doesn't exist, please check your spelling and try again";
                    break;
            }
            // make the response colored for style
            event.getChannel().sendMessage(new EmbedBuilder().setTitle(response).setColor(Color.RED).build()).queue();
        }
    }
}