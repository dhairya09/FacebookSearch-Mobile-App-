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


import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.faveventslist;
//import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.map;
import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.mapevent;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFavFragment extends Fragment {



    FavListAdapter adapter;
    KeyItem item;
    ListView lv;
    String tit_event;

    public EventsFavFragment(List<KeyItem> faveventlist)
    {
        faveventslist.clear();
        for(int i=0;i<faveventlist.size();i++){
            item = faveventlist.get(i);

            String name_fav = item.getItem_name();
            String id_fav = item.getItem_id();
            String img_fav = item.getItem_img();

            Add_Fav_Page(item);



        }
    }

    public EventsFavFragment(SharedPreferences sharedPref, String title)
    {
        faveventslist.clear();
        if(sharedPref != null){

            Map<String,?> keys = sharedPref.getAll();
            for(Map.Entry<String,?> entry : keys.entrySet()){

                item = mapevent.get(entry.getKey());
                tit_event = String.valueOf(entry.getValue());
                Log.d("tit_Jains",tit_event);
                Log.d("key_event",entry.getKey());


                if(item!=null){
                    if(tit_event.equals(item.getItem_title())) {

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



    public EventsFavFragment(){

    }

    @Override
    public void onResume() {
        super.onResume();
        adapter = new FavListAdapter(
                getActivity(),R.layout.test_button,faveventslist,"Events"
        );
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootView = inflater.inflate(R.layout.fragment_events_fav, container, false);

         lv = (ListView)rootView.findViewById(R.id.lv_eventsfav);
        Log.d("yooooaavii gayooooooo", String.valueOf(faveventslist));

        if(faveventslist != null){
            Log.d("aavii gayooooooo", String.valueOf(faveventslist));
            adapter = new FavListAdapter(
                    getActivity(),R.layout.test_button,faveventslist,"Events"
            );
            adapter.notifyDataSetChanged();
            lv.setAdapter(adapter);
        }




        return rootView;
    }

    private void Add_Fav_Page(KeyItem item)
    {


        faveventslist.add(item);
    }

}
