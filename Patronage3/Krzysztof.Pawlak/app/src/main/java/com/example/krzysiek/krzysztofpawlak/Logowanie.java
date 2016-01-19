package com.example.krzysiek.krzysztofpawlak;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Logowanie extends AppCompatActivity {

    String emailU = "Patro@gmail.com";
    String hasloU = "Patro2016";

    private Button mSignUpButton;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logowanie);

//        SharedPreferences sharedPreferences = PreferenceManager
//                .getDefaultSharedPreferences(this);

        mSignUpButton = (Button) findViewById(R.id.sign_up_btn);
        mEmailEditText = (EditText) findViewById(R.id.email_et);
        mPasswordEditText = (EditText) findViewById(R.id.password_et);

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tryToSignUp();

            }
        });

        Intent i = new Intent("com.example.krzysiek.krzysztofpawlak.LOGOWANIE");

    }

    private void tryToSignUp() {
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        boolean hasErrors = false;

        if(TextUtils.isEmpty(email)) {
            hasErrors = true;
            mEmailEditText.setError("Pole email nie może być puste!");
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmailEditText.setError("Nieprawidłowy adres email!");
            hasErrors = true;
        }

        if(TextUtils.isEmpty(password)) {
            hasErrors = true;
            mPasswordEditText.setError("Pole hasło nie może być puste!");
        }
        else if (!password.matches("(?=.*[A-Z])(?=.*\\d).*[a-z].*") || (password.length() < 8)) {
            hasErrors = true;
            mPasswordEditText.setError("Hasło powinno zawierać 8 znaków, przynajmniej jedną dużą, jedną malą literę oraz jedną cyfrę!");
        }
        if(!hasErrors) {
            signUp(email, password);
        }
    }

    private void signUp(String email, String password) {

        if(email.equals(emailU)) {

            if(password.equals(hasloU)) {
                savePreferences("checkbox", true);

                Intent intent = new Intent(getApplicationContext(), Apka.class);
                startActivity(intent);

                finish();
            }
            else
                mPasswordEditText.setError("nieprawidłowe hasło!");
        }
        else {
            mEmailEditText.setError("e-mailu nie ma w bazie, wprowadź inny e-mail!");
        }
    }

    private void savePreferences(String key, boolean value){
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        SharedPreferences.Editor checkbox = sharedPreferences.edit();
        checkbox.putBoolean(key, value);
        checkbox.commit();
    }
}
