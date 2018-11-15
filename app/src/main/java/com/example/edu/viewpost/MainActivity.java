package com.example.edu.viewpost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int mProgressBarStatus = 0;
    ProgressBar progressBarPost;
    TextView textViewProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBarPost = findViewById(R.id.progressBarPost);
        textViewProgress = findViewById(R.id.textViewProgress);

        ((Button)findViewById(R.id.buttonRun)).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        mProgressBarStatus = 0;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mProgressBarStatus < 100){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mProgressBarStatus ++;
                    progressBarPost.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBarPost.setProgress(mProgressBarStatus);
                        }
                    });
                    textViewProgress.post(new Runnable() {
                        @Override
                        public void run() {
                            textViewProgress.setText(String.valueOf(mProgressBarStatus)+" %");
                        }
                    });
                }
            }
        }).start();
    }
}
