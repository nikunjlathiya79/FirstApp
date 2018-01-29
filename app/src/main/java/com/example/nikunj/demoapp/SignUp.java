package com.example.nikunj.demoapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nikunj.db.Gender;
import com.example.nikunj.db.User;
import com.example.nikunj.db.UserManager;
import com.example.nikunj.db.UserType;

import java.util.ArrayList;
import java.util.List;

public class SignUp extends AppCompatActivity {

    EditText firstName_edittext,lastName_edittext,emailId_edittext,userName_edittext,Password_edittext,confirmPassword_edittext;
    RadioGroup radioGroup;
    RadioButton maleRadioButton,femaleRadioButton;
    CheckBox hobbyCheckbox1,hobbyCheckbox2,hobbyCheckbox3;
    private String spinnerValue;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Spinner userTypes_Spinner;
    String userType []={"Select User Type","Admin","User"};
    Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ActionBar ab=getSupportActionBar();
        ab.setTitle(getString(R.string.screen_name_signup));

        firstName_edittext=(EditText)findViewById(R.id.fname_edittext);
        lastName_edittext=(EditText)findViewById(R.id.lname_edittext);
        emailId_edittext=(EditText)findViewById(R.id.emailid_edittext);
        userName_edittext=(EditText)findViewById(R.id.unm_edittext);
        Password_edittext=(EditText)findViewById(R.id.pwd_editText);
        confirmPassword_edittext=(EditText)findViewById(R.id.confirm_pwd_editText);
        radioGroup=(RadioGroup)findViewById(R.id.radiogroup);
        maleRadioButton=(RadioButton)findViewById(R.id.male_radio);
        femaleRadioButton=(RadioButton)findViewById(R.id.female_radio);
        hobbyCheckbox1=(CheckBox)findViewById(R.id.cricket_checkbox);
        hobbyCheckbox2=(CheckBox)findViewById(R.id.dancing_checkbox);
        hobbyCheckbox3=(CheckBox)findViewById(R.id.singing_checkbox);
        okButton=(Button)findViewById(R.id.ok_button);

        firstName_edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    Editable text = firstName_edittext.getText();
                    if(text.equals("")){
                        firstName_edittext.setError("Please enter first name");
                    }
                }
            }
        });
        lastName_edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    Editable text=lastName_edittext.getText();
                    if(text.equals("")){
                        lastName_edittext.setError("Please enter last name");
                    }
                }
            }
        });
        emailId_edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    Editable text=emailId_edittext.getText();
                    if(text.equals("")){
                        emailId_edittext.setError("Please enter email");
                    }
                }
            }
        });
        userName_edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    Editable text=userName_edittext.getText();
                    if(text.equals("")) {
                        userName_edittext.setError("Please enter user name");
                    }
                }
            }
        });
        Password_edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    Editable text=Password_edittext.getText();
                    if (text.equals("")){
                    Password_edittext.setError(" PLease enter password");
                    }
                }
            }
        });
        confirmPassword_edittext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    Editable text=confirmPassword_edittext.getText();
                    if(text.equals("")) {
                        confirmPassword_edittext.setError("Password must be six character");
                    }
                }
            }
        });
        userTypes_Spinner =(Spinner)findViewById(R.id.usertype_spinner);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item,userType);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_item);
        userTypes_Spinner.setAdapter(aa);

        userTypes_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerValue= userTypes_Spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    public void ButtonClick(View v) {

        String firstName,lastName,emailId,userName,password,gender;
        ArrayList<String> hobbies=new ArrayList<>();

        firstName=firstName_edittext.getText().toString();
        lastName=lastName_edittext.getText().toString();
        emailId=emailId_edittext.getText().toString();
        userName=userName_edittext.getText().toString();
        password=Password_edittext.getText().toString();

        if(maleRadioButton.isChecked())
            gender = maleRadioButton.getText().toString();
        else
            gender = femaleRadioButton.getText().toString();
        if (hobbyCheckbox1.isChecked())
            hobbies.add(hobbyCheckbox1.getText().toString());
         if(hobbyCheckbox2.isChecked())
            hobbies.add(hobbyCheckbox2.getText().toString());
         if(hobbyCheckbox3.isChecked())
              hobbies.add(hobbyCheckbox3.getText().toString());

        if(!password.equals(confirmPassword_edittext.getText().toString()))
            confirmPassword_edittext.setError("Password doesn't match");

        if (UserManager.getInstance(this).isUsernameExist(userName))
            Toast.makeText(SignUp.this,"Username already exists", Toast.LENGTH_SHORT).show();
        else {
            User user =new User(firstName,lastName,userName,emailId,password,UserType.getUserType(spinnerValue),Gender.getGender(gender),hobbies);
            UserManager.getInstance(this).store(user);

            Toast.makeText(SignUp.this, "Congrates you are Registered", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(SignUp.this,Login.class);
            startActivity(i);
        }
    }
}

