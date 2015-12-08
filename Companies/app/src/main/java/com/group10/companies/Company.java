package com.group10.companies;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Company extends Fragment {
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_company, container, false);
        instantiateDB();
        createGallery();
        return rootView;
    }

    public void createGallery()
    {
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);
        ImageAdapter adapter = new ImageAdapter(getActivity());
        viewPager.setAdapter(adapter);
    }

    public void instantiateDB()
    {
        //Add row to database
        DatabaseHelper DBHelper = new DatabaseHelper(getActivity());
        SQLiteDatabase writedb = DBHelper.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        //initialValues.put("id", 1);
        initialValues.put("name", "Pete");
        initialValues.put("history", "Blub");
        initialValues.put("website", "http://website.nl");
        writedb.insert("company", null, initialValues);

        //Add pictures
        ContentValues initialPicture1 = new ContentValues();
        initialPicture1.put("company_id", "1");
        initialPicture1.put("url", "");
        writedb.insert("company_picture", null, initialPicture1);

        //Delete row
        //db.delete("company", "id = 1", null);

        //SELECT
        SQLiteDatabase readdb = DBHelper.getReadableDatabase();
        Cursor c = readdb.query("company", new String[]{"id", "name", "history", "website"}, "id = 1", null, null, null, null, null);
        c.moveToFirst();
        TextView companyName = (TextView) rootView.findViewById(R.id.company_name);
        companyName.setText(c.getString(1));
        TextView companyHistory = (TextView) rootView.findViewById(R.id.company_history);
        companyHistory.setText(c.getString(2));
        TextView companyUrl = (TextView) rootView.findViewById(R.id.company_url);
        companyUrl.setText(c.getString(3));
    }

    public void openWeb(View v){
        TextView text = (TextView)v;
        Intent i = new Intent("android.intent.action.VIEW");
        i.setData(Uri.parse(text.getText().toString()));
        startActivity(i);
    }
}
