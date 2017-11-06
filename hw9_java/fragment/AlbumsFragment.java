package com.example.dhairyapujara.hw9_final.fragment;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.dhairyapujara.hw9_final.KeyItem;
import com.example.dhairyapujara.hw9_final.R;
import com.example.dhairyapujara.hw9_final.adapter.ExpandableListAdapter;
import com.example.dhairyapujara.hw9_final.adapter.ProductListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumsFragment extends Fragment {


    String user_alb="";
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader = new ArrayList<>();

    HashMap<String, List<String>> listDataChild = new HashMap<>();
    TextView tv;
    int f = 0;

    public AlbumsFragment(String res_albums)
    {
        user_alb = res_albums;

        //Log.d("alb_fra:",user_alb);

    }

    public AlbumsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display_Albums ob = new Display_Albums();
        ob.execute();
    }

    public class Display_Albums extends AsyncTask<String,String,String> {



        @Override
        protected String doInBackground(String... strings) {

            //Log.d("do_in_back:",user_res);

            JSONArray jaar3 = null;
            JSONArray jaar2 = null;
            JSONObject jobj = null;
            JSONObject jobj2 = null;
            JSONObject jobj3 = null;
            JSONObject jobj4 = null;
            JSONObject jobj_pic = null;
            JSONObject jobj_pic_data = null;

            try {
                jobj = new JSONObject(user_alb);
                //Log.d("Rasoi", String.valueOf(jobj));

                //if(jobj.has("albums")){
                    jobj2 = jobj.getJSONObject("albums");
                    jaar2 = jobj2.getJSONArray("data");

                    for (int i = 0; i < jaar2.length(); i++) {
                        jobj3 = jaar2.getJSONObject(i);
                        String name = jobj3.getString("name");

                        listDataHeader.add(name);
                        List<String> img_child = new ArrayList<>();

                        //if(jobj3.has("photos")){

                        try {

                            jobj_pic = (JSONObject) jobj3.get("photos");
                            jaar3 = jobj_pic.getJSONArray("data");

                            for (int j = 0; j < jaar3.length(); j++) {
                                jobj4 = jaar3.getJSONObject(j);

                                String pic_id = jobj4.getString("id");
                                img_child.add("https://graph.facebook.com/v2.8/" + pic_id + "/picture?access_token=EAAY6p7po1b8BAPqJZCGlIDzQhuZAkwmgZBTFhgfEKOEVOjAjIlxKTfFIHI8lJ1XvMFMP1T1Uxf70wMWZBmcXQwt3nYR60QCi0KrZC5fMjohU45rqfaeW37fvaGNZAYZCOpXj0ZB5h4L1cx4xEbjkY1HLBZCwC4JG12vEZD");

                            }
                            listDataChild.put(listDataHeader.get(i), img_child);

                        }
                        catch (Exception e){
                            Log.d("fail alb na photos","hat yaar");
                            f=2;
                            break;
                        }

                        /*}
                        else{
                            Log.d("listdatahead_val:", String.valueOf(listDataHeader));
                            Log.d("listdatachild_val:", String.valueOf(listDataChild));
                        }*/

                    }
                //}

            }
             catch (JSONException e) {
                //e.printStackTrace();
               Log.d("fail alb","yhayu yaar");
                 f=1;


            }




            return null;
        }

    }
//105652159469464

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView;
        rootView = inflater.inflate(R.layout.fragment_albums, container, false);
        tv = (TextView)rootView.findViewById(R.id.noalb);

        if(f == 2){
            tv.setText("No Album photos available to display");
        }



        //if(!listDataHeader.isEmpty() || !listDataChild.isEmpty()){
          if(f == 1){
              tv.setText("No albums available to display");
              //f = 0;
          }
          else{



              expListView = (ExpandableListView) rootView.findViewById(R.id.lvExp);
              listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
              expListView.setAdapter(listAdapter);

              expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                  int previousItem = -1;

                  @Override
                  public void onGroupExpand(int groupPosition) {
                      if (groupPosition != previousItem)
                          expListView.collapseGroup(previousItem);
                      previousItem = groupPosition;
                  }
              });
          }



        /*}
        else{
            Log.d("sacho_alb_head:", String.valueOf(listDataHeader));
            Log.d("sacho_alb_child:", String.valueOf(listDataChild));
            rootView = inflater.inflate(R.layout.no_alb, container, false);
        }*/




        return rootView;
    }

}
