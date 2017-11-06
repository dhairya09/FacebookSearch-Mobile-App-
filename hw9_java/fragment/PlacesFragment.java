package com.example.dhairyapujara.hw9_final.fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.dhairyapujara.hw9_final.Config;
import com.example.dhairyapujara.hw9_final.DetailActivity;
import com.example.dhairyapujara.hw9_final.KeyItem;
import com.example.dhairyapujara.hw9_final.R;
import com.example.dhairyapujara.hw9_final.RequestHandler;
import com.example.dhairyapujara.hw9_final.adapter.ProductListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.map;
import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.mapplace;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlacesFragment extends Fragment implements View.OnClickListener {

    FragmentManager manager;
    FragmentTransaction ft;
    ListView lv;


    String user_place="";
    String title;
    public static List<KeyItem> prolist = new ArrayList<>();

    ProductListAdapter adapter;
    KeyItem item;
    int start = 0;
    int count = 0;
    Button prev;
    Button next;

    public PlacesFragment() {
        // Required empty public constructor
    }

    public PlacesFragment(String res_place,String tab)
    {
        user_place = res_place;
        this.title = tab;
        prolist.clear();

    }

    public static void refresh(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display_Da ob = new Display_Da();
        ob.execute();


    }

    @Override
    public void onResume() {
        super.onResume();
        adapter = new ProductListAdapter(
                getActivity(),R.layout.test_button,prolist,title
        );
        adapter.notifyDataSetChanged();

        lv.setAdapter(adapter);

    }

    public class Display_Da extends AsyncTask<String,String,String> {



        @Override
        protected String doInBackground(String... strings) {

            //Log.d("do_in_back:",user_res);

            JSONArray jaar1 = null;
            JSONArray jaar2 = null;
            JSONObject jobj = null;
            JSONObject jobj2 = null;
            JSONObject jobj_pic = null;
            JSONObject jobj_pic_data = null;

            try {
                jobj = new JSONObject(user_place);
                jaar2 = jobj.getJSONArray("data");
                Log.d("length:", String.valueOf(jaar2.length()));
                //Log.d("Data_obj", String.valueOf(jaar2));
                while(start < jaar2.length()){


                    if(count == 10){
                        count = 0;
                        break;
                    }
                    count++;
                    jobj2 = jaar2.getJSONObject(start);
                    String name = jobj2.getString("name");
                    String id = jobj2.getString("id");
                    //Log.d("Fetched_name:",name);

                    jobj_pic = (JSONObject) jobj2.get("picture");
                    jobj_pic_data = jobj_pic.getJSONObject("data");

                    String img = jobj_pic_data.getString("url");
                    //Log.d("Fetched_pic_obj", String.valueOf(jobj_pic));
                    //Log.d("Fetched_pic_data_obj", String.valueOf(jobj_pic_data));
                    //Log.d("img_che:",img);

                    add_Product(id,name,img,title);
                    start++;


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }




            return String.valueOf(jaar2.length());
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            int l = Integer.parseInt(s);
            if(l <= 10){
                Log.d("nnn", String.valueOf(l)
                );
                next.setEnabled(false);
            }



        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_places, container, false);

        prev = (Button)rootView.findViewById(R.id.Prev_Place);
        next = (Button)rootView.findViewById(R.id.Next_Place);
        prev.setOnClickListener(this);
        next.setOnClickListener(this);

        lv = (ListView)rootView.findViewById(R.id.lv_places);
        adapter = new ProductListAdapter(
                getActivity(),R.layout.test_button,prolist,title
        );
        adapter.notifyDataSetChanged();

        lv.setAdapter(adapter);
        //lv.setOnItemClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view)
    {
        if(view == prev){
            UsersFragment.refresh();
            Log.d("start_prev:", String.valueOf(start));
            Log.d("count_prev:", String.valueOf(count));
            start -= (20 - count);
            Log.d("start_prev_pachi:", String.valueOf(start));
            prolist.clear();
            count = 0;

            if(start >= 0){
                if(start == 0){
                    prev.setEnabled(false);
                }
                else{
                    prev.setEnabled(true);
                }

                next.setEnabled(true);
                Display_Da ob = new Display_Da();
                ob.execute();
            }
            else {
                next.setEnabled(true);
                prev.setEnabled(false);
            }




        }

        if(view == next){
            PlacesFragment.refresh();
            Log.d("start_next:", String.valueOf(start));
            Log.d("count_next:", String.valueOf(count));

            //start+= count;

            prolist.clear();
            if(start <= 20){
                if(start == 20){
                    next.setEnabled(false);
                }
                else{
                    next.setEnabled(true);
                }

                prev.setEnabled(true);

                Display_Da ob = new Display_Da();
                ob.execute();
            }
            else{
                prev.setEnabled(true);
                next.setEnabled(false);
            }




        }

        adapter = new ProductListAdapter(
                getActivity(),R.layout.test_button,prolist,title
        );
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);

        /*manager = getActivity().getSupportFragmentManager();
        Fragment fragment = PlacesFragment.this;
        ft = manager
                .beginTransaction()
                .detach(fragment)
                .attach(fragment);

        ft.commit();*/
    }



    private void add_Product(String id, String name, String img, String title)
    {
        //Log.d("naam_hai:",name);

        KeyItem item = new KeyItem(id,name,img,title);
        prolist.add(item);
        mapplace.put(id,item);
        Log.d("mapplace_len:", String.valueOf(mapplace.size()));
    }

}
