package com.nwabear.discord;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Random;

public class GImage extends Command {
    private int time;
    public GImage(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";gimage <query>: searches for a google image matching the input";
    }

    @Override
    public void run() {
        this.channel.sendMessage("Deprecated").queue();

//        String google = "https://www.google.com/search?tbm=isch&safe=strict&tbs=ift%3Apng&tbs=ift%3Ajpg&q=";
//        String expression = this.message.getContentRaw().substring(7);
//        expression = expression.replace("\\", "\\\\");
//
//        try {
//            // get each image in the google search
//            Elements list = Jsoup.connect(google + URLEncoder.encode(expression, "UTF-8")).get().select("div.rg_meta.notranslate");
//
//            Random rand = new Random();
//
//            // select a random image to display
//            String output = list.get(rand.nextInt(list.size())).html();
//
//            // get the substring of the image based on the last characters of it
//            if(output.contains(".png")) {
//                output = output.substring(0, output.indexOf(".png") + 4);
//            } else {
//                output = output.substring(0, output.indexOf(".jpg") + 4);
//            }
//
//            // get the beginning of the link based on the last selection of http before the extension
//            output = output.substring(output.lastIndexOf("http"));
//
//            // print the image into the chat
//            output = URLDecoder.decode(output, "UTF-8");
//            System.out.println(output);
//
//            this.channel.sendMessage(new EmbedBuilder().setImage(output).build()).queue();
//        } catch (Exception e) {
//            // if the search failed for any reason
//            // try again until it has gone 5 times
//            // where it can be assumed that the search
//            // won't work and it will stop trying
//            this.commandTime(time + 1);
//        }
    }

    public void commandTime(int time) {
        this.time = time;
        if(!(this.time > 5)) {
            this.run();
        } else {
            this.channel.sendMessage("Failed to find image").queue();
        }
    }
}
