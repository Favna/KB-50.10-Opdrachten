package com.example.method.thecompanyapp;


import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OfficeFragment extends Fragment {
    // The Java namespace for the Content Provider
    static final String PROVIDER_NAME = "com.example.method.test.CityProvider";

    // Assigned to a content provider so any application can access it
    // cpcontacts is the virtual directory in the provider
    static final String URL = "content://" + PROVIDER_NAME + "/city";
    static final Uri CONTENT_URL = Uri.parse(URL);

    ContentResolver resolver;
    View view;
    ListView listView;
    List<String> list, listNumber, listAddress; // TODO: One list, combined String. Desect string later into seperate words
    int save = -1;

    public OfficeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_office, container, false);
        resolver = view.getContext().getContentResolver();

        listView = (ListView) view.findViewById(R.id.listView);
        getCities();

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
                String itemValue = list.get(position).toString();

                Intent intent = new Intent(view.getContext(), Detail.class);
                if(list.size() != 0)
                    intent.putExtra("name", list.get(position).toString());
                if(listNumber.size() != 0)
                    intent.putExtra("number", listNumber.get(position).toString());
                if(listAddress.size() != 0)
                    intent.putExtra("address", listAddress.get(position).toString());
                startActivityForResult(intent, 1);
            }});


        return view;
    }

    public void getCities() {
        String[] projection = new String[] {"name", "number", "address"};

        Cursor cursor = resolver.query(CONTENT_URL, projection, null, null, null);

        list = new ArrayList<String>();
        listNumber = new ArrayList<String>();
        listAddress = new ArrayList<String>();

        if(cursor == null )
            Toast.makeText(view.getContext().getApplicationContext(), R.string.toast_no_record, Toast.LENGTH_LONG).show();

        if(cursor != null) {
            if(cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String number = cursor.getString(cursor.getColumnIndex("number"));
                    String address = cursor.getString(cursor.getColumnIndex("address"));

                    list.add(name);
                    listNumber.add(number);
                    listAddress.add(address);
                } while(cursor.moveToNext());
            }

            cursor.close();
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, list);
        listView.setAdapter(adapter);

    }

}
