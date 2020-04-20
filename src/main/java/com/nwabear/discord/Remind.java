package com.nwabear.discord;

import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.User;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.Calendar;
import java.util.Date;

public class Remind implements Runnable {
    private String message;
    private User user;
    private Date finish;
    private long id;

    public Remind(String message, User user, Date finish) {
        this.message = message;
        this.user = user;
        this.finish = finish;
    }

    @Override
    public void run() {
        this.command();
    }

    public void command() {
        try {
            Calendar cal = Calendar.getInstance();
            while(cal.getTime().before(this.finish)) {
                cal = Calendar.getInstance();
                try {
                    // test every second to see if the remind will be sent
                    Thread.sleep(1000);
                } catch (Exception e) {
                    // do nothing
                }
            }

            // send the message to the user after the specified time has passed
            PrivateChannel pc = this.user.openPrivateChannel().complete();
            pc.sendMessage(message).queue();

            HttpDelete delete = new HttpDelete("http://localhost:8080/reminders?id=" + this.id);
            HttpClient client = HttpClientBuilder.create().build();

            client.execute(delete);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
