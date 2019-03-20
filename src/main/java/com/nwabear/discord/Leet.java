package com.nwabear.discord;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Leet extends Command {
    public Leet(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";leet <input>: turns all text into leetspeak";
    }

    @Override
    public void command() {
        // get string after command call and set it to all uppercase
        String input = this.message.getContentRaw().substring(6);
        input = input.toUpperCase();
        String output = "";

        String originals = "ABCDEGHIJKLMNORSTUVWXY";
        String[] replacements =
                {"4", "8", "<", "|>", "3", "6", "|-|", "1", "\\_|", "|<", "|\\_", "|\\\\/|", "|\\\\|", "0", "|2", "5", "7", "|\\_|", "\\\\/", "\\\\/\\\\/", "><", "'/"};

        for(int i = 0; i < input.length(); i++) {
            // if the current character is able to be leeted
            if(originals.contains(input.charAt(i) + "")) {
                // add the leeted character to the string
                output += replacements[originals.indexOf(input.charAt(i))];
            } else {
                // if not add the un-leeted character to the string
                output += input.charAt(i);
            }
            // add a space to make the words more readable
            output += " ";
        }
        // send the leeted string back to the user
        this.channel.sendMessage(output).queue();
    }
}
