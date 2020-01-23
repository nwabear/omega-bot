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

import java.util.ArrayList;

public class BotAudioManager implements Runnable {
    private Guild guild;
    private AudioManager am;
    private AudioPlayerManager apm = new DefaultAudioPlayerManager();
    private AudioPlayer player = apm.createPlayer();
    private ArrayList<MessageReceivedEvent> queue = new ArrayList<>();
    private boolean isPlaying = false;

    public BotAudioManager(Guild guild) {
        this.guild = guild;
        this.am = this.guild.getAudioManager();
        AudioSourceManagers.registerRemoteSources(apm);
        am.setSendingHandler(new AudioPlayerSendHandler(player));
        am.setSpeakingMode(SpeakingMode.PRIORITY);
    }

    public void play(MessageReceivedEvent event) {
        if(this.player.getPlayingTrack() == null) {
            new Play(event, this.am, this.apm, this.player).command();
        } else {
            this.queue.add(event);
            event.getChannel().sendMessage("Song added, #" + this.queue.size() + " in queue").queue();
        }
        this.isPlaying = true;
    }

    public void stop() {
        this.player.stopTrack();
        this.isPlaying = false;
    }

    public void resume() {
        this.isPlaying = true;
    }

    public void skip() {
        this.player.stopTrack();
        new Play(this.queue.get(0), this.am, this.apm, this.player).command();
        this.queue.remove(0);
    }

    public void clear() {
        this.queue = new ArrayList<>();
    }

    @Override
    public void run() {
        while(true) {
            if(this.isPlaying && !this.queue.isEmpty() && this.player.getPlayingTrack() == null) {
                new Play(this.queue.get(0), this.am, this.apm, this.player).command();
                this.queue.remove(0);
            }

            try {
                Thread.sleep(1000);
            } catch(Exception e) {
                // do nothing
            }
        }
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
