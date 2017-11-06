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


import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.favpageslist;
//import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.map;
import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.mappage;

/**
 * A simple {@link Fragment} subclass.
 */
public class PagesFavFragment extends Fragment {



    FavListAdapter adapter;
    KeyItem item;
    ListView lv;

    String tit_page;

    public PagesFavFragment(List<KeyItem> favpagelist)
    {
        //this.favlist = favpagelist;
        //Log.d("fav_page_items:", String.valueOf(favlist));
        favpageslist.clear();
        for(int i=0;i<favpagelist.size();i++){
            item = favpagelist.get(i);

            String name_fav = item.getItem_name();
            String id_fav = item.getItem_id();
            String img_fav = item.getItem_img();

            Log.d("name_dp", name_fav);
            Log.d("id_dp", id_fav);
            Log.d("img_dp", img_fav);



            Add_Fav_Page(item);



        }



    }



    public PagesFavFragment(){

    }

    @Override
    public void onResume() {
        super.onResume();

        adapter = new FavListAdapter(
                getActivity(),R.layout.test_button,favpageslist,"Pages"
        );
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);

    }

    public PagesFavFragment(SharedPreferences sharedPref, String title)
    {
        favpageslist.clear();
        if(sharedPref != null){

            Map<String,?> keys = sharedPref.getAll();
            for(Map.Entry<String,?> entry : keys.entrySet()){

                item = mappage.get(entry.getKey());
                tit_page = String.valueOf(entry.getValue());
                Log.d("tit_vibs",tit_page);
                Log.d("key_page",entry.getKey());

//
                    if(item!=null){

                        if(tit_page.equals(item.getItem_title())) {

                            String name_fav = item.getItem_name();
                            String id = item.getItem_id();
                            String img_fav = item.getItem_img();

                            Log.d("name_page", name_fav);
                            Log.d("id_page", id);
                            Log.d("img_page", img_fav);

                            Add_Fav_Page(item);
                        }

                    }

            }
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        final View rootView = inflater.inflate(R.layout.fragment_pages_fav, container, false);

        lv = (ListView)rootView.findViewById(R.id.lv_pagesfav);
        Log.d("yooooaavii gayooooooo", String.valueOf(favpageslist));

        if(favpageslist != null){
            Log.d("aavii gayooooooo", String.valueOf(favpageslist));
            adapter = new FavListAdapter(
                    getActivity(),R.layout.test_button,favpageslist,"Pages"
            );
            adapter.notifyDataSetChanged();
            lv.setAdapter(adapter);
        }




        return rootView;
    }

    private void Add_Fav_Page(KeyItem item)
    {


        favpageslist.add(item);


    }

}
