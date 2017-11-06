package com.example.dhairyapujara.hw9_final;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    Toolbar toolbar = null;
    NavigationView navigationView = null;
    Button search;
    Button clear;
    EditText key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        search = (Button) findViewById(R.id.Btn_Search);
        clear = (Button) findViewById(R.id.Btn_Clear);
        key = (EditText) findViewById(R.id.keyword);

        search.setOnClickListener(this);
        clear.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == search) {
            String keyword = key.getText().toString().trim();
            if(keyword.isEmpty() || keyword == "" || keyword == null){
                Toast.makeText(HomeActivity.this,"Please enter a keyword!",Toast.LENGTH_LONG).show();
            }
            else{
                Log.d("dp", keyword);
                Fetch_Data(keyword);
            }

        }

        if (view == clear) {
            key.setText("");
        }
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

    private void Fetch_Data(final String keyword) {
        class FetchData extends AsyncTask<Object, Object, ArrayList<String>> {

            HashMap<String, String> params1 = new HashMap<String, String>();
            HashMap<String, String> params2 = new HashMap<String, String>();
            HashMap<String, String> params3 = new HashMap<String, String>();
            HashMap<String, String> params4 = new HashMap<String, String>();
            HashMap<String, String> params5 = new HashMap<String, String>();

            @Override
            protected ArrayList<String> doInBackground(Object... voids) {

                ArrayList<String> al = new ArrayList<>();

                params1.put(Config.KEY_SEARCH, keyword);
                params1.put(Config.KEY_TYPE,"user");

                params2.put(Config.KEY_SEARCH, keyword);
                params2.put(Config.KEY_TYPE,"page");

                params3.put(Config.KEY_SEARCH, keyword);
                params3.put(Config.KEY_TYPE,"event");

                params4.put(Config.KEY_SEARCH, keyword);
                params4.put(Config.KEY_TYPE,"place");

                params5.put(Config.KEY_SEARCH, keyword);
                params5.put(Config.KEY_TYPE,"group");




                RequestHandler rh = new RequestHandler();


                String res_user = rh.performPostCall(Config.URL_GET_DATA, params1);
                String res_page = rh.performPostCall(Config.URL_GET_DATA, params2);
                String res_event = rh.performPostCall(Config.URL_GET_DATA, params3);
                String res_place = rh.performPostCall(Config.URL_GET_DATA, params4);
                String res_group = rh.performPostCall(Config.URL_GET_DATA, params5);

                al.add(res_user);
                al.add(res_page);
                al.add(res_event);
                al.add(res_place);
                al.add(res_group);

                return al;


            }

            @Override
            protected void onPreExecute() {

            }

            @Override
            protected void onPostExecute(ArrayList<String> al) {

                //Log.d("response", String.valueOf(s));
                Intent in = new Intent(HomeActivity.this,ResultActivity.class);
                in.putExtra("json_user",al.get(0));
                in.putExtra("json_page",al.get(1));
                in.putExtra("json_event",al.get(2));
                in.putExtra("json_place",al.get(3));
                in.putExtra("json_group",al.get(4));

                startActivity(in);


                //Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
            }


        }


        FetchData ob = new FetchData();
        ob.execute();

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

            Intent i = new Intent(HomeActivity.this,AboutMe.class);
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
