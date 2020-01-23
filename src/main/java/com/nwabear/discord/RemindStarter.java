package com.nwabear.discord;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class RemindStarter extends Command {
    public RemindStarter(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";remind <hours> <minutes> <message>: dm's you the message after the specified time has passed";
    }

    @Override
    public void run() {
        // start a remind thread
        Remind remind = new Remind(this.event);

        Thread thread = new Thread(remind);
        thread.start();
    }
}
