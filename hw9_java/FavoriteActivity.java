package com.example.dhairyapujara.hw9_final;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dhairyapujara.hw9_final.Favorite_Fragment.EventsFavFragment;
import com.example.dhairyapujara.hw9_final.Favorite_Fragment.GroupsFavFragment;
import com.example.dhairyapujara.hw9_final.Favorite_Fragment.PagesFavFragment;
import com.example.dhairyapujara.hw9_final.Favorite_Fragment.PlacesFavFragment;
import com.example.dhairyapujara.hw9_final.Favorite_Fragment.UsersFavFragment;
import com.example.dhairyapujara.hw9_final.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar = null;
    NavigationView navigationView = null;
    TabLayout tab;
    ViewPager viewPage;
    ViewPagerAdapter viewPagerAdapter;
    SharedPreferences sharedPreferences;

    public static List<KeyItem> prolist = new ArrayList<>();
    public static HashMap<String,KeyItem> map =  new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tab = (TabLayout)findViewById(R.id.tabLayout_fav);
        viewPage = (ViewPager)findViewById(R.id.viewPager_fav);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        sharedPreferences= getSharedPreferences("fav3", Context.MODE_PRIVATE);

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




    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){


            case R.id.action_favorites :
                Log.d("Fav_setting","gani");
                Toast.makeText(this,"You selected Favorites",Toast.LENGTH_LONG).show();






                break;

            case R.id.action_share:
                Log.d("Share_setting","mani");
                Toast.makeText(this,"You selected Share",Toast.LENGTH_LONG).show();

                break;

        }

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Nav_Home) {

            startActivity(new Intent(this,HomeActivity.class));

            /*HomeFragment fragment = new HomeFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();*/

        } else if (id == R.id.Nav_Fav) {

            /*FavFragment fragment = new FavFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();

            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();*/
            startActivity(new Intent(this,FavoriteActivity.class));

        } else if (id == R.id.Nav_About) {

            //startActivity(new Intent(this,AboutMe.class));

            Intent i = new Intent(FavoriteActivity.this,AboutMe.class);
            startActivity(i);


            /*AboutFragment fragment = new AboutFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container,fragment);
            fragmentTransaction.commit();*/

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }
}
