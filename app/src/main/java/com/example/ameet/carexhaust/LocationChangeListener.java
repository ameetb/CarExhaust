package com.example.ameet.carexhaust;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ameet on 11/16/2015.
 */
public class LocationChangeListener implements LocationListener {

    public Context context;
    public double currentSpeed;
    private TextView speedText;

    private double[] speedArray;
    SoundPlayer soundPlayer;
    String provider;
    private int previousChangeInSpeed;
    public final int CONSTANT_SPEED = 100;
    public final int ACCELERATION = 200;
    public final int DECELERATION = 300;
    public final int IDLE = 400;
    int speedError = 2;
    private ImageView engineStartLight;
    private ImageButton engineStartButton;
    private TextView gpsStatusTextView;
    private ImageButton revButton;
    private boolean engineStarted;
    private Color color;
    private int consecutiveAccelerations;

    public LocationChangeListener(Context context, final ImageView engineStartLight, final ImageButton engineStartButton,
                                  TextView gpsStatusTextView, TextView speedTextView, ImageButton revButton, Car car,
                                  boolean noSoundAtConstantSpeed) {
        this.context = context;
        this.speedText = speedTextView;
        this.engineStartButton = engineStartButton;
        this.engineStartLight = engineStartLight;
        this.gpsStatusTextView = gpsStatusTextView;
        this.revButton = revButton;
        speedArray = new double[]{-1, -1, -1};

        soundPlayer = new SoundPlayer(this.context, car, noSoundAtConstantSpeed);

//        revButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                soundPlayer.playRev();
//            }
//
//        });

        engineStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                soundPlayer.playStartup();
                engineStarted = true;

            }

        });
        onLocationChanged(null);

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {

            if (engineStarted) {

                double speed = 2.23694 * location.getSpeed();

                speedArray[0] = speed;


                if (determineCarState() == CONSTANT_SPEED) {
//                speedText.setText("constant");

                    if (previousChangeInSpeed == ACCELERATION) {
                        soundPlayer.playEndAcceleration();
                    } else if (previousChangeInSpeed != CONSTANT_SPEED || soundPlayer.checkIfNothingIsPlaying()) {
                        soundPlayer.playConstantSpeed((int) speed);
                    }
                    previousChangeInSpeed = CONSTANT_SPEED;
                }

                if (determineCarState() == ACCELERATION) {
//                speedText.setText("accelerating");
                    if (previousChangeInSpeed != ACCELERATION || soundPlayer.checkIfNothingIsPlaying()) {
                        soundPlayer.playAcceleration();
                    }
                    previousChangeInSpeed = ACCELERATION;
                }

                if (determineCarState() == DECELERATION) {
//                speedText.setText("decelerating");

                    if (previousChangeInSpeed == ACCELERATION) {
                        soundPlayer.playEndAcceleration();
                    } else if (previousChangeInSpeed != DECELERATION || soundPlayer.checkIfNothingIsPlaying()) {
                        soundPlayer.playDeceleration();
                    }
                    previousChangeInSpeed = DECELERATION;
                }

                if (determineCarState() == IDLE) {
//                speedText.setText("idle");
                    if (previousChangeInSpeed == CONSTANT_SPEED) {
                        soundPlayer.playDeceleration();
                    }
                    if (previousChangeInSpeed != IDLE || soundPlayer.checkIfNothingIsPlaying()) {
                        soundPlayer.playIdle();
                    }
                    previousChangeInSpeed = IDLE;
                }
                if (determineCarState() == 0) {
                    speedText.setText(
                            "0=" + String.valueOf(speedArray[0]) + System.getProperty("line.separator") +
                                    "1=" + String.valueOf(speedArray[1]) + System.getProperty("line.separator") +
                                    "2=" + String.valueOf(speedArray[2])
                    );

                }


            }

            speedText.setText(String.valueOf(2.23694 * (int) location.getSpeed()));
        } else {
            gpsStatusTextView.setText("gps status: locating");
        }
        speedArray[2] = speedArray[1];
        speedArray[1] = speedArray[0];

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

        if (status != LocationProvider.AVAILABLE) {
            CharSequence text = "GPS not Available";
            int duration = Toast.LENGTH_SHORT;

            gpsStatusTextView.setText("gps status: unavailable");

        } else {
            gpsStatusTextView.setText("gps status: active");
        }
    }

    @Override
    public void onProviderEnabled(String provider) {
        gpsStatusTextView.setText("gps status: active");
        Log.e("p", "provider enabled");

    }

    @Override
    public void onProviderDisabled(String provider) {
        this.provider = provider;
        gpsStatusTextView.setText("gps status: unavailable");


        Log.e("p", "provider disabled");

    }

    public int determineCarState() {
        int carState = 0;

        if (speedArray[0] != -1 && speedArray[1] != -1 && speedArray[2] != -1) {

            if (speedArray[0] < 10 && speedArray[1] < 10 && speedArray[2] < 10) {
                carState = IDLE;
            } else if (speedArray[0] > speedArray[1] + speedError && speedArray[1] + speedError > speedArray[2]) {
                carState = ACCELERATION;

            } else if (speedArray[0] < (speedArray[1] - speedError * 1.3) && (speedArray[1] - speedError * 1.3) < speedArray[2]) {
                carState = DECELERATION;

            } else {
                carState = CONSTANT_SPEED;

            }

        } else {
            Toast.makeText(context, "sp=-1", Toast.LENGTH_LONG).show();
        }

//        if(consecutiveAccelerations>3)
//        {
//            if(carState!=ACCELERATION)
//            {
//                consecutiveAccelerations=0;
//            }
//            else {
//                consecutiveAccelerations+=1;
//            }
//            return ACCELERATION;
//        }
//        else
//        {
//            if (carState==ACCELERATION)
//            {
//                consecutiveAccelerations+=1;
//            }
//            else
//            {
//                consecutiveAccelerations=0;
//            }
//           return carState;
//        }
        return carState;
    }


    public SoundPlayer getSoundPlayer() {
        return soundPlayer;
    }

}
//different level downshift
//acceleration stays one extra second
