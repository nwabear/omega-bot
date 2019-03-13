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
        String input = this.message.getContentRaw().substring(6);
        input = input.toUpperCase();
        String output = "";

        String originals = "ABCDEGHIJKLMNORSTUVWXY";
        String[] replacements =
                {"4", "8", "<", "|>", "3", "6", "|-|", "1", "\\_|", "|<", "|\\_", "|\\\\/|", "|\\\\|", "0", "|2", "5", "7", "|\\_|", "\\\\/", "\\\\/\\\\/", "><"};

        for(int i = 0; i < input.length(); i++) {
            if(originals.contains(input.charAt(i) + "")) {
                output += replacements[originals.indexOf(input.charAt(i))];
            } else {
                output += input.charAt(i);
            }
            output += " ";
        }
        this.channel.sendMessage(output).queue();
    }
}
