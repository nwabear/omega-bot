package com.nwabear.discord;

import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Calendar;
import java.util.Date;

public class Remind implements Runnable {
    private MessageReceivedEvent event;

    @Override
    public void run() {
        String[] args = this.event.getMessage().getContentRaw().substring(8).split(" ");
        Calendar cal = Calendar.getInstance();

        try {
            int hours = Integer.parseInt(args[0]);
            int minutes = (60 * hours) + Integer.parseInt(args[1]);

            String message = "";

            for (int i = 2; i < args.length; i++) {
                message += args[i];
                message += " ";
            }

            this.event.getChannel().sendMessage("Reminding you in " + args[0] + " hours and " + args[1] + " minutes about \"" + message + "\".").queue();

            cal.add(Calendar.MINUTE, minutes);
            Date finish = cal.getTime();

            while (Calendar.getInstance().getTime().before(finish)) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    // do nothing
                }
            }


            PrivateChannel channel = event.getAuthor().openPrivateChannel().complete();
            channel.sendMessage(message).queue();
        } catch(Exception e) {
            this.event.getChannel().sendMessage("Something went wrong, check your formatting and try again!").queue();
        }
    }

    public void setEvent(MessageReceivedEvent event) {
        this.event = event;
    }
}
