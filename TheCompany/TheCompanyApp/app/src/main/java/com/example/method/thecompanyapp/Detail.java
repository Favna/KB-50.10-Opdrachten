package com.example.method.thecompanyapp;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Detail extends AppCompatActivity {

    String name, number, address;
    TextView tv_number, tv_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = getIntent().getStringExtra("name");
        number = getIntent().getStringExtra("number");
        address = getIntent().getStringExtra("address");

        setText();
    }

    private void setText() {
        tv_number = (TextView) findViewById(R.id.txt_number);
        tv_address = (TextView) findViewById(R.id.txtAddress);
        tv_number.setText(number);
        tv_address.setText(address);
    }

    public void call(View v) {
        Intent intentCall = new Intent(Intent.ACTION_DIAL);
        intentCall.setData(Uri.parse("tel:" + "0" + number));
        startActivity(intentCall);
    }

    public void map(View v) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + name);
        Intent intentMap = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        intentMap.setPackage("com.google.android.apps.maps");
        startActivity(intentMap);
    }
}
