package com.example.method.test;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText contactNameEditText;
    // TODO: String.xml
    // TODO: AddCity & AddCompany automatisch doen indien provider gecalled wordt door een andere applicatie.
    // Omdat MainActivity nooit gecalled wordt door andere applicaties die gebruik maken van providers.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast toast = Toast.makeText(getApplicationContext(), "Service is running...", Toast.LENGTH_LONG);
        toast.show();
        addCity();
        addCompany();
        Toast.makeText(getApplicationContext(), "Service is done running...", Toast.LENGTH_LONG).show();
        finish();
    }

    public void addCompany() {
        // Stores a key value pair
        ContentValues values = new ContentValues();
        values.put(com.example.method.test.CompanyProvider.name, "TestComp");
        values.put(com.example.method.test.CompanyProvider.history, "TestHist");
        values.put(com.example.method.test.CompanyProvider.information, "TestInfo");

        Uri uri = getContentResolver().insert(com.example.method.test.CompanyProvider.CONTENT_URL, values);
    }

    public void addCity() {
        ContentValues values = new ContentValues();
        values.put(com.example.method.test.CityProvider.name, "Utrecht");
        values.put(com.example.method.test.CityProvider.number, 640539581);
        values.put(com.example.method.test.CityProvider.address, "TestAddressUtrecht");
        Uri uri = getContentResolver().insert(com.example.method.test.CityProvider.CONTENT_URL, values);

        values.put(com.example.method.test.CityProvider.name, "Rotterdam");
        values.put(com.example.method.test.CityProvider.number, 640539581);
        values.put(com.example.method.test.CityProvider.address, "TestAddressRotterdam");
        Uri uri2 = getContentResolver().insert(com.example.method.test.CityProvider.CONTENT_URL, values);

        values.put(com.example.method.test.CityProvider.name, "The Hague");
        values.put(com.example.method.test.CityProvider.number, 640539581);
        values.put(com.example.method.test.CityProvider.address, "TestAddressTheHague");
        Uri uri3 = getContentResolver().insert(com.example.method.test.CityProvider.CONTENT_URL, values);
    }
}
