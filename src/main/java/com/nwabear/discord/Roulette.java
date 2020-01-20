package com.nwabear.discord;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Roulette extends Command {
    public Roulette(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";roulette: spins a roulette wheel";
    }

    @Override
    public void command() {
        Random rand = new Random();
        try {
            // create a random number and print it to the channel
            String toReturn = "";
            int[] red = {9, 30, 7, 32, 5, 34, 3, 36, 1, 27, 25, 12, 19, 18, 21, 16, 23, 14};
            int[] black = {28, 26, 11, 20, 17, 22, 15, 24, 13, 10, 29, 8, 31, 6, 33, 4, 35, 2};
            int num = rand.nextInt(37);
            num--;

            toReturn = toReturn + num + " ";

            int color = 0;

            for(int i = 0; i < 18; i++) {
                if(red[i] == num) {
                    color = 1;
                }

                if(black[i] == num) {
                    color = 2;
                }
            }

            switch(color) {
                case 0:
                    toReturn = toReturn + "green";
                    break;

                case 1:
                    toReturn = toReturn + "red";
                    break;

                case 2:
                    toReturn = toReturn + "black";
                    break;
            }

            toReturn = toReturn + "\n\n";

            if(num > 0 && num < 13) {
                toReturn = toReturn + "1st dozen";
            } else if(num > 12 && num < 25) {
                toReturn = toReturn + "2nd dozen";
            } else if(num > 24) {
                toReturn = toReturn + "3rd dozen";
            }

            toReturn = toReturn + "\n";

            if(num != 0 && num % 3 == 1) {
                toReturn = toReturn + "1st column";
            } else if(num != 0 && num % 3 == 2) {
                toReturn = toReturn + "2nd column";
            } else if(num != 0 && num % 3 == 0) {
                toReturn = toReturn + "3rd column";
            }

            this.channel.sendMessage(toReturn).queue();
        } catch (Exception e) {
            // if the range was left blank, print a number between 1 and 100
            this.channel.sendMessage((rand.nextInt(100) + 1) + "").queue();
        }
    }
}
