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
        return event.getMessage().getContentRaw().split(" ")[0].substring(1);
    }
}