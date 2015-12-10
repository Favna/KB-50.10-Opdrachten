package com.group10.companies;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Stefan on 2015-12-08.
 */
public class Office extends ListFragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Add row to database
        DatabaseHelper DBHelper = new DatabaseHelper(getActivity());
        SQLiteDatabase writedb = DBHelper.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put("telephonenumber", "012111111111");
        initialValues.put("city", "Shitty");
        initialValues.put("address", "Town 1");
        initialValues.put("longitude", "412");
        initialValues.put("latitude", "123");
        writedb.insert("office", null, initialValues);

        //Set listview
        SQLiteDatabase readdb = DBHelper.getReadableDatabase();
        Cursor c = readdb.query("office", new String[]{"id", "telephonenumber", "city", "address", "longitude", "latitude"}, null, null, null, null, null, null);

        ArrayList<String> values = new ArrayList<>();
        while(c.moveToNext()) {
            values.add(c.getString(0) + " " + c.getString(1) + " " + c.getString(2) + " " + c.getString(3) + " " + c.getString(4) + " " + c.getString(5));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        TextView c = (TextView) v;
        String text = c.getText().toString();
        int row_id = Integer.valueOf(text.substring(0, text.indexOf(" ")));
        //Toast.makeText(getActivity(), "id = " + row_id, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getActivity(), Detail.class);
        i.putExtra("id", row_id);
        startActivity(i);
    }
}
