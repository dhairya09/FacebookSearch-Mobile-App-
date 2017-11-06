package com.example.dhairyapujara.hw9_final.Favorite_Fragment;





import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.ListView;

import com.example.dhairyapujara.hw9_final.Config;
import com.example.dhairyapujara.hw9_final.DetailActivity;
import com.example.dhairyapujara.hw9_final.KeyItem;
import com.example.dhairyapujara.hw9_final.R;
import com.example.dhairyapujara.hw9_final.RequestHandler;
import com.example.dhairyapujara.hw9_final.adapter.FavListAdapter;
import com.example.dhairyapujara.hw9_final.adapter.ProductListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.favuserslist;
//import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.map;
import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.mapuser;


/**
 * A simple {@link Fragment} subclass.
 */
public class UsersFavFragment extends Fragment {


    FavListAdapter adapter;
    KeyItem item;
    ListView lv;
    String tit_users;




    //SharedPreferences pref = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    //SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
    //SharedPreferences pref = this.getActivity().getSharedPreferences();

    //SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);

    //SharedPreferences sharedPref = getActivity().getSharedPreferences("Users", Context.MODE_PRIVATE);






    public UsersFavFragment(List<KeyItem> favuserlist) {

        favuserslist.clear();

        for(int i=0;i<favuserlist.size();i++){
            item = favuserlist.get(i);
            Add_Fav_Page(item);





        }
    }

    public UsersFavFragment()
    {
        Log.d("majaa:","bhumi");

        Log.d("Act_majaa:", String.valueOf(getActivity()));
        Log.d("Cont_majaa:", String.valueOf(getContext()));


    }

    public UsersFavFragment(SharedPreferences sharedPref, String title)
    {
        favuserslist.clear();

        if(sharedPref != null){

            Map<String,?> keys = sharedPref.getAll();
            for(Map.Entry<String,?> entry : keys.entrySet()){

                item = mapuser.get(entry.getKey());
                tit_users = String.valueOf(entry.getValue());
                Log.d("tit_ats",tit_users);
                Log.d("key_user",entry.getKey());


                    if(item!=null){
                        if(tit_users.equals(item.getItem_title())) {

                            String name_fav = item.getItem_name();
                            String id = item.getItem_id();
                            String img_fav = item.getItem_img();

                            Log.d("name_dp", name_fav);
                            Log.d("id_dp", id);
                            Log.d("img_dp", img_fav);

                            Add_Fav_Page(item);
                        }

                    }




            }
            //onResume();
        }
    }

    @Override
    public void onResume() {
        super.onResume();



        adapter = new FavListAdapter(
                getActivity(),R.layout.test_button,favuserslist,"Users"
        );
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_users_fav, container, false);

        lv = (ListView)rootView.findViewById(R.id.lv_usersfav);


        Log.d("yooooaavii gayooooooo", String.valueOf(favuserslist));

        if(favuserslist != null){
            Log.d("aavii gayooooooo", String.valueOf(favuserslist));
            Log.d("Activity:", String.valueOf(getActivity()));
            adapter = new FavListAdapter(
                    getActivity(),R.layout.test_button,favuserslist,"Users"
            );
            adapter.notifyDataSetChanged();
            lv.setAdapter(adapter);
        }

        return rootView;
    }

    private void Add_Fav_Page(KeyItem item)
    {
        favuserslist.add(item);
    }



}
