package com.example.android.orynda.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.orynda.R;

/**
 * Created by админ on 20.07.2017.
 */

public class CompletedFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.completed_list_fragment, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.list_title_lol_completed);
        textView.setText("Completed tasks");
        return rootView;
    }

    public CompletedFragment() {
    }
}
