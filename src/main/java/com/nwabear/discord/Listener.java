package com.nwabear.discord;

import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Listener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // when a message is recieved, if it is a bot, don't respond
        if(!event.getAuthor().isBot()) {
            // if the guild is null, meaning it is in a PM
            // noify the user not to send messages that way
            if(event.getGuild() != null) {
                // if the first character of the message is a semicolon, run the commands
                if (event.getMessage().getContentRaw().charAt(0) == ';') {
                    // print to the terminal the user and the command they are running
                    System.out.println(event.getAuthor().getName() + ": " + event.getMessage().getContentDisplay());

                    // call the command method do run the cooresponding command
                    this.command(event);
                }
            } else {
                event.getChannel().sendMessage("Please do not sent private messages, instead join a server with me on it.").queue();
            }
        }
    }

    private void command(MessageReceivedEvent event) {
        // get the string for the command that the user is calling
        String command = getCommand(event).toLowerCase();

        switch (command) {
            case "kick": {
                new Kick(event).command();
                break;
            }

            case "ban": {
                new Ban(event).command();
                break;
            }

            case "roll": {
                new Roll(event).command();
                break;
            }

            case "reverse": {
                new Reverse(event).command();
                break;
            }

            case "reverseName": {
                new ReverseName(event).command();
                break;
            }

            case "help": {
                new Help(event);
                break;
            }

            case "echo": {
                new Echo(event).command();
                break;
            }

            case "leet": {
                new Leet(event).command();
                break;
            }

            case "||pm||": {
                new PM(event).command();
                break;
            }

            case "remind": {
                new RemindStarter(event).command();
                break;
            }

            case "solve": {
                new Solve(event).command();
                break;
            }

            case "wikipedia": {
                new Wikipedia(event).command();
                break;
            }

            case "gimage": {
                new GImage(event).commandTime(0);
                break;
            }

            case "translate": {
                new Translate(event).command();
                break;
            }

            case "japanese": {
                new Japanese(event).command();
                break;
            }

            case "avatar": {
                new Avatar(event).command();
                break;
            }

            case "idk": {
                new React(event, "idk");
                break;
            }

            case "x": {
                new React(event, "x");
                break;
            }

            case "f": {
                new React(event, "f");
                break;
            }
        }
    }

    private String getCommand(MessageReceivedEvent event) {
        // returns the name of the command called
        return event.getMessage().getContentRaw().split(" ")[0].substring(1);
    }
}