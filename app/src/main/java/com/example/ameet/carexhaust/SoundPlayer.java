package com.example.ameet.carexhaust;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Ameet on 11/16/2015.
 */
public class SoundPlayer {


    Context context;
    private MediaPlayer mp;
    boolean nothingIsPlaying;
    public final int ACCELERATION = 200;
    private Car car;
    boolean noSoundAtConstantSpeed;

    int IDLE_TRACK=1000;
    int ACCELERATION_TRACK=2000;
    int DECELERATION_TRACK=3000;
    int CONSTANT_TRACK=4000;


    SoundPlayer(Context context,Car car,boolean noSoundAtConstantSpeed) {
        this.context = context;
        nothingIsPlaying =true;
        this.car=car;
        this.noSoundAtConstantSpeed=noSoundAtConstantSpeed;
    }

    public void playConstantSpeed(int currentSpeed) {
        if (!noSoundAtConstantSpeed) {
            int resourceId;

            if (currentSpeed < 45) {
                resourceId = car.getConstantMediumSpeedAudio();
            } else {
                resourceId = car.getConstantHighSpeedAudio();
            }

            if (mp != null) {
                mp.stop();
                mp.reset();
            }

            mp = MediaPlayer.create(context, resourceId);
            mp.setVolume(.70f, .70f);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    nothingIsPlaying = true;
                    mp.stop();
                    mp.reset();
                }
            });
            mp.start();
            nothingIsPlaying = false;
        }
    }

    public void playAcceleration() {
        if(mp!=null) {
            mp.stop();
            mp.reset();
        }
        mp = MediaPlayer.create(context, car.getAccelerationAudio());
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mp.reset();
                nothingIsPlaying =true;
            }
        });
        mp.start();
        nothingIsPlaying =false;
    }
    public void playDeceleration() {
        if(mp!=null) {
            mp.stop();
            mp.reset();
        }
        mp = MediaPlayer.create(context, car.getDownShiftAudio());
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                nothingIsPlaying =true;
                mp.stop();
                mp.reset();

            }
        });
        mp.start();
        nothingIsPlaying =false;

    }
    public void playIdle(){
        if(mp!=null) {
            mp.stop();
            mp.reset();
        }
        mp = MediaPlayer.create(context, car.getIdleAudio());
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                nothingIsPlaying =true;
                mp.stop();
                mp.reset();

            }
        });
        mp.start();
        nothingIsPlaying =false;
    }
    public void playStartup(){
        if(mp!=null) {
            mp.stop();
            mp.reset();
        }
        mp = MediaPlayer.create(context, car.getStartUpAudio());
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playIdle();
            }
        });
        mp.start();
        nothingIsPlaying =false;
    }

    public void playEndAcceleration(){
        if(mp!=null) {
            mp.stop();
            mp.reset();
        }
        mp = MediaPlayer.create(context, car.getEndAccelerationAudio());
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                nothingIsPlaying =true;
                mp.stop();
                mp.reset();

            }
        });
        mp.start();
        nothingIsPlaying =false;
    }
    public void playRev(){
        if(mp!=null) {
            mp.stop();
            mp.reset();
        }
        mp = MediaPlayer.create(context,car.getDownShiftAudio());
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                nothingIsPlaying =true;
                mp.stop();
                mp.reset();

            }
        });
        mp.start();
        nothingIsPlaying =false;
    }


    public boolean checkIfNothingIsPlaying()
    {
        return nothingIsPlaying;
    }


    public void soundPlayerRelease()
    {
        mp.release();
    }


}