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
                ";gimage <query>: searches for a google image matching the input\n\tIMPORTANT: Safe Search is not enabled, use at your own risk";
    }

    @Override
    public void command() {
        String google = "https://www.google.com/search?tbm=isch&tbs=ift%3Apng&tbs=ift%3Ajpg&q=";
        String expression = this.message.getContentRaw().substring(7);

        try {
            Elements list = Jsoup.connect(google + URLEncoder.encode(expression, "UTF-8")).get().select("div.rg_meta.notranslate");

            Random rand = new Random();

            String output = list.get(rand.nextInt(list.size())).html();

            if(output.contains(".png")) {
                output = output.substring(0, output.indexOf(".png") + 4);
            } else {
                output = output.substring(0, output.indexOf(".jpg") + 4);
            }

            output = output.substring(output.lastIndexOf("http"));

            output = URLDecoder.decode(output, "UTF-8");
            System.out.println(output);

            this.channel.sendMessage(new EmbedBuilder().setImage(output).build()).queue();
        } catch (Exception e) {
            this.commandTime(time + 1);
        }
    }

    public void commandTime(int time) {
        this.time = time;
        if(!(this.time > 20)) {
            this.command();
        }
    }
}
