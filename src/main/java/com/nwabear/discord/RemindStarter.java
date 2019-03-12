package com.nwabear.discord;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class RemindStarter {
    public RemindStarter(MessageReceivedEvent event) {
        Remind remind = new Remind();
        remind.setEvent(event);

        Thread thread = new Thread(remind);
        thread.start();
    }
}
