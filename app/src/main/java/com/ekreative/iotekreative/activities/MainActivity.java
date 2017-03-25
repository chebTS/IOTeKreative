package com.ekreative.iotekreative.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.ekreative.iotekreative.R;
import com.ekreative.iotekreative.views.VerticalSeekBar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private DatabaseReference databaseReference;
    private DatabaseReference referenceLeft;
    private DatabaseReference referenceRight;
    private DatabaseReference referenceServo;
    private final String LEFT_MOTOR_0 = "cheb_motor_l";
    private final String RIGHT_MOTOR_0 = "cheb_motor_r";
    private final String LEFT_MOTOR_1 = "gary_motor_l";
    private final String RIGHT_MOTOR_1 = "gary_motor_r";
    private final String LEFT_MOTOR_2 = "kirill_motor_l";
    private final String RIGHT_MOTOR_2 = "kirill_motor_r";

    private final String CHEB_SERVO = "cheb_servo";
    private final String GARY_SERVO = "gary_servo";
    private final String KIRILL_SERVO = "kirill_servo";

    private final int ZERO = 255;


    protected void changeRobot(int i) {
        Log.i(TAG, "changeRobot: "  + i);
        switch (i){
            default:
            case 0:
                referenceLeft = FirebaseDatabase.getInstance().getReference().child(LEFT_MOTOR_0);
                referenceRight = FirebaseDatabase.getInstance().getReference().child(RIGHT_MOTOR_0);
                referenceServo = FirebaseDatabase.getInstance().getReference().child(CHEB_SERVO);
                break;
            case 1:
                referenceLeft = FirebaseDatabase.getInstance().getReference().child(LEFT_MOTOR_1);
                referenceRight = FirebaseDatabase.getInstance().getReference().child(RIGHT_MOTOR_1);
                referenceServo = FirebaseDatabase.getInstance().getReference().child(GARY_SERVO);
                break;
            case 2:
                referenceLeft = FirebaseDatabase.getInstance().getReference().child(LEFT_MOTOR_2);
                referenceRight = FirebaseDatabase.getInstance().getReference().child(RIGHT_MOTOR_2);
                referenceServo = FirebaseDatabase.getInstance().getReference().child(KIRILL_SERVO);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        changeRobot(0);
        setUpSpinner();
        setUpProgressBars();
    }

    protected void setUpSpinner(){
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.robots, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                changeRobot(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setUpProgressBars(){
        final VerticalSeekBar left = (VerticalSeekBar) findViewById(R.id.left);
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
                referenceLeft.setValue(0);
            }
        });

        final VerticalSeekBar right = (VerticalSeekBar) findViewById(R.id.right);
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
                referenceRight.setValue(0);
            }
        });
        final SeekBar servo = (SeekBar)findViewById(R.id.servo);
        servo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                referenceServo.setValue(i - ZERO);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}
