package com.nwabear.discord;

import com.google.gson.Gson;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.User;
import org.apache.commons.io.IOUtils;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReminderLoader implements Runnable {
    @Override
    public void run() {
        boolean loaded = false;
        while(!loaded) {
            try {
                String url = "http://192.168.86.110:8080/reminders";
                Gson gson = new Gson();
                Reminders reminders = gson.fromJson(IOUtils.toString(new URL(url)), Reminders.class);
                List<Guild> guilds = BotApp.getJda().getGuilds();

                for (Reminder r : reminders.reminders) {
                    String message = r.reminder_text;

                    User user = BotApp.getJda().getUserById(r.user_id);
                    SimpleDateFormat sdf = new SimpleDateFormat();
                    sdf.applyLocalizedPattern("yyyy-MM-dd HH:mm:ss");
                    Date finish = sdf.parse(r.reminder_date);
                    Remind remind = new Remind(message, user, finish);
                    remind.setId(Long.parseLong(r.reminder_id));

                    Thread thread = new Thread(remind);
                    thread.start();

                    System.out.println("Reminder loaded: " + new Gson().toJson(r));
                }

                loaded = true;
            } catch(Exception e) {
                // do nothing
            }

            try {
                Thread.sleep(1000);
            } catch(Exception e) {
                // do nothing
            }
        }
    }
}
