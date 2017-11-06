package com.example.dhairyapujara.hw9_final;

/**
 * Created by Dhairya Pujara on 15-04-2017.
 */

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.dhairyapujara.hw9_final.adapter.ViewPagerAdapter;
import com.example.dhairyapujara.hw9_final.fragment.EventsFragment;
import com.example.dhairyapujara.hw9_final.fragment.GroupsFragment;
import com.example.dhairyapujara.hw9_final.fragment.PagesFragment;
import com.example.dhairyapujara.hw9_final.fragment.PlacesFragment;
import com.example.dhairyapujara.hw9_final.fragment.UsersFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    Toolbar tool;
    TabLayout tab;
    ViewPager viewPage;
    ViewPagerAdapter viewPagerAdapter;

    public static List<KeyItem> prolist = new ArrayList<>();
    public static HashMap<String,KeyItem> map =  new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }



        String res_user = (String) getIntent().getExtras().get("json_user");
        String res_page = (String) getIntent().getExtras().get("json_page");
        String res_event = (String) getIntent().getExtras().get("json_event");
        String res_place = (String) getIntent().getExtras().get("json_place");
        String res_group = (String) getIntent().getExtras().get("json_group");

















        /*tool = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tool);*/

        tab = (TabLayout)findViewById(R.id.tabLayout_result);
        viewPage = (ViewPager)findViewById(R.id.viewPager_result);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragments(new UsersFragment(res_user,"Users"),"Users");
        viewPagerAdapter.addFragments(new PagesFragment(res_page,"Pages"),"Pages");
        viewPagerAdapter.addFragments(new EventsFragment(res_event,"Events"),"Events");
        viewPagerAdapter.addFragments(new PlacesFragment(res_place,"Places"),"Places");
        viewPagerAdapter.addFragments(new GroupsFragment(res_group,"Groups"),"Groups");


        viewPage.setAdapter(viewPagerAdapter);
        viewPage.setOffscreenPageLimit(5);
        tab.setupWithViewPager(viewPage);


        tab.getTabAt(0).setIcon(R.drawable.users);
        tab.getTabAt(1).setIcon(R.drawable.pages);
        tab.getTabAt(2).setIcon(R.drawable.events);
        tab.getTabAt(3).setIcon(R.drawable.places);
        tab.getTabAt(4).setIcon(R.drawable.groups);

        tab.getTabAt(0).setText(viewPagerAdapter.getPageTitle(0));
        tab.getTabAt(1).setText(viewPagerAdapter.getPageTitle(1));
        tab.getTabAt(2).setText(viewPagerAdapter.getPageTitle(2));
        tab.getTabAt(3).setText(viewPagerAdapter.getPageTitle(3));
        tab.getTabAt(4).setText(viewPagerAdapter.getPageTitle(4));




        //tab.addOnTabSelectedListener(this);

        /*String res = (String) getIntent().getExtras().get("json_data");
        //Log.d("res_bijiact",res);

        JSONArray jaar1 = null;
        JSONArray jaar2 = null;
        JSONObject jobj = null;
        JSONObject jobj2 = null;
        JSONObject jobj_pic = null;
        JSONObject jobj_pic_data = null;



        try {
            jaar1 = new JSONArray(res);
            jobj = jaar1.getJSONObject(0);
            jaar2 = jobj.getJSONArray("data");
            //Log.d("Data_obj", String.valueOf(jaar2));

            for(int i=0;i<10;i++) {
                jobj2 = jaar2.getJSONObject(i);
                String name = jobj2.getString("name");
                String id = jobj2.getString("id");
                //Log.d("Fetched_name:",name);

                jobj_pic = (JSONObject) jobj2.get("picture");
                jobj_pic_data = jobj_pic.getJSONObject("data");

                String img = jobj_pic_data.getString("url");
                //Log.d("Fetched_pic_obj", String.valueOf(jobj_pic));
                //Log.d("Fetched_pic_data_obj", String.valueOf(jobj_pic_data));
                //Log.d("img_che:",img);

                add_Product(id,name,img);



            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        /*String favorites_off = "favorites_off";
        String details = "details";

        try {
            InputStream stream = getAssets().open(favorites_off + ".png");
            Drawable d = Drawable.createFromStream(stream,null);
        } catch (IOException e) {
            e.printStackTrace();
        }*/



        /*ProductListAdapter adapter = new ProductListAdapter(
                this,R.layout.activity_result_button,prolist
        );
        ListView lv = (ListView)findViewById(R.id.lv_chu);
        lv.setAdapter(adapter);*/






    }


    /*private void add_Product(String id, String name, String img)
    {
        KeyItem item = new KeyItem(id,name,img);
        prolist.add(item);
        map.put(id,item);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }





}


 /*bundle = new Bundle();
                bundle.putString("page", res);

                PagesFragment pf = new PagesFragment();
                pf.setArguments(bundle);*/