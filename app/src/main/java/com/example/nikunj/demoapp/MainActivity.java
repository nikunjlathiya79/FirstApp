package com.example.nikunj.demoapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button signupButton, loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar ab = getSupportActionBar();
        ab.setTitle(getString(R.string.screen_name_home));

        signupButton = (Button) findViewById(R.id.sign_button);
        loginButton = (Button) findViewById(R.id.login_button);

    }
    public void signup(View v) {
        Intent i = new Intent(MainActivity.this, SignUp.class);
        startActivity(i);

    }
    public void login(View v) {
        Intent i = new Intent(MainActivity.this, Login.class);
        startActivity(i);

    }
}
