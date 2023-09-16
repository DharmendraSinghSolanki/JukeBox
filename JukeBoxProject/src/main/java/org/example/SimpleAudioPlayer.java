package org.example;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class SimpleAudioPlayer {
    // to store current position
    Long currentFrame;
    Clip clip;

    // current status of clip
    String status;

    AudioInputStream audioInputStream;
    static String filePath;

    // constructor to initialize streams and clip
    public SimpleAudioPlayer(String path)
            throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        // create AudioInputStream object
        audioInputStream =
                AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());

        // create clip reference
        clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }



    public static void MusicControls (String path)
    {
        try
        {
            filePath=path;
            SimpleAudioPlayer audioPlayer =
                    new SimpleAudioPlayer(path);

            audioPlayer.play();
            Scanner sc = new Scanner(System.in);

            while (true)
            {
                System.out.println("1. pause");
                System.out.println("2. resume");
                System.out.println("3. restart");
                System.out.println("4.  next");
                System.out.println("5. stop");
                //  System.out.println("6. Jump to specific time");
                int c = sc.nextInt();
                audioPlayer.gotoChoice(c);
                if (c == 5)
                {
                    break;
                }
            }
            //  sc.close();
        }
        catch (Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }


    // Work as the user enters his choice
    public static void MusicControls (List<String> list) {
        for(int i=0;i<list.size();i++) {

            try {
                filePath = list.get(i);
                SimpleAudioPlayer audioPlayer =
                        new SimpleAudioPlayer(list.get(i));

                audioPlayer.play();
                Scanner sc = new Scanner(System.in);

                while (true) {
                    System.out.println("1. pause");
                    System.out.println("2. resume");
                    System.out.println("3. restart");
                    System.out.println("4.  next");
                    System.out.println("5. stop");
                    //  System.out.println("6. Jump to specific time");
                    int c = sc.nextInt();
                    audioPlayer.gotoChoice(c);
                    if (c==4)
                        break;
                    if (c == 5) {
                        i=list.size()-1;

                        break;
                    }
                }
                //  sc.close();
            } catch (Exception ex) {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }
        }
    }

    public static void MusicControl(List<String>list)
    {
        for(int i=0;i<list.size();i++) {

            try {
                filePath = list.get(i);
                SimpleAudioPlayer audioPlayer =
                        new SimpleAudioPlayer(list.get(i));

                audioPlayer.play();
                Scanner sc = new Scanner(System.in);

                while (true) {
                    System.out.println("1. pause");
                    System.out.println("2. resume");
                    System.out.println("3. restart");
                    System.out.println("4.  next");
                    System.out.println("5. stop");
                    //  System.out.println("6. Jump to specific time");
                    int c = sc.nextInt();
                    audioPlayer.gotoChoice(c);
                    if (c == 4)
                        break;
                    if (c == 5) {
                        i = list.size() - 1;

                        break;
                    }
                }
                //  sc.close();
            } catch (Exception ex) {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }
        }
    }

    private void gotoChoice(int c)
            throws IOException, LineUnavailableException, UnsupportedAudioFileException
    {
        switch (c)
        {
            case 1:
                pause();
                break;
            case 2:
                resumeAudio();
                break;
            case 3:
                restart();
                break;
            case 4:
                next();
                break;
            case 5:
                stop();
                break;
            case 6:
                System.out.println("Enter time (" + 0 +
                        ", " + clip.getMicrosecondLength() + ")");
                Scanner sc = new Scanner(System.in);
                long c1 = sc.nextLong();
                jump(c1);
                break;
        }
    }
    // Method to play the audio
    public void play()
    {
        //start the clip
        clip.start();

        status = "play";
    }

    // Method to pause the audio
    public void pause()
    {
        if (status.equals("paused"))
        {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame =
                this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    // Method to resume the audio
    public void resumeAudio() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        if (status.equals("play"))
        {
            System.out.println("Audio is already "+
                    "being played");
            return;
        }
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    // Method to restart the audio
    public void restart() throws IOException, LineUnavailableException,
            UnsupportedAudioFileException
    {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }
    public void next() throws  IOException,LineUnavailableException,UnsupportedAudioFileException{
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    public void nextAudioSystem() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream=AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    // Method to stop the audio
    public void stop() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }
    // Method to jump over a specific part
    public void jump(long c) throws UnsupportedAudioFileException, IOException,
            LineUnavailableException
    {
        if (c > 0 && c < clip.getMicrosecondLength())
        {
            clip.stop();
            clip.close();
            resetAudioStream();
            currentFrame = c;
            clip.setMicrosecondPosition(c);
            this.play();
        }
    }

    // Method to reset audio stream
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException
    {
        audioInputStream = AudioSystem.getAudioInputStream(
                new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
