package com.example.ameet.carexhaust;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Ameet on 12/27/2015.
 */
public class CustomArrayAdapter extends ArrayAdapter<Car> {

    ArrayList<Car> carList;
    Context context;
    int layoutResourceId;

    public CustomArrayAdapter(Context context,int layoutResourceId,ArrayList<Car>classList)
    {
        super(context,layoutResourceId);
        this.context=context;
        this.carList =classList;
        this.layoutResourceId=layoutResourceId;


    }
    //    @Override
//    public Class getItem(int position)
//    {
//        return super.getItem();
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Car car = getItem(position);
        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView listClassText = (TextView)convertView.findViewById(R.id.itemGradeText);
        listClassText.setText(car.getCarName());


        return convertView;

    }
    @Override
    public Car getItem(int position){

        return super.getItem(position);
    }

}
