package com.example.nikunj.demoapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nikunj.db.User;
import com.example.nikunj.db.UserManager;

public class Login extends AppCompatActivity {

    SQLiteDatabase db;
    EditText userName,Password;
    Button login;
    String registeredUserName,registeredPassword,newUserName,newPassword;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar ab=getSupportActionBar();
        ab.setTitle(getString(R.string.screen_name_login));

        userName=(EditText)findViewById(R.id.username_edittext);
        Password=(EditText)findViewById(R.id.password_edittext);
        login=(Button)findViewById(R.id.login_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newUserName=userName.getText().toString();
                newPassword=Password.getText().toString();

                User user=UserManager.getInstance(getApplicationContext()).getUser(newUserName,newPassword);

                if(user==null)
                {
                    Toast.makeText(Login.this, "Username/Password doesn't match", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i=new Intent(Login.this,DisplayActivity.class);
                            startActivity(i);
                }
            }
        });
    }
}
