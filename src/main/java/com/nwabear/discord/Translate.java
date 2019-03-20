package com.nwabear.discord;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.jsoup.Jsoup;

import java.net.URLEncoder;

public class Translate extends Command {
    public Translate(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";translate <input>: translates the input to english using google translate";
    }

    @Override
    public void command() {
        String google = "http://www.google.com/search?q=";
        String expression = this.message.getContentRaw().substring(11);

        System.out.println(expression);

        expression += " translate";

        try {
            // get output of google translate search
            String output = Jsoup.connect(google + URLEncoder.encode(expression, "UTF-8")).get().select("div#tw-target").first().text();

            // slim the result down to just the translated text
            output = output.substring(0, output.indexOf(" More More ----"));

            // send the translated text back to the user
            this.channel.sendMessage("\\\"" + output + "\\\"").queue();
        } catch (Exception e) {
            this.channel.sendMessage("There was an error translating, please try again.").queue();
            System.out.println(e);
        }
    }
}
