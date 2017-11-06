package com.example.dhairyapujara.hw9_final;

import android.support.v4.app.FragmentTransaction;import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dhairyapujara.hw9_final.Favorite_Fragment.EventsFavFragment;
import com.example.dhairyapujara.hw9_final.Favorite_Fragment.GroupsFavFragment;
import com.example.dhairyapujara.hw9_final.Favorite_Fragment.PagesFavFragment;
import com.example.dhairyapujara.hw9_final.Favorite_Fragment.PlacesFavFragment;
import com.example.dhairyapujara.hw9_final.Favorite_Fragment.UsersFavFragment;
import com.example.dhairyapujara.hw9_final.adapter.ViewPagerAdapter;

public class FavFragment extends Fragment {

    Toolbar tool;
    TabLayout tab;
    ViewPager viewPage;
    ViewPagerAdapter viewPagerAdapter;
    SharedPreferences sharedPreferences;


    //SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
    //SharedPreferences sharedPreferences= getActivity().getSharedPreferences("fav3", Context.MODE_PRIVATE);

    public FavFragment(){
        //SharedPreferences sharedPreferences= getActivity().getSharedPreferences("fav3", Context.MODE_PRIVATE);



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootView = inflater.inflate(R.layout.activity_fav_fragment,container,false);
        ((MainActivity)getActivity()).setActionBarTitle("Favorite");

        //tool = (Toolbar) rootView.findViewById(R.id.toolbar);
        //((MainActivity)getActivity()).setSupportActionBar(tool);

        tab = (TabLayout) rootView.findViewById(R.id.tabLayout_fav);
        viewPage = (ViewPager)rootView.findViewById(R.id.viewPager_fav);

        viewPagerAdapter = new ViewPagerAdapter(((MainActivity)getActivity()).getSupportFragmentManager());

        sharedPreferences= getActivity().getSharedPreferences("fav3", Context.MODE_PRIVATE);

        Log.d("sp:", String.valueOf(sharedPreferences));



        Log.d("Pacho aayvo:","aayvooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        viewPagerAdapter.addFragments(new UsersFavFragment(sharedPreferences,"Users"),"Users");
        viewPagerAdapter.addFragments(new PagesFavFragment(sharedPreferences,"Pages"),"Pages");
        viewPagerAdapter.addFragments(new EventsFavFragment(sharedPreferences,"Events"),"Events");
        viewPagerAdapter.addFragments(new PlacesFavFragment(sharedPreferences,"Places"),"Places");
        viewPagerAdapter.addFragments(new GroupsFavFragment(sharedPreferences,"Groups"),"Groups");

        //sharedPreferences,"Users"

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



        //viewPagerAdapter.getPageTitle(4)
        return rootView;
    }


}
