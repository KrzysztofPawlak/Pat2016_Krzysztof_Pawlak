package com.example.krzysiek.krzysztofpawlak;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

/**
 * Created by Krzysiek on 2016-01-12.
 */
public class Splash extends Activity {

    boolean flaga;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        final SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean music = getPrefs.getBoolean("checkbox", false);

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(6000);

                    synchronized (this) {
                        while (flaga) {
                            wait();
                        }
                    }
                } catch (InterruptedException e){
                    e.printStackTrace();
                }finally{
                    wybranyEkran();
                    finish();
                }
            }
        };
        timer.start();
    }

    public void wybranyEkran() {
SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getApplication());
        boolean music = getPrefs.getBoolean("checkbox", false);

        if (music == false) {
            Intent i = new Intent(getApplicationContext(), Logowanie.class);
            startActivity(i);
        }
        else {
            Intent i = new Intent(getApplicationContext(), Apka.class);
            startActivity(i);
        }

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
