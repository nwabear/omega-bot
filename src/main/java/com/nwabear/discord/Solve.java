package com.nwabear.discord;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.jsoup.Jsoup;

import java.net.URLEncoder;

public class Solve extends Command {
    public Solve(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";solve <expression>: solves a given math problem";
    }

    @Override
    public void command() {
        String google = "http://www.google.com/search?q=";
        String expression = this.message.getContentRaw().substring(7);

        try {
            String output = Jsoup.connect(google + URLEncoder.encode(expression, "UTF-8")).get().select("div#search").first().text();

            output = output.substring(output.indexOf("Result ") + 7, output.indexOf("You") - 1);

            this.channel.sendMessage(output).queue();
        } catch (Exception e) {
            this.channel.sendMessage("There was an error calculating, please make sure the syntax is correct and try again.").queue();
        }
    }
}