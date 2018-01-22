package com.example.nikunj.demoapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class SignUp extends AppCompatActivity {

    EditText fname,lname,eid,uname,pwd,cpwd;
    RadioGroup radioGroup;
    RadioButton maleradio,femaleradio;
    CheckBox box1,box2,box3;
    SQLiteDatabase db;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Cursor c;
    Spinner usertype;
    String user[]={"Select User Type","Admin","User"};
    String fn,ln,id,unm,password,gender,utype,hobby,cpassword;
    Button okbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fname=(EditText)findViewById(R.id.fname_edittext);
        lname=(EditText)findViewById(R.id.lname_edittext);
        eid=(EditText)findViewById(R.id.emailid_edittext);
        uname=(EditText)findViewById(R.id.unm_edittext);
        pwd=(EditText)findViewById(R.id.pwd_editText);
        cpwd=(EditText)findViewById(R.id.confirm_pwd_editText);
        radioGroup=(RadioGroup)findViewById(R.id.radiogroup);
        maleradio=(RadioButton)findViewById(R.id.male_radio);
        femaleradio=(RadioButton)findViewById(R.id.female_radio);
        box1=(CheckBox)findViewById(R.id.cricket_checkbox);
        box2=(CheckBox)findViewById(R.id.dancing_checkbox);
        box3=(CheckBox)findViewById(R.id.singing_checkbox);
        okbutton=(Button)findViewById(R.id.ok_button);

//      =======Database creates by following line=======
        db = openOrCreateDatabase("Mydatabase.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

//        String s="drop table Register";
//        db.execSQL(s);
//        Toast.makeText(SignUp.this, "Successully drop", Toast.LENGTH_SHORT).show();

//      =======Table creates by following line==========
        String s = "CREATE TABLE IF NOT EXISTS Register(ID INTEGER PRIMARY KEY AUTOINCREMENT,Fname text,Lname text,Emailid text,Uname text,Password text,Cpassword text,Gender text,Usertype text,Hobbies text)";
        db.execSQL(s);

        usertype=(Spinner)findViewById(R.id.usertype_spinner);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item,user);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_item);
        usertype.setAdapter(aa);

        usertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

//          ========To take spinner's selected item by following line========
                utype= usertype.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        okbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                c=db.rawQuery("select * from  Register where Uname='"+unm+"'",null);

                fn=fname.getText().toString();
                ln=lname.getText().toString();
                id=eid.getText().toString();
                unm=uname.getText().toString();
                password=pwd.getText().toString();
                cpassword=cpwd.getText().toString();

                if(fn.equals(""))
                    fname.setError("Please enter first name");

                else if(ln.equals(""))
                    lname.setError("Please enter last name");

                else  if (id!=emailPattern && id.length() <0)
                    eid.setError("Please enter valid email id");

                else if (uname.equals("")) {
                    uname.setError("Please enter user name");
                    }
                else if (pwd.equals("")) {
                    pwd.setError("Please enter password");
                    }
                else if (cpwd.equals("")) {
                    cpwd.setError("Please enter confirm password");
                }
                else if (password.length() < 6 || cpassword.length() < 6) {
                    pwd.setError("Please enter password at least 6 character password");
                }
//                else if(cpassword!=password) {
//                    cpwd.setError("Password doesn't match");
//                }
                else if(maleradio.isChecked()) {
                    gender = maleradio.getText().toString();
                }
                else if (femaleradio.isChecked()){
                        gender = maleradio.getText().toString();
                }
                else if(gender.equals("")){
                        Toast.makeText(SignUp.this, "Please select gender", Toast.LENGTH_SHORT).show();
                }
                else if (box1.isChecked()) {
                    hobby = box1.getText().toString();
                }
                else if (box2.isChecked()){
                        hobby = box2.getText().toString();
                }
                else if (box3.isChecked()) {
                    hobby = box3.getText().toString();
                }
                else if(hobby.equals("")){
                    Toast.makeText(SignUp.this, "Please select hobby", Toast.LENGTH_SHORT).show();
                }
                else if (c.getCount() > 0) {
                        Toast.makeText(SignUp.this, "Username already exists", Toast.LENGTH_SHORT).show();
                }
                else {
                        db.execSQL("insert into Register(Fname,Lname,Emailid,Uname,Password,Cpassword,Gender,Usertype,Hobbies)values('" + fn + "','" + ln + "','" + id + "','" + unm + "','" + password + "','" + cpassword + "','" + gender + "','" + utype + "','" + hobby + "');");
                        Toast.makeText(SignUp.this, "Congrates you are Registered", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(SignUp.this,Login.class);
                        startActivity(i);
                }
            }
        });
    }
}
