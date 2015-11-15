package com.udacity.gradle.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * References:
 * [1] http://stackoverflow.com/questions/17346102/must-every-activity-have-a-layout
 */
public class MainActivity extends AppCompatActivity {

    public static final String TOAST_MESSAGE = "com.udacity.gradle.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String message = intent.getStringExtra(TOAST_MESSAGE);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        finish();
    }

}
