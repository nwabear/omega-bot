package com.nwabear.discord;

import com.google.gson.Gson;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RemindStarter extends Command {
    public RemindStarter(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";remind <hours> <minutes> <message>: dm's you the message after the specified time has passed";
    }

    @Override
    public void run() {
        // get amount of time to wait for
        String[] args = this.message.getContentRaw().split(" ");
        int hours = Integer.parseInt(args[1]);
        int minutes = (60 * hours) + Integer.parseInt(args[2]);

        String message;

        int prefixLen = 8 + args[1].length() + 1 + args[2].length() + 1;
        message = this.message.getContentRaw().substring(prefixLen);

        // confirm that the user will get the remind after the specified amount of time
        this.channel.sendMessage("Reminding you in " + args[1] + " hours and " + args[2] + " minutes about \"" + message + "\".").queue();

        Calendar cal = Calendar.getInstance();
        // get the time in which the remind will send
        cal.add(Calendar.MINUTE, minutes);
        Date finish = cal.getTime();

        // start a remind thread
        Remind remind = new Remind(message, this.user, finish);
        Thread thread = new Thread(remind);
        thread.start();

        HttpPost post = new HttpPost("http://localhost:8080/reminders");
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyLocalizedPattern("yyyy-MM-dd HH:mm:ss");
        String payload = "{\"user_id\":\"" + this.user.getId() + "\",\"reminder_text\":\"" + message + "\",\"reminder_date\":\"" + sdf.format(finish) + "\"}";
        StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);

        HttpClient client = HttpClientBuilder.create().build();
        post.setEntity(entity);

        try {
            HttpResponse response = client.execute(post);
            remind.setId(Long.parseLong(IOUtils.toString(response.getEntity().getContent())));
        } catch (Exception e) {
            // do nothing
        }
    }
}
