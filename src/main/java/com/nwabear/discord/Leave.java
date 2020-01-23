package com.nwabear.discord;

import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.AudioManager;

public class Leave extends Command {
    public Leave(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";leave: leaves current voice channel";
    }

    @Override
    public void run() {
        AudioManager am = this.guild.getAudioManager();
        am.closeAudioConnection();
    }
}
