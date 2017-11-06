package com.example.dhairyapujara.hw9_final;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {


    Button search;
    Button clear;
    EditText key;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);

        ((MainActivity) getActivity()).setActionBarTitle("Search on FB");

        search = (Button) rootView.findViewById(R.id.Btn_Search);
        clear = (Button) rootView.findViewById(R.id.Btn_Clear);
        key = (EditText) rootView.findViewById(R.id.keyword);

        search.setOnClickListener(this);
        clear.setOnClickListener(this);

        return rootView;


    }

    @Override
    public void onClick(View view) {
        if (view == search) {
            String keyword = key.getText().toString().trim();
            if(keyword.isEmpty() || keyword == "" || keyword == null){
                Toast.makeText(getActivity(),"Please enter a keyword!",Toast.LENGTH_LONG).show();
            }
            else{
                Log.d("dp", keyword);
                Fetch_Data(keyword);
            }

        }

        if (view == clear) {
            key.setText("");
        }
    }

    private void Fetch_Data(final String keyword) {
        class FetchData extends AsyncTask<Object, Object, ArrayList<String>> {

            HashMap<String, String> params1 = new HashMap<String, String>();
            HashMap<String, String> params2 = new HashMap<String, String>();
            HashMap<String, String> params3 = new HashMap<String, String>();
            HashMap<String, String> params4 = new HashMap<String, String>();
            HashMap<String, String> params5 = new HashMap<String, String>();

            @Override
            protected ArrayList<String> doInBackground(Object... voids) {

                ArrayList<String> al = new ArrayList<>();

                params1.put(Config.KEY_SEARCH, keyword);
                params1.put(Config.KEY_TYPE,"user");

                params2.put(Config.KEY_SEARCH, keyword);
                params2.put(Config.KEY_TYPE,"page");

                params3.put(Config.KEY_SEARCH, keyword);
                params3.put(Config.KEY_TYPE,"event");

                params4.put(Config.KEY_SEARCH, keyword);
                params4.put(Config.KEY_TYPE,"place");

                params5.put(Config.KEY_SEARCH, keyword);
                params5.put(Config.KEY_TYPE,"group");




                RequestHandler rh = new RequestHandler();


                String res_user = rh.performPostCall(Config.URL_GET_DATA, params1);
                String res_page = rh.performPostCall(Config.URL_GET_DATA, params2);
                String res_event = rh.performPostCall(Config.URL_GET_DATA, params3);
                String res_place = rh.performPostCall(Config.URL_GET_DATA, params4);
                String res_group = rh.performPostCall(Config.URL_GET_DATA, params5);

                al.add(res_user);
                al.add(res_page);
                al.add(res_event);
                al.add(res_place);
                al.add(res_group);

                return al;


            }

            @Override
            protected void onPreExecute() {

            }

            @Override
            protected void onPostExecute(ArrayList<String> al) {

                 //Log.d("response", String.valueOf(s));
                 Intent in = new Intent(getActivity(),ResultActivity.class);
                 in.putExtra("json_user",al.get(0));
                 in.putExtra("json_page",al.get(1));
                 in.putExtra("json_event",al.get(2));
                 in.putExtra("json_place",al.get(3));
                 in.putExtra("json_group",al.get(4));

                 startActivity(in);


                //Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
            }


        }


        FetchData ob = new FetchData();
        ob.execute();

    }



}
