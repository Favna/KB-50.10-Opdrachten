package com.example.method.test2;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DeleteCompany extends AppCompatActivity {

    static final String PROVIDER_NAME = "com.example.method.test.CompanyProvider";
    static final String URL = "content://" + PROVIDER_NAME + "/cpcontacts";
    static final Uri CONTENT_URL = Uri.parse(URL);

    ContentResolver resolver;
    ListView listView;
    int save = -1;
    String itemValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_company);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        resolver = getContentResolver();

        listView = (ListView) findViewById(R.id.listView);
        getCompany();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub

                parent.getChildAt(position).setBackgroundColor(
                        Color.parseColor("#A9BCF5"));

                if (save != -1 && save != position) {
                    parent.getChildAt(save).setBackgroundColor(
                            Color.parseColor("#d6e6ff"));
                }

                save = position;

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                itemValue = (String) listView.getItemAtPosition(position);

                // Retrieve ID
                if (itemValue.contains(" "))
                    itemValue = itemValue.substring(0, itemValue.indexOf(" "));

            }
        });
    }

    public void getCompany() {
        String[] projection = new String[] {"id", "name"};

        Cursor cursor = resolver.query(CONTENT_URL, projection, null, null, null);

        List<String> list = new ArrayList<String>();

        if(cursor == null )
            Toast.makeText(getApplicationContext(), R.string.toast_no_record, Toast.LENGTH_LONG).show();

        if(cursor != null) {
            if(cursor.moveToFirst()) {
                do {
                    String id = cursor.getString(cursor.getColumnIndex("id"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));

                    list.add(id + " " + name + "\n");
                } while(cursor.moveToNext());
            }
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, list);
        listView.setAdapter(adapter);
    }

    public void deleteCompany(View v){
        System.out.println(itemValue);
        long idDeleted = resolver.delete(CONTENT_URL, "id = ? ", new String[]{itemValue});
        getCompany();
        Toast.makeText(getApplicationContext(), R.string.toast_delete_company, Toast.LENGTH_LONG).show();
    }

    public void cancelRecord(View v) {
        finish();
    }

    public void modifyRecord(View v) {
        System.out.println(itemValue);
        if(itemValue == null) {
            Toast.makeText(getApplicationContext(), R.string.toast_no_selection, Toast.LENGTH_LONG).show();
        } else {
            Intent i = new Intent(this, ModifyCompany.class);
            i.putExtra("id", Integer.valueOf(itemValue));
            startActivity(i);
        }
    }
}
