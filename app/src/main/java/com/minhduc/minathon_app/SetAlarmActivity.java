package com.minhduc.minathon_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SetAlarmActivity extends AppCompatActivity {
    private static final String TAG = "SET ALARM";

    public TimePicker mTimePicker;
    public Button mSaveButton;
    public Button mCancelButton;

    public RadioGroup mRadioGroup;
    public RadioButton mRadioButton;

    private DatabaseReference alarmTimeRef, ringtoneRef;

    private static final String[] soundUrl = {
            "https://data.chiasenhac.com/dataxx/20/downloads/1126/0/1125984-4f2f6037/128/Happy%20-%20Pharrell%20Williams.mp3",
            "https://data.chiasenhac.com/dataxx/03/downloads/1541/0/1540695-49ecd0c4/320/Reality%20-%20Lost%20Frequencies_%20Janieck%20Devy.mp3",
//            "https://data.chiasenhac.com/dataxx/38/downloads/1867/0/1866600-0fe4cc84/320/The%20Middle%20-%20Zedd_%20Maren%20Morris_%20Grey.mp3",
            "https://data.chiasenhac.com/dataxx/37/downloads/1919/0/1918967-ceaf4c6a/128/I_m%20A%20Mess%20-%20Bebe%20Rexha.mp3",
            "https://data.chiasenhac.com/dataxx/38/downloads/1901/0/1900494-3e487c31/128/No%20Tears%20Left%20To%20Cry%20-%20Ariana%20Grande.mp3",
            "https://data.chiasenhac.com/dataxx/32/downloads/1922/0/1921793-9d0d10aa/128/Connection%20-%20OneRepublic.mp3",
            "https://data.chiasenhac.com/dataxx/2/downloads/1579/0/1578179-5440f108/128/Never%20Forget%20You%20-%20Zara%20Larsson_%20MNEK.mp3",
            "https://data.chiasenhac.com/dataxx/38/downloads/1914/0/1913377-90555858/128/Colour%20-%20MNEK_%20Hailee%20Steinfeld.mp3",
            "https://data.chiasenhac.com/dataxx/3/downloads/1766/0/1765119-43e48842/128/Ciao%20Adios%20-%20Anne-Marie.mp3",
            "https://data.chiasenhac.com/dataxx/37/downloads/1919/0/1918884-d3eb7200/128/Born%20To%20Be%20Yours%20-%20Kygo_%20Imagine%20Dragons.mp3",
            "https://data.chiasenhac.com/dataxx/25/downloads/1988/0/1987848-13006bb0/128/Un%20A__o%20-%20Sebastian%20Yatra_%20Reik.mp3"
    };

    int soundIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        mTimePicker = (TimePicker) findViewById(R.id.timePicker);
        mSaveButton = (Button) findViewById(R.id.saveBtn);
        mCancelButton = (Button) findViewById(R.id.cancelBtn);

        mRadioGroup = (RadioGroup) findViewById(R.id.soundPicker);

        alarmTimeRef = FirebaseDatabase.getInstance().getReference().child("alarmTime");
        ringtoneRef = FirebaseDatabase.getInstance().getReference().child("controls").child("currentTone");

        alarmTimeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String time = dataSnapshot.getValue().toString();
                Log.i("Set alarm", time);
            }

            @Override
            public void onCancelled(DatabaseError databaseError){}
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hour = String.format("%02d", mTimePicker.getHour());
                String minute = String.format("%02d", mTimePicker.getMinute());

//                int selectedRadioButton = mRadioGroup.getCheckedRadioButtonId();
//                mRadioButton = (RadioButton) findViewById(selectedRadioButton);
//                Log.i(TAG, "selected ID: " + selectedRadioButton);

                alarmTimeRef.setValue(hour+":"+minute);

                switch(mRadioGroup.getCheckedRadioButtonId()) {
                    case R.id.option1:
                        soundIndex = 0;
                        break;
                    case R.id.option2:
                        soundIndex = 1;
                        break;
                    case R.id.option3:
                        soundIndex = 2;
                        break;
                    case R.id.option4:
                        soundIndex = 3;
                        break;
                    case R.id.option5:
                        soundIndex = 4;
                        break;
                    case R.id.option6:
                        soundIndex = 5;
                        break;
                    case R.id.option7:
                        soundIndex = 6;
                        break;
                    case R.id.option8:
                        soundIndex = 7;
                        break;
                    case R.id.option9:
                        soundIndex = 8;
                        break;
                    case R.id.option10:
                        soundIndex = 9;
                        break;
                    default: soundIndex = 0;
                        break;
                }
                ringtoneRef.setValue(soundUrl[soundIndex]);

                finish();
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

