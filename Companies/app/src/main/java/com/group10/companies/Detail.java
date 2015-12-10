package com.group10.companies;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Stefan on 2015-12-10.
 */
public class Detail extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if(getIntent().getExtras() != null) {
            int id = getIntent().getIntExtra("id", 1);

            //SELECT
            DatabaseHelper DBHelper = new DatabaseHelper(getApplicationContext());
            SQLiteDatabase readdb = DBHelper.getReadableDatabase();
            Cursor c = readdb.query("Office", new String[]{"telephonenumber", "city", "address", "longitude", "latitude"}, "id = " + id, null, null, null, null, null);
            c.moveToFirst();
            TextView companyId = (TextView) findViewById(R.id.office_id);
            companyId.setText(id);
            TextView companyTelephonenumber = (TextView) findViewById(R.id.office_telephonenumber);
            companyTelephonenumber.setText(c.getString(1));
            TextView companyCity = (TextView) findViewById(R.id.office_city);
            companyCity.setText(c.getString(2));
            TextView companyAddress = (TextView) findViewById(R.id.office_address);
            companyAddress.setText(c.getString(3));
        }else{
            Toast.makeText(getApplicationContext(), "Intent id is empty.", Toast.LENGTH_SHORT).show();
        }
    }

}
