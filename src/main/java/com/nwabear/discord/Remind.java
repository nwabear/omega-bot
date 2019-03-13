package com.nwabear.discord;

import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Calendar;
import java.util.Date;

public class Remind extends Command implements Runnable {
    @Override
    public void run() {
        this.command();
    }

    @Override
    public void command() {
        String[] args = this.message.getContentRaw().substring(8).split(" ");
        Calendar cal = Calendar.getInstance();

        try {
            int hours = Integer.parseInt(args[0]);
            int minutes = (60 * hours) + Integer.parseInt(args[1]);

            String message = "";

            for (int i = 2; i < args.length; i++) {
                message += args[i];
                message += " ";
            }

            this.channel.sendMessage("Reminding you in " + args[0] + " hours and " + args[1] + " minutes about \"" + message + "\".").queue();

            cal.add(Calendar.MINUTE, minutes);
            Date finish = cal.getTime();

            while (Calendar.getInstance().getTime().before(finish)) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    // do nothing
                }
            }

            PrivateChannel pc = this.user.openPrivateChannel().complete();
            pc.sendMessage(message).queue();
        } catch(Exception e) {
            this.channel.sendMessage("Something went wrong, check your formatting and try again!").queue();
        }
    }

    public Remind(MessageReceivedEvent event) {
        super(event);
    }
}
