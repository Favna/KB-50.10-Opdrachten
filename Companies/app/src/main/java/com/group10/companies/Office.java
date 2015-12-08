package com.group10.companies;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Stefan on 2015-12-08.
 */
public class Office extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office);

        //Add row to database
        DatabaseHelper DBHelper = new DatabaseHelper(this);
        SQLiteDatabase writedb = DBHelper.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        //initialValues.put("id", 1);
        initialValues.put("telephonenumber", "012111111111");
        initialValues.put("city", "Shitty");
        initialValues.put("address", "Town 1");
        initialValues.put("longitude", "412");
        initialValues.put("latitude", "123");
        writedb.insert("office", null, initialValues);

        //Set listview
        SQLiteDatabase readdb = DBHelper.getReadableDatabase();
        Cursor c = readdb.query("company", new String[]{"id", "telephonenumber", "city", "address", "longitude", "latitude"}, null, null, null, null, null, null);

        ArrayList<String> values = new ArrayList<>();
        while(c.moveToNext()) {
            values.add(c.getString(0) + " " + c.getString(1) + " " + c.getString(2) + " " + c.getString(3) + " " + c.getString(4) + " " + c.getString(5));
        }

        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);
    }
}
