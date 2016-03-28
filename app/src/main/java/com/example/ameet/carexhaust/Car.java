package com.example.ameet.carexhaust;

import java.lang.reflect.Array;
import java.io.Serializable;


/**
 * Created by Ameet on 12/27/2015.
 */
@SuppressWarnings("serial")

public class Car implements Serializable {
    int[] mAudioFiles;
    String mCarName;

    public Car(int[] audioFiles, String carName) {
        this.mAudioFiles = audioFiles;
        this.mCarName = carName;
    }

    public int[] getAudioFiles() {
        return mAudioFiles;
    }

    public String getCarName() {
        return mCarName;
    }

    public int getConstantMediumSpeedAudio() {
        return mAudioFiles[1];
    }

    public int getConstantHighSpeedAudio() {
        return mAudioFiles[2];
    }

    public int getAccelerationAudio() {
        return mAudioFiles[3];
    }

    public int getDownShiftAudio() {
        return mAudioFiles[4];
    }

    public int getIdleAudio() {
        return mAudioFiles[4];
    }


    public int getStartUpAudio() {
        return mAudioFiles[5];
    }

    public int getEndAccelerationAudio() {
        return mAudioFiles[6];
    }

}
