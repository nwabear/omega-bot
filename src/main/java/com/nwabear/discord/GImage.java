package com.nwabear.discord;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.jsoup.Jsoup;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class GImage extends Command {
    public GImage(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";gimage <query>: searches for a google image matching the input";
    }

    @Override
    public void command() {
        String google = "https://www.google.com/search?tbm=isch&tbs=ift%3Apng&tbs=ift%3Ajpg&q=";
        String expression = this.message.getContentRaw().substring(7);

        try {
            String output = Jsoup.connect(google + URLEncoder.encode(expression, "UTF-8")).get().select("div.rg_meta.notranslate").last().text();

            if(output.contains(".png")) {
                output = output.substring(0, output.indexOf(".png") + 4);
            } else {
                output = output.substring(0, output.indexOf(".jpg") + 4);
            }

            output = output.substring(output.lastIndexOf("http"));

            output = URLDecoder.decode(output, "UTF-8");

            this.channel.sendMessage(new EmbedBuilder().setImage(output).build()).queue();
        } catch (Exception e) {
            this.channel.sendMessage("There was an error searching, please try again.").queue();
            System.out.println(e);
        }
    }
}
