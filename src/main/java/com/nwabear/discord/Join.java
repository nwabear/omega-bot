package com.nwabear.discord;

import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.AudioManager;

import java.util.List;

public class Join extends Command {
    public Join(MessageReceivedEvent event) {
        super(event);
        this.description =
                ";join <voice channel>: joins specified voice channel";
    }

    @Override
    public void run() {
        try {
            VoiceChannel vc = this.guild.getVoiceChannelsByName(this.message.getContentRaw().substring(6), true).get(0);
            AudioManager am = this.guild.getAudioManager();

            am.openAudioConnection(vc);
        } catch(Exception e) {
            this.channel.sendMessage("There was an error joining, please try again").queue();
        }
    }
}
