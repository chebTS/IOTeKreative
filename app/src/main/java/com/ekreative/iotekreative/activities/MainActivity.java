package com.ekreative.iotekreative.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SeekBar;

import com.ekreative.iotekreative.R;
import com.ekreative.iotekreative.views.VerticalSeekBar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private DatabaseReference databaseReference;
    private DatabaseReference referenceLeft;
    private DatabaseReference referenceRight;
    private final String LEFT_MOTOR = "motor_l";
    private final String RIGHT_MOTOR = "motor_r";
    private final int ZERO = 255;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        referenceLeft = FirebaseDatabase.getInstance().getReference().child(LEFT_MOTOR);
        referenceRight = FirebaseDatabase.getInstance().getReference().child(RIGHT_MOTOR);
        final VerticalSeekBar left = (VerticalSeekBar)findViewById(R.id.left);
        left.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean isFromUser) {
                Log.i(TAG, "Left : " + i);
                referenceLeft.setValue(i - ZERO);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i(TAG, "onStopTrackingTouch LEFT");
                left.setProgress(ZERO);
                left.updateThumb();
                referenceLeft.setValue(ZERO);
            }
        });

        final VerticalSeekBar right = (VerticalSeekBar)findViewById(R.id.right);
        right.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean isFromUser) {
                Log.i(TAG, "Right : " + i);
                referenceRight.setValue(i - ZERO);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i(TAG, "onStopTrackingTouch RIGHT");
                right.setProgress(ZERO);
                right.updateThumb();
                referenceRight.setValue(ZERO);
            }
        });
    }

}
