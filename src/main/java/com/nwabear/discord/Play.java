package com.nwabear.discord;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import net.dv8tion.jda.core.audio.AudioSendHandler;
import net.dv8tion.jda.core.audio.SpeakingMode;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.AudioManager;

import java.nio.ByteBuffer;

public class Play extends Command {
    private AudioTrack at;
    private AudioManager am;
    private AudioPlayerManager apm;
    private AudioPlayer player;

    public Play(MessageReceivedEvent event, AudioManager am, AudioPlayerManager apm, AudioPlayer ap) {
        super(event);
        this.am = am;
        this.apm = apm;
        this.player = ap;

        this.description =
                ";play <youtube link>: plays youtube video in current voice channel";
    }

    public void command() {
        if(this.am.getConnectedChannel() != null) {
            this.apm.loadItem(this.message.getContentRaw().substring(6), new AudioLoadResultHandler() {
                @Override
                public void trackLoaded(AudioTrack audioTrack) {
                    Play.this.player.playTrack(audioTrack);
                }

                @Override
                public void playlistLoaded(AudioPlaylist audioPlaylist) {
                    Play.this.player.playTrack(audioPlaylist.getSelectedTrack());
                }

                @Override
                public void noMatches() {
                    Play.this.channel.sendMessage("Nothing matching the query found").queue();
                }

                @Override
                public void loadFailed(FriendlyException e) {
                    Play.this.channel.sendMessage("Failed to load audio").queue();
                }
            });
        } else {
            this.channel.sendMessage("I am not connected to a voice channel").queue();
        }
    }

    @Override
    public void run() {
        // no
    }

    public AudioPlayer getPlayer() {
        return this.player;
    }
}