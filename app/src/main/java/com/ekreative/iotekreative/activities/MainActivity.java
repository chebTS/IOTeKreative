package com.ekreative.iotekreative.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ekreative.iotekreative.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private DatabaseReference databaseReference;
    private DatabaseReference reference;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        reference = FirebaseDatabase.getInstance().getReference().child("pushbutton");
        Log.i(TAG, reference.getKey());

        //findViewById(R.id.btn_left).setOnClickListener(this);
    }
/*
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_left:
                count = count + 1;
                reference.setValue(count);
                break;
        }
    }*/
}
