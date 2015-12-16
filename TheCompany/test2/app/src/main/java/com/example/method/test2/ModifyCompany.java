package com.example.method.test2;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Stefan on 2015-12-14.
 */
public class ModifyCompany extends AppCompatActivity {

    static final String PROVIDER_NAME = "com.example.method.test.CompanyProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/cpcontacts";
    static final Uri CONTENT_URL = Uri.parse(URL);

    ContentResolver resolver;
    EditText nameText, historyText, informationText;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company);

        resolver = getContentResolver();

        //Update buttons
        Button update = (Button) findViewById(R.id.button4);
        update.setText("Update Record");

        //fill textfields
        nameText = (EditText) findViewById(R.id.nameText);
        informationText = (EditText) findViewById(R.id.informationText);
        historyText = (EditText) findViewById(R.id.historyText);

        id = getIntent().getExtras().getInt("id");


        String[] projection = new String[] {"name", "information", "history"};

        Cursor cursor = resolver.query(CONTENT_URL, projection, "id = " + id, null, null);

        if(cursor != null) {
            if(cursor.moveToFirst()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String information = cursor.getString(cursor.getColumnIndex("information"));
                String history = cursor.getString(cursor.getColumnIndex("history"));

                nameText.setText(name, TextView.BufferType.EDITABLE);
                informationText.setText(information, TextView.BufferType.EDITABLE);
                historyText.setText(history, TextView.BufferType.EDITABLE);
            }else{
                Toast.makeText(getApplicationContext(), R.string.toast_no_record, Toast.LENGTH_LONG).show();
            }
        }

    }

    public void addRecord(View v) {
        String name = nameText.getText().toString();
        String history = historyText.getText().toString();
        String information = informationText.getText().toString();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("history", history);
        values.put("information", information);

        resolver.update(CONTENT_URL, values, "id = ? ", new String[]{String.valueOf(id)});

        Toast.makeText(getApplicationContext(), R.string.toast_modify_company, Toast.LENGTH_SHORT).show();

        finish();
    }

    public void cancelRecord(View v) {
        finish();
    }
}
