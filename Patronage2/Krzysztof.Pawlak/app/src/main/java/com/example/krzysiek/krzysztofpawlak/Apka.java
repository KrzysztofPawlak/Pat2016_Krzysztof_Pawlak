package com.example.krzysiek.krzysztofpawlak;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Apka extends AppCompatActivity {

    private Button mLogOffButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apka);

        mLogOffButton = (Button) findViewById(R.id.logoff_up_btn);

        final SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean music = getPrefs.getBoolean("checkbox", false);

        if (music == false) {
            Intent i = new Intent("com.example.krzysiek.krzysztofpawlak.APKA");
            startActivity(i);
        }


        mLogOffButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                getPrefs.edit().putBoolean("checkbox", false).commit();

                Intent i = new Intent(getApplicationContext(), Logowanie.class);
                startActivity(i);

                finish();

            }
        });
    }

}
