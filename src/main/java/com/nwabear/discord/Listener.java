package com.nwabear.discord;

import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Listener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(!event.getAuthor().isBot()) {
            if(event.getGuild() != null) {
                if (event.getMessage().getContentRaw().charAt(0) == ';') {
                    System.out.println(event.getAuthor().getName() + ": " + event.getMessage().getContentDisplay());
                    this.command(event);
                }
            } else {
                event.getChannel().sendMessage("Please do not sent private messages, instead join a server with me on it.").queue();
            }
        }
    }

    private void command(MessageReceivedEvent event) {
        String command = getCommand(event);

        switch (command) {
            case "kick": {
                new Kick(event);
                break;
            }

            case "ban": {
                new Ban(event);
                break;
            }

            case "roll": {
                new Roll(event);
                break;
            }

            case "reverse": {
                new Reverse(event);
                break;
            }

            case "reverseName": {
                new ReverseName(event);
                break;
            }

            case "help": {
                new Help(event);
                break;
            }

            case "echo": {
                new Echo(event);
                break;
            }

            case "leet": {
                new Leet(event);
                break;
            }

            case "rps": {
                new RPS(event);
                break;
            }

            case "||pm||": {
                new PM(event);
                break;
            }

            case "remind": {
                new RemindStarter(event);
                break;
            }

            case "solve": {
                new Solve(event);
                break;
            }

            case "wikipedia": {
                new Wikipedia(event);
                break;
            }

            case "avatar": {
                new Avatar(event);
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
        return event.getMessage().getContentRaw().split(" ")[0].substring(1);
    }
}