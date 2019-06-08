package com.joshuacrotts.standards;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * This class was incredibly painful to construct, but in essence, it uses both
 * pure Java, and the JavaFX libraries to playback sounds. The JavaFX library is
 * utilized more for long playback songs, such as the game's soundtracks, and
 * whatnot. The pure Java library is used for more repetitive sound effects,
 * that need to be played very quickly; JavaFX does not have a very effective
 * way of playing back sounds quickly.
 *
 * JavaFX SUPPORTS MP3'S AND WAV'S AND POSSIBLY OGG FILES (Use MP3's for songs)
 * Native Java SUPPORTS WAV FILES ONLY (Use WAV's for sfx)
 *
 * @author Joshua
 *
 */
public class StandardAudio
{
    //These deal with Java2D sounds (SFX mainly)

    private Clip        audioClip;
    private String      fileName;

    //These deal with JavaFX sounds
    private AudioClip   sound;
    private boolean     sfx;

    //Constructor for the Java2D sound effects
    public StandardAudio( String fileName )
    {
        this.fileName = fileName;
        try
        {
            File audioFile = new File( fileName );
            AudioInputStream audioStream = AudioSystem.getAudioInputStream( audioFile );
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info( Clip.class, format );
            audioClip = ( Clip ) AudioSystem.getLine( info );
            audioClip.open( audioStream );
        } catch ( IOException | LineUnavailableException | UnsupportedAudioFileException e )
        {
            e.printStackTrace();
        }
    }

    //Constructor for the JavaFX sound effects
    public StandardAudio( String fileName, boolean sfx )
    {
        this.sfx = sfx;
        new javafx.embed.swing.JFXPanel();

        try
        {
            this.sound = new AudioClip( new File( fileName ).toURI().toString() );

        } catch ( Exception e )
        {
            e.printStackTrace();
        }

        this.sound.setVolume( 1 );
    }

    /**
     * Resets and plays the sound effect, ONLY FOR JAVA2D SFX
     */
    public void rAndP()
    {
        this.audioClip.setFramePosition( 0 );
        this.audioClip.start();

    }

    /**
     * Plays the Java2D audio clip
     */
    public void J2DPlay()
    {
        audioClip.start();
    }

    /**
     * Stops the Java2D audio clip
     */
    public void J2DStop()
    {
        audioClip.stop();
    }

    /**
     * Closes the Java2D audio clip
     */
    public void J2DClose()
    {
        audioClip.close();
    }

    /**
     * @return if the Java2D audio clip is running.
     */
    public boolean isPlaying()
    {
        return this.audioClip.isRunning();
    }

    public void FXPlay()
    {
        if ( this.sound.isPlaying() )
        {
            return;
        } else
        {
            this.sound.play();
        }
        if (  ! sfx )
        {
            this.FXLoop();
        }
    }

    public void adjustFXVolume( double val )
    {
        this.sound.setVolume( this.sound.getVolume() + val );
    }

    public double getFXVolume()
    {
        return this.sound.getVolume();
    }

    public void FXStop()
    {
        this.sound.stop();
        //audioClip.close();
    }

    public String getFileName()
    {
        return fileName;
    }

    public void FXLoop()
    {
        this.sound.setCycleCount( MediaPlayer.INDEFINITE );
    }

    public void FXResetVolume()
    {
        this.sound.setVolume( 1 );
    }

    public boolean isFXPlaying()
    {
        return this.sound.isPlaying();
    }

}
