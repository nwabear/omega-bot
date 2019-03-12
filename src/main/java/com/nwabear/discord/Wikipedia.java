package com.nwabear.discord;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeFilter;

import java.net.URLEncoder;

public class Wikipedia {
    public Wikipedia(MessageReceivedEvent event) {
        String baseURL = "https://www.google.com/search?q=";
        String search = event.getMessage().getContentRaw().substring(11);

        try {
            NodeFilter nf;
            String stuff = Jsoup.connect(baseURL + URLEncoder.encode(search, "UTF-8")).get().getElementsContainingText("Wikipedia").attr("abs:href");
            event.getChannel().sendMessage(stuff).queue();
        } catch(Exception e) {
            event.getChannel().sendMessage("There was an error finding that, please try again or make sure that it exists").queue();
        }
    }
}
