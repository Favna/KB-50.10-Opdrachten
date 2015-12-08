package com.example.method.test2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    /*
     * Used for CRUD activities.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void addCompany(View v)
    {
        Intent intent = new Intent(this, AddCompany.class);
        startActivity(intent);
    }

    public void modifyCompany(View v)
    {
        System.out.println("modCompany");
    }

    public void deleteCompany(View v)
    {
        Intent intent = new Intent(this, DeleteCompany.class);
        startActivity(intent);
    }

}
