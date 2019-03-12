package com.nwabear.discord;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Random;

public class RPS {
    public RPS(MessageReceivedEvent event) {
        int userChoice = 0;
        int computerChoice = 0;
        switch (event.getMessage().getContentRaw().toLowerCase().charAt(5)) {
            case 'r':
                userChoice = 1;
                break;
            case 'p':
                userChoice = 2;
                break;
            case 's':
                userChoice = 4;
                break;
        }

        Random rand = new Random();
        switch (rand.nextInt(3)) {
            case 0:
                computerChoice = 8;
                break;
            case 1:
                computerChoice = 16;
                break;
            case 2:
                computerChoice = 32;
                break;
        }

        int number = computerChoice - userChoice;
        if (number == 31 || number == 6 || number == 12) {
            event.getChannel().sendMessage("You won!").queue();
        } else if (number == 4 || number == 15 || number == 30) {
            event.getChannel().sendMessage("You lost...").queue();
        } else {
            event.getChannel().sendMessage("You tied").queue();
        }
    }
}
