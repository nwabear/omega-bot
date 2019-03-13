package com.nwabear.discord;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.jsoup.Jsoup;
import org.jsoup.select.NodeFilter;

import java.net.URLEncoder;

public class Wikipedia extends Command {
    public Wikipedia(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";wikipedia <search>: provides wikipedia link about a given topic";
    }

    @Override
    public void command() {
        String baseURL = "https://www.google.com/search?q=";
        String search = this.message.getContentRaw().substring(11);

        try {
            NodeFilter nf;
            String stuff = Jsoup.connect(baseURL + URLEncoder.encode(search, "UTF-8")).get().getElementsContainingText("Wikipedia").attr("abs:href");
            this.channel.sendMessage(stuff).queue();
        } catch(Exception e) {
            this.channel.sendMessage("There was an error finding that, please try again or make sure that it exists").queue();
        }
    }
}
