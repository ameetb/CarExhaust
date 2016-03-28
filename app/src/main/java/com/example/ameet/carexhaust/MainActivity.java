package com.example.ameet.carexhaust;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Car> carArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.ameet.carexhaust.R.layout.activity_main);
//        Button button = (Button)findViewById(com.example.ameet.carexhaust.R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(MainActivity.this, ExhaustActivity.class);
//                startActivity(intent);
//
//
//            }
//        });
        ;
        carArrayList = new ArrayList<>();

        CustomArrayAdapter customArrayAdapter = new CustomArrayAdapter(MainActivity.this, R.layout.list_item, carArrayList);
        ListView classList = (ListView) (findViewById(R.id.classList));
        classList.setOnItemClickListener(new AdapterView.OnItemClickListener(){


            @Override
            public void onItemClick(AdapterView<?>adapter,View v, int position,long id){

                Car car = (Car)adapter.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this,ExhaustActivity.class);
                intent.putExtra("car",car);
                startActivity(intent);

            }


        });
        classList.setAdapter(customArrayAdapter);

        final Car fType = new Car(new int[] {
                R.raw.f_type_constant_medium_speed,
                R.raw.f_type_constant_high_speed,
                R.raw.f_type_aceleration,
                R.raw.f_type_downshift,
                R.raw.f_type_idle,
                R.raw.f_type_startup,
                R.raw.f_type_end_acceleration,
        },"f-type");
        customArrayAdapter.add(fType);
        carArrayList.add(fType);

                customArrayAdapter.notifyDataSetChanged();

//        final ImageButton settingsButton= (ImageButton) findViewById(R.id.settingsButton);

//        settingsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                MainActivity.this.getFragmentManager().beginTransaction()
//                        .replace(android.R.id.content, new SettingsFragment())
//                        .commit();
//
//            }
//
//        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.example.ameet.carexhaust.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == com.example.ameet.carexhaust.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
