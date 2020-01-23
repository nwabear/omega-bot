package com.nwabear.discord;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.jsoup.Jsoup;

import java.net.URLEncoder;

public class Wikipedia extends Command {
    public Wikipedia(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";wikipedia <search>: provides wikipedia link about a given topic";
    }

    @Override
    public void run() {
        String baseURL = "https://www.google.com/search?q=";
        String search = this.message.getContentRaw().substring(11);

        try {
            // search what the user specified
            // and output the first result that is a Wikipedia link
            String stuff = Jsoup.connect(baseURL + URLEncoder.encode(search, "UTF-8")).get().getElementsContainingText("Wikipedia").attr("abs:href");
            this.channel.sendMessage(stuff).queue();
        } catch(Exception e) {
            this.channel.sendMessage("There was an error finding that, please try again or make sure that it exists").queue();
        }
    }
}