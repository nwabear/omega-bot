package com.nwabear.discord;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import net.dv8tion.jda.core.audio.AudioSendHandler;
import net.dv8tion.jda.core.audio.SpeakingMode;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.AudioManager;
import net.dv8tion.jda.core.managers.GuildController;

public class BotAudioManager {
    Guild guild;
    AudioManager am;
    AudioPlayerManager apm = new DefaultAudioPlayerManager();
    AudioPlayer player = apm.createPlayer();

    public BotAudioManager(Guild guild) {
        this.guild = guild;
        this.am = this.guild.getAudioManager();
        AudioSourceManagers.registerRemoteSources(apm);
        am.setSendingHandler(new AudioPlayerSendHandler(player));
        am.setSpeakingMode(SpeakingMode.VOICE);
    }

    public void play(MessageReceivedEvent event) {
        new Play(event, this.am, this.apm, this.player).command();
    }

    public void stop() {
        this.player.stopTrack();
    }

    public static class AudioPlayerSendHandler implements AudioSendHandler {
        private final AudioPlayer audioPlayer;
        private AudioFrame lastFrame;

        public AudioPlayerSendHandler(AudioPlayer audioPlayer) {
            this.audioPlayer = audioPlayer;
        }

        @Override
        public boolean canProvide() {
            lastFrame = audioPlayer.provide();
            return lastFrame != null;
        }

        @Override
        public byte[] provide20MsAudio() {
            return lastFrame.getData();
        }

        @Override
        public boolean isOpus() {
            return true;
        }
    }
}
