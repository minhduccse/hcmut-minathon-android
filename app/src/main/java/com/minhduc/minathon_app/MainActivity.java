package com.minhduc.minathon_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    public TextView mNowAlarmView;
    private TextView mTemp, mHumid;

    private DatabaseReference alarmTimeRef, temperatureRef, humidityRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNowAlarmView = (TextView) findViewById(R.id.nowAlarm);
        mTemp = (TextView) findViewById(R.id.temp_value);
        mHumid = (TextView) findViewById(R.id.humidity_value);

        alarmTimeRef = FirebaseDatabase.getInstance().getReference().child("alarmTime");

        temperatureRef = FirebaseDatabase.getInstance().getReference().child("temp");
        humidityRef = FirebaseDatabase.getInstance().getReference().child("humid");


        alarmTimeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nowAlarmStr = dataSnapshot.getValue().toString();

                mNowAlarmView.setText(nowAlarmStr);
            }

            @Override
            public void onCancelled(DatabaseError databaseError){}
        });

        temperatureRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mTemp.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError){}
        });


        humidityRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mHumid.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError){}
        });
    }



    public void setAlarm(View view){
        Intent intent = new Intent(MainActivity.this, SetAlarmActivity.class);
        startActivity(intent);
    }
}

