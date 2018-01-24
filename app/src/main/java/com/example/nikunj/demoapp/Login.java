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

public class Login extends AppCompatActivity {

    SQLiteDatabase db;
    EditText unm,pwd;
    Button login;
    String ousername,opassword,username,password;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar ab=getSupportActionBar();
        ab.setTitle("Login");

        unm=(EditText)findViewById(R.id.username_edittext);
        pwd=(EditText)findViewById(R.id.password_edittext);
        login=(Button)findViewById(R.id.login_button);

//      ========Following line open our database===========
        db = openOrCreateDatabase("Mydatabase.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

//      ========Following line select  database===========
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username=unm.getText().toString();
                password=pwd.getText().toString();
                c = db.rawQuery("select * from Register where Uname='"+username+"' AND Password='"+password+"'",null);
            try {
                if (c != null) {
                    while(c.moveToNext())
                    {
                            ousername=c.getString(4);
                            opassword=c.getString(5);
                    }
                    if(ousername.equals(username) && opassword.equals(password)){
                            Intent i=new Intent(Login.this,DisplayActivity.class);
                            startActivity(i);
                    }
                    else {
                        Toast.makeText(Login.this, "Username/Password doesn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            catch (Exception c)
            {
                }
            }
        });
    }
}
