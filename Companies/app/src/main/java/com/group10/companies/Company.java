package com.group10.companies;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Company extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        instantiateDB();
        createGallery();
        //Set image gallery
        //ImageView imageView = (ImageView) findViewById(R.id.gallery);
        //imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
    }
    public void createGallery()
    {
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);
    }
    public void instantiateDB()
    {
        //Add row to database
        DatabaseHelper DBHelper = new DatabaseHelper(this);
        SQLiteDatabase writedb = DBHelper.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        //initialValues.put("id", 1);
        initialValues.put("name", "Pete");
        initialValues.put("history", "Blub");
        initialValues.put("website", "http://website.nl");
        initialValues.put("telephonenumber", "0123456789");
        initialValues.put("city", "City");
        initialValues.put("address", "Address 1");
        initialValues.put("longitude", "1234");
        initialValues.put("latitude", "4321");
        writedb.insert("company", null, initialValues);

        //Add pictures
        ContentValues initialPicture1 = new ContentValues();
        initialValues.put("company_id", "1");
        initialValues.put("url", "");
        writedb.insert("company", null, initialValues);

        //Delete row
        //db.delete("company", "id = 1", null);

        //SELECT
        SQLiteDatabase readdb = DBHelper.getReadableDatabase();
        Cursor c = readdb.query("company", new String[]{"id", "name", "history", "website", "telephonenumber", "city", "address", "longitude", "latitude"}, "id = 1", null, null, null, null, null);
        c.moveToFirst();
        TextView companyName = (TextView) findViewById(R.id.company_name);
        companyName.setText(c.getString(1));
        TextView companyHistory = (TextView) findViewById(R.id.company_history);
        companyHistory.setText(c.getString(2));
        TextView companyUrl = (TextView) findViewById(R.id.company_url);
        companyUrl.setText(c.getString(3));
    }
    public void openWeb(View v){
        TextView text = (TextView)v;
        Intent i = new Intent("android.intent.action.VIEW");
        i.setData(Uri.parse(text.getText().toString()));
        startActivity(i);
    }
}
