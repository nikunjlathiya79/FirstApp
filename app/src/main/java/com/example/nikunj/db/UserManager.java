package com.example.nikunj.db;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.nikunj.demoapp.Login;
import com.example.nikunj.demoapp.R;
import com.example.nikunj.demoapp.SignUp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikunj on 28-01-2018.
 */
public class UserManager {

    private static UserManager instance;
    private static SQLiteDatabase db;
    Cursor c;

    //SingleTon
    private UserManager() {

    }

    public static UserManager getInstance(Context context) {
        if (instance == null) {
            instance = new UserManager();

            db = context.openOrCreateDatabase("Mydatabase.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
//        String s="drop table Register";
//        db.execSQL(s);
//        Toast.makeText(SignUp.this, "Successully drop", Toast.LENGTH_SHORT).show();

            String s = "CREATE TABLE IF NOT EXISTS Register(ID INTEGER PRIMARY KEY AUTOINCREMENT,Fname text,Lname text,Emailid text,Uname text,Password text,Gender text,Usertype text,Hobbies text)";
            db.execSQL(s);

            return instance;
        }
        return instance;
    }

    public boolean isUsernameExist(String userName) {
        Cursor c = db.rawQuery("select * from  Register where Uname='" + userName + "'", null);
        return c.getCount() > 0 ? true : false;
    }

    public void store(User user) {
        db.execSQL("insert into Register(Fname,Lname,Emailid,Uname,Password,Gender,Usertype,Hobbies)values('" + user.getFirstName() + "','" + user.getLastName() + "','" + user.getEmailId() + "','" + user.getUserName() + "','" + user.getPassword() + "','" + user.getGender().toString() + "','" + user.getUserType() + "','" + user.getHobbies() + "');");
    }
    public User getUser(String newUserName, String newPassword) {
        c = db.rawQuery("select * from Register where Uname='" + newUserName + "' AND Password='" + newPassword + "'", null);
        if (c.getCount() > 0) {
            while(c.moveToNext()) {
                String firstName = c.getString(1);
                String lastName = c.getString(2);
                String emailId = c.getString(3);
                String userName = c.getString(4);
                String password = c.getString(5);
                Gender gender = Gender.getGender(c.getString(6));
                UserType userType = UserType.getUserType(c.getString(7));
                User user = new User(firstName, lastName, emailId, userName, password,userType,gender, null);
                return user;
            }
            return  null;
        } else {
            return null;
        }
    }
}
