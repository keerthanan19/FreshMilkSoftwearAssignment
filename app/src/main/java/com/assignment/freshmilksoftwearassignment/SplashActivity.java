package com.assignment.freshmilksoftwearassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.assignment.freshmilksoftwearassignment.Database.DBUtils;
import com.assignment.freshmilksoftwearassignment.Utils.Controller;

public class SplashActivity extends AppCompatActivity {

    Context mContext;

    private ProgressBar progressBar;
    private int progressStatus = 0;
    private TextView textView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mContext = SplashActivity.this;
        DBUtils.deleteAllData(mContext);

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        progressBar = (ProgressBar) findViewById(R.id.progress_circular);
        textView = (TextView) findViewById(R.id.textView);
        // Start long running operation in a background thread
        Controller.progressBar(progressStatus,handler,progressBar,textView);
        Controller.getAllData(this);
    }
}