package com.example.dhairyapujara.hw9_final;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AboutFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //final View rootView = inflater.inflate(R.layout.activity_about_fragment,container,false);
        ((MainActivity) getActivity()).setActionBarTitle("About Me");

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);

        return inflater.inflate(R.layout.activity_about_fragment,container,false);
    }


}
