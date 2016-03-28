package com.example.ameet.carexhaust;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ExhaustActivity extends AppCompatActivity {
    private Button unRegisterButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhaust);
        Intent i = getIntent();
        Car car = (Car)i.getSerializableExtra("car");

//
//                final ImageButton settingsButton= (ImageButton) findViewById(R.id.settingsButton);
//
//                settingsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                ExhaustActivity.this.getFragmentManager().beginTransaction()
//                        .replace(android.R.id.content, new SettingsFragment())
//                        .commit();
//
//            }
//
//        });
        CheckBox c = (CheckBox)(findViewById(R.id.checkBox));
        boolean noSoundAtConstantSpeed= c.isChecked();

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationChangeListener locationChangeListener = new LocationChangeListener(this,
                (ImageView) findViewById(R.id.engineStartLight),
                (ImageButton) findViewById(R.id.engineStartButton),
                (TextView) findViewById(R.id.gpsStatusText),
                (TextView) findViewById(com.example.ameet.carexhaust.R.id.revText),
                (ImageButton) findViewById(R.id.revButton),
                car,
                noSoundAtConstantSpeed
                );
        final ImageButton revButton= (ImageButton) findViewById(R.id.revButton);




        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                ) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationChangeListener);
        } else {
            Toast.makeText(this, "no permission", Toast.LENGTH_LONG).show();
        }

        locationChangeListener.onLocationChanged(null);
        boolean b = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

                revButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }

        });


    }

}
