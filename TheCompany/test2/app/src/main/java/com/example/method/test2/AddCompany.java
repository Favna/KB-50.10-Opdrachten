package com.example.method.test2;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddCompany extends AppCompatActivity {

    static final String PROVIDER_NAME = "com.example.method.test.CompanyProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/cpcontacts";
    static final Uri CONTENT_URL = Uri.parse(URL);

    ContentResolver resolver;
    EditText nameText, historyText, informationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company);

        resolver = getContentResolver();

        nameText = (EditText) findViewById(R.id.nameText);
        historyText = (EditText) findViewById(R.id.historyText);
        informationText = (EditText) findViewById(R.id.informationText);
    }

    public void addRecord(View v) {
        String name = nameText.getText().toString();
        String history = historyText.getText().toString();
        String information = informationText.getText().toString();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("history", history);
        values.put("information", information);

        resolver.insert(CONTENT_URL, values);

        Toast.makeText(getApplicationContext(), "Company Added", Toast.LENGTH_SHORT).show();
    }

    public void cancelRecord(View v) {
        finish();
    }
}
