package com.nwabear.discord;

import net.dv8tion.jda.client.entities.Application;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;

public class Listener extends ListenerAdapter {
    private ArrayList<BotAudioManager> audioManagers = new ArrayList<>();
    private ArrayList<String> keys = new ArrayList<>();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getGuild() != null) {
            if (!this.keys.contains(event.getGuild().getId())) {
                this.keys.add(event.getGuild().getId());
                BotAudioManager temp = new BotAudioManager(event.getGuild());
                new Thread(temp).start();
                this.audioManagers.add(temp);
            }
        }
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
                new Thread(new Kick(event)).start();
                break;
            }

            case "ban": {
                new Thread(new Ban(event)).start();
                break;
            }

            case "roll": {
                new Thread(new Roll(event)).start();
                break;
            }

            case "flip": {
                new Thread(new Flip(event)).start();
                break;
            }

            case "roulette": {
                new Thread(new Roulette(event)).start();
                break;
            }

            case "reverse": {
                new Thread(new Reverse(event)).start();
                break;
            }

            case "help": {
                new Help(event);
                break;
            }

            case "echo": {
                new Thread(new Echo(event)).start();
                break;
            }

            case "leet": {
                new Thread(new Leet(event)).start();
                break;
            }

            case "chance": {
                new Thread(new Chance(event)).start();
                break;
            }

            case "remind": {
                new Thread(new RemindStarter(event)).start();
                break;
            }

            case "wikipedia": {
                new Thread(new Wikipedia(event)).start();
                break;
            }

            case "gimage": {
                new Thread(new GImage(event)).start();
                break;
            }

            case "avatar": {
                new Thread(new Avatar(event)).start();
                break;
            }

            case "wavy": {
                new Thread(new Wavy(event)).start();
                break;
            }

            case "join": {
                new Thread(new Join(event)).start();
                break;
            }

            case "leave": {
                new Thread(new Leave(event)).start();
                break;
            }

            case "play": {
                this.audioManagers.get(this.keys.indexOf(event.getGuild().getId())).play(event);
                break;
            }

            case "stop": {
                this.audioManagers.get(this.keys.indexOf(event.getGuild().getId())).stop();
                break;
            }

            case "skip": {
                this.audioManagers.get(this.keys.indexOf(event.getGuild().getId())).skip();
                break;
            }

            case "resume": {
                this.audioManagers.get(this.keys.indexOf(event.getGuild().getId())).resume();
                break;
            }

            case "clear": {
                this.audioManagers.get(this.keys.indexOf(event.getGuild().getId())).clear();
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