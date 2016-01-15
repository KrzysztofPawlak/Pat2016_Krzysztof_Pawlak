package com.example.krzysiek.krzysztofpawlak;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Krzysiek on 2016-01-12.
 */
public class Splash extends Activity {


    boolean flaga;
    //Thread timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(5000);

                    synchronized (this) {
                        while (flaga) {
                            wait();
                        }
                    }
                } catch (InterruptedException e){
                    e.printStackTrace();
                }finally{
                    //Intent i = new Intent(getApplicationContext(), EkrLog.class);
                    Intent i = new Intent("com.example.krzysiek.krzysztofpawlak.EKRLOG");

                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }

int backButtonCount;
    @Override
    public void onBackPressed()

    {
        if(backButtonCount >= 1)
        {
            finish();
        }
        else
        {
            Toast.makeText(this, "Przyciśnij przycisk jeszcze raz aby wyjść z aplikacji.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
            flaga = true;
        }
    }
}
