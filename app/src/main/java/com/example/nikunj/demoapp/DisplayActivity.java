package com.example.nikunj.demoapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {

    SQLiteDatabase db;
    ListView lv;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        ActionBar ab=getSupportActionBar();
        ab.setTitle("DisplayUsers");

        lv=(ListView)findViewById(R.id.display_listView);
        db = openOrCreateDatabase("Mydatabase.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        c = db.rawQuery("select * from Register", null);

        ArrayList<String> list = new ArrayList<>();
        List<String> data = new ArrayList<String>();
        try
        {
            if(c.getCount()>0)
            {
                while (c.moveToNext()) {
                    String fname = c.getString(1);
                    String lname = c.getString(2);
                    String gen=c.getString(7);
                    String hobbies=c.getString(9);
                    data.add(fname + "   " + lname + "   " + gen + "   " + hobbies);
                }
                c.close();
            }
            else
            {
                Toast.makeText(DisplayActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.displaydata,data);
            lv.setAdapter(adapter);
        }
        catch(Exception e)
        {
            Toast.makeText(DisplayActivity.this, "" + e, Toast.LENGTH_SHORT).show();
        }
    }
}
