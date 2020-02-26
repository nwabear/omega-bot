package com.nwabear.discord;

import javafx.event.Event;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.react.GenericMessageReactionEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.core.requests.restaction.MessageAction;

public class VoteManager extends Command {
    private int up = -1;
    private int down = -1;

    private Listener listener;

    public VoteManager(MessageReceivedEvent event, Listener listener) {
        super(event);
        this.description =
                ";vote <topic>: creates a 1 minute poll for a topic";

        this.channel.sendMessage("Vote created: \"" + event.getMessage().getContentRaw().substring(6) + ",\" please react with ✅ or ❌!").queue(message -> {
            message.addReaction("✅").queue();
            message.addReaction("❌").queue();
        });
        this.listener = listener;
    }

    public VoteManager(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";vote <topic>: creates a 1 minute poll for a topic";
    }

    @Override
    public void run() {
        try {
            Thread.sleep(60000);
        } catch(Exception e) {
            // do nothing
        }

        float percentage = (float)up / (float)(up + down);
        if(percentage > 0.5) {
            this.channel.sendMessage("The vote passed with " + up + " for and " + down + " against!").queue();
        } else if(percentage == 0.5) {
            this.channel.sendMessage("The vote was a tie with " + up + " for and against.").queue();
        } else {
            this.channel.sendMessage("The vote failed with " + up + " for and " + down + " against...").queue();
        }

        this.listener.finishVote(this.channel);
    }

    public MessageChannel getChannel() {
        return this.channel;
    }

    public void processAddVote(MessageReactionAddEvent event) {
        if(event.getReactionEmote().getName().equals("✅")) {
            this.up++;
        }

        if(event.getReactionEmote().getName().equals("❌")) {
            this.down++;
        }

        System.out.println(event.getReactionEmote().getName());
    }

    public void processRemoveVote(MessageReactionRemoveEvent event) {
        if(event.getReactionEmote().getName().equals("✅")) {
            this.up--;
        }

        if(event.getReactionEmote().getName().equals("❌")) {
            this.down--;
        }
    }
}
