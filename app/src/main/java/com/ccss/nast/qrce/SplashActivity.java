package com.ccss.nast.qrce;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.widget.ProgressBar;


public class SplashActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    private int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_splash);

        progressBar = (ProgressBar) findViewById(R.id.progress);

        //Long operation by thread
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < progressBar.getMax()) {
                    progressStatus += 5;
                    //Update progress bar with completion of operation
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                    try {
                        // Sleep for 300 milliseconds.
                        //Just to display the progress slowly
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.post(new Runnable() {
                    public void run() {
                        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    }
                });
            }
        }).start();
    }

    class MyProgressBar extends ProgressBar {

        MyProgressBar(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public MyProgressBar(Context context) {
            super(context);

        }

        @Override
        public void setProgress(int progress) {
            super.setProgress(progress);
            if (progress == this.getMax()) {
                //Do stuff when progress is max
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        }

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items
//        //to the action bar if it is present.
//        getMenuInflater().inflate(R.Menu.activity_splash, menu);
//        return true;
//    }
}


