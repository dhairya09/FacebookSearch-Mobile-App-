package com.example.dhairyapujara.hw9_final.Favorite_Fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.dhairyapujara.hw9_final.KeyItem;
import com.example.dhairyapujara.hw9_final.R;
import com.example.dhairyapujara.hw9_final.adapter.FavListAdapter;
import com.example.dhairyapujara.hw9_final.adapter.ProductListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.favplaceslist;
//import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.map;
import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.mapplace;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlacesFavFragment extends Fragment {


    FavListAdapter adapter;
    KeyItem item;
    ListView lv;
    String tit_place;

    public PlacesFavFragment(List<KeyItem> favplacelist)
    {
        favplaceslist.clear();
        for(int i=0;i<favplacelist.size();i++){
            item = favplacelist.get(i);

            String name_fav = item.getItem_name();
            String id_fav = item.getItem_id();
            String img_fav = item.getItem_img();

            Add_Fav_Page(item);



        }
    }



    public PlacesFavFragment(){

    }

    public PlacesFavFragment(SharedPreferences sharedPref, String title)
    {
        favplaceslist.clear();
        if(sharedPref != null){

            Map<String,?> keys = sharedPref.getAll();
            for(Map.Entry<String,?> entry : keys.entrySet()){

                item = mapplace.get(entry.getKey());
                tit_place = String.valueOf(entry.getValue());
                Log.d("tit_dhairs",tit_place);
                Log.d("key_place",entry.getKey());


                if(item!=null){
                    if(tit_place.equals(item.getItem_title())) {

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
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter = new FavListAdapter(
                getActivity(),R.layout.test_button,favplaceslist,"Places"
        );
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_places_fav, container, false);

        lv = (ListView)rootView.findViewById(R.id.lv_placesfav);
        Log.d("yooooaavii gayooooooo", String.valueOf(favplaceslist));

        if(favplaceslist != null){
            Log.d("aavii gayooooooo", String.valueOf(favplaceslist));
            adapter = new FavListAdapter(
                    getActivity(),R.layout.test_button,favplaceslist,"Places"
            );
            adapter.notifyDataSetChanged();
            lv.setAdapter(adapter);
        }






        return rootView;
    }

    private void Add_Fav_Page(KeyItem item)
    {

        favplaceslist.add(item);
    }

}
