package com.example.dhairyapujara.hw9_final.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.dhairyapujara.hw9_final.KeyItem;
import com.example.dhairyapujara.hw9_final.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




import com.example.dhairyapujara.hw9_final.PostItem;

import com.example.dhairyapujara.hw9_final.adapter.PostListAdapter;

//import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.map;
import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.mapevent;
import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.mapgroup;
import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.mappage;
import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.mapplace;
import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.mapuser;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostsFragment extends Fragment {


    String user_pos="";
    String id_che;
    //public static HashMap<String,KeyItem> map =  new HashMap<>();
    public static List<PostItem> postlist = new ArrayList<>();
    KeyItem item;

    String main_img;
    String main_naam;
    int f = 0;

    PostListAdapter adapter;
    TextView tv;



    public PostsFragment(String res_posts,String id,String tab_title)
    {
        postlist.clear();
        user_pos = res_posts;
        id_che = id;
        Log.d("id_che:",id_che);

        //item = map.get(id_che);
        switch(tab_title){
            case "Users":   item = mapuser.get(id_che);
                            break;
            case "Pages":   item = mappage.get(id_che);
                             break;
            case "Events":  item = mapevent.get(id_che);
                              break;
            case "Places":  item = mapplace.get(id_che);
                             break;

            case "Groups":  item = mapgroup.get(id_che);
                              break;
        }

        //Log.d("item_che:", String.valueOf(item));

        main_img = item.getItem_img();
        main_naam = item.getItem_name();

        //Log.d("main_naam_che:", String.valueOf(main_naam));
        //Log.d("main_img_che:", String.valueOf(main_img));


        //Log.d("pos_fra:",user_pos);

        //Log.d("id_aavigayo",id_che);

    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display_Posts ob = new Display_Posts();
        ob.execute();
    }

    class Display_Posts extends AsyncTask<String, String, String> {



            @Override
            protected String doInBackground(String... strings) {

                //Log.d("do_in_back:",user_res);

                JSONArray jaar1 = null;
                JSONArray jaar2 = null;
                JSONObject jobj = null;
                JSONObject jobj2 = null;
                JSONObject jobj3 = null;
                JSONObject jobj_pic = null;
                JSONObject jobj_pic_data = null;

                try {

                    jobj = new JSONObject(user_pos);

                    //if (jobj.has("posts")) {
                        jobj2 = jobj.getJSONObject("posts");

                        jaar2 = jobj2.getJSONArray("data");
                        //Log.d("length che:", String.valueOf(jaar2.length()));

                        for (int i = 0; i < jaar2.length(); i++) {
                            jobj3 = jaar2.getJSONObject(i);
                            String msg = "";
                            String time = "";

                            if (jobj3.has("message")) {
                                msg = jobj3.getString("message");
                            }

                            if (jobj3.has("created_time")) {
                                time = jobj3.getString("created_time");
                            }


                            //Log.d("Message:", msg);
                            //Log.d("Time:", time);
                            add_Posts(main_naam, main_img, time, msg);
                        }

                    //}


                } catch (JSONException e) {
                    //e.printStackTrace();
                    Log.d("fail post","yhayu yaar");
                    f=1;
                }


                return null;
            }

        }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View rootView;
        rootView = inflater.inflate(R.layout.fragment_posts, container, false);
        tv = (TextView)rootView.findViewById(R.id.nopos);


        //Log.d("postlist_val:", String.valueOf(postlist));
        //if(!postlist.isEmpty()){
            //Log.d("khoto_val:", String.valueOf(postlist));
        if(f == 1){
            tv.setText("No posts available to display");
        }
        else{


            adapter = new PostListAdapter(
                    getActivity(),R.layout.post_item_view,postlist
            );

            ListView lv = (ListView)rootView.findViewById(R.id.lv_posts);
            lv.setAdapter(adapter);
        }
        /*else{
            Log.d("sacho_val:", String.valueOf(postlist));
            rootView = inflater.inflate(R.layout.no_pos, container, false);
        }*/



        return rootView;
    }

    private void add_Posts(String name, String img, String time, String msg)
    {


        PostItem item = new PostItem(name,img,time,msg);
        postlist.add(item);
        //map.put(id,item);
    }

}
