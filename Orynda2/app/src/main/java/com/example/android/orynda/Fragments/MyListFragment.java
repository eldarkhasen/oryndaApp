package com.example.android.orynda.Fragments;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.orynda.R;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by админ on 20.07.2017.
 */

public class MyListFragment extends Fragment {


    public MyListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_fragment, container, false);

        TextView textView = (TextView) rootView.findViewById(R.id.list_title_lol);
        return rootView;
    }


}
