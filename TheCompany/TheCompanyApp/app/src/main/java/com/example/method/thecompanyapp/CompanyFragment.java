package com.example.method.thecompanyapp;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompanyFragment extends Fragment {

    // The Java namespace for the Content Provider
    static final String PROVIDER_NAME = "com.example.method.test.CompanyProvider";

    // Assigned to a content provider so any application can access it
    // cpcontacts is the virtual directory in the provider
    static final String URL = "content://" + PROVIDER_NAME + "/cpcontacts";
    static final Uri CONTENT_URL = Uri.parse(URL);

    ContentResolver resolver;
    TextView naamText, historyText, websiteText;

    private ArrayAdapter adapter;
    private View view;

    List<String> listNaam = new ArrayList<String>();
    List<String> listHistory = new ArrayList<String>();

    int pos = 0;

    public CompanyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //view = inflater.inflate(R.layout.fragment_blank, container, false);
        view = inflater.inflate(R.layout.fragment_blank, container,false);
        createGallery();
        naamText = (TextView) view.findViewById(R.id.naamText);
        historyText = (TextView) view.findViewById(R.id.historyText);
        websiteText = (TextView) view.findViewById(R.id.textView3);
        websiteText.setMovementMethod(LinkMovementMethod.getInstance());

        resolver = view.getContext().getContentResolver();

//        Button back = (Button) view.findViewById(R.id.btn_back);
//        Button forward = (Button) view.findViewById(R.id.btn_forward);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                back();
//            }
//        });
//        forward.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                forward();
//            }
//        });


        getCompany();

        return view;
    }
    public void createGallery()
    {
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        GalleryAdapter adapter = new GalleryAdapter(getActivity());
        viewPager.setAdapter(adapter);
    }
    public void getCompany() {
        String[] projection = new String[] {"name", "history"};

        Cursor cursor = resolver.query(CONTENT_URL, projection, null, null, null);

        if(cursor == null )
            Toast.makeText(view.getContext().getApplicationContext(), "No Records found", Toast.LENGTH_LONG).show();

        if(cursor != null ) {
            if(cursor.moveToFirst()) {
                do {
                    String naam = cursor.getString(cursor.getColumnIndex("name"));
                    String history = cursor.getString(cursor.getColumnIndex("history"));

                    listNaam.add(naam);
                    listHistory.add(history);

                } while(cursor.moveToNext());

                cursor.close();
            }
        }


        if(!listNaam.isEmpty())
            iterateList(0);
    }

    private void iterateList(int position) {
        // Slecht maar lui
        System.out.println(listNaam.size());
        System.out.println(pos);
        System.out.println(listNaam.get(position));
        System.out.println(listHistory.get(position));
        if(listNaam.isEmpty()) {
            naamText.setText("No company data found"); // TODO: String.xml
            historyText.setText("No company history found"); // TODO: String.xml
        } else {
            naamText.setText(listNaam.get(position));
            historyText.setText(listHistory.get(position));
        }
    }

    public void forward() {
        if(listNaam.size() != pos + 1) {
            pos++;
            iterateList(pos);
        } else
            Toast.makeText(view.getContext().getApplicationContext(), "No Record found", Toast.LENGTH_SHORT);
    }

    public void back() {
        if(pos - 1 != -1) {
            pos--;
            iterateList(pos);
        } else
            Toast.makeText(view.getContext().getApplicationContext(), "No Record found", Toast.LENGTH_SHORT);
    }

}
