package com.nwabear.discord;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.jsoup.Jsoup;

import java.net.URLEncoder;

public class Solve {
    public Solve(MessageReceivedEvent event) {
        String google = "http://www.google.com/search?q=";
        String expression = event.getMessage().getContentRaw().substring(7);
        System.out.println("idk");

        try {
            String output = Jsoup.connect(google + URLEncoder.encode(expression, "UTF-8")).get().select("div#search").first().text();

            output = output.substring(output.indexOf("Result ") + 7, output.indexOf("You") - 1);

            event.getChannel().sendMessage(output).queue();
        } catch (Exception e) {
            event.getChannel().sendMessage("There was an error calculating, please make sure the syntax is correct and try again.").queue();
        }
    }
}