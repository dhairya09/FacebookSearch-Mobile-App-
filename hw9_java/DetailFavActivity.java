package com.example.dhairyapujara.hw9_final;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dhairyapujara.hw9_final.Favorite_Fragment.EventsFavFragment;
import com.example.dhairyapujara.hw9_final.Favorite_Fragment.GroupsFavFragment;
import com.example.dhairyapujara.hw9_final.Favorite_Fragment.PagesFavFragment;
import com.example.dhairyapujara.hw9_final.Favorite_Fragment.PlacesFavFragment;
import com.example.dhairyapujara.hw9_final.Favorite_Fragment.UsersFavFragment;
import com.example.dhairyapujara.hw9_final.adapter.ViewPagerAdapter;
import com.example.dhairyapujara.hw9_final.fragment.AlbumsFragment;
import com.example.dhairyapujara.hw9_final.fragment.PostsFragment;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.ShareApi;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.dhairyapujara.hw9_final.DetailActivity.faveventlist;
import static com.example.dhairyapujara.hw9_final.DetailActivity.favgrouplist;
import static com.example.dhairyapujara.hw9_final.DetailActivity.favpagelist;
import static com.example.dhairyapujara.hw9_final.DetailActivity.favplacelist;
import static com.example.dhairyapujara.hw9_final.DetailActivity.favuserlist;
//import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.map;
import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.mapevent;
import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.mapgroup;
import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.mappage;
import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.mapplace;
import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.mapuser;

public class DetailFavActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private LoginManager manager;

    KeyItem items;
    String share_img;
    String share_naam;
    SharedPreferences sharedPreferences;

    Toolbar tool;
    TabLayout tab;
    ViewPager viewPage;
    ViewPagerAdapter viewPagerAdapter;

    String res_albpos="";
    String res_id = "";
    String tab_title="";

    Bitmap myBitmap;
    //ImageView iv_fav;

    /*public static List<KeyItem> favuserlist = new ArrayList<>();;
    public static List<KeyItem> favpagelist = new ArrayList<>();
    public static List<KeyItem> faveventlist = new ArrayList<>();
    public static List<KeyItem> favplacelist = new ArrayList<>();
    public static List<KeyItem> favgrouplist = new ArrayList<>();*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_fav);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        res_albpos = (String) getIntent().getExtras().get("json_albpos");
        res_id = (String)getIntent().getExtras().get("res_id");
        tab_title = (String)getIntent().getExtras().get("title");

        //tool = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(tool);

        tab = (TabLayout)findViewById(R.id.tabLayout_result);
        viewPage = (ViewPager)findViewById(R.id.viewPager_result);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragments(new AlbumsFragment(res_albpos),"Albums");

        switch(tab_title){
            case "Users":   items = mapuser.get(res_id);
                break;
            case "Pages":   items = mappage.get(res_id);
                break;
            case "Events":  items = mapevent.get(res_id);
                break;
            case "Places":  items = mapplace.get(res_id);
                break;

            case "Groups":  items = mapgroup.get(res_id);
                break;
        }


        //items = map.get(res_id);
        share_img = items.getItem_img();
        share_naam = items.getItem_name();

        viewPagerAdapter.addFragments(new PostsFragment(res_albpos,res_id,tab_title),"Posts");

        viewPage.setAdapter(viewPagerAdapter);
        tab.setupWithViewPager(viewPage);


        tab.getTabAt(0).setIcon(R.drawable.albums);
        tab.getTabAt(1).setIcon(R.drawable.posts);

        tab.getTabAt(0).setText(viewPagerAdapter.getPageTitle(0));
        tab.getTabAt(1).setText(viewPagerAdapter.getPageTitle(1));

        Log.d("share_img",share_img);
        Log.d("share_naam",share_naam);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favorites, menu);
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
                Log.d("Fav_settingsssssss","gani");
                Toast.makeText(this,"Removed from Favorites!",Toast.LENGTH_LONG).show();
                Log.d("Tab-Title:",tab_title);
                Log.d("res-id:",res_id);

                /*iv_fav = (ImageView)findViewById(R.id.fav_chu);

                Drawable d = iv_fav.getDrawable();
                if(d != null){
                    iv_fav.setImageResource(R.drawable.favorites_on);
                }*/

                //items = map.get(res_id);



                String rem_naam = items.getItem_name();
                String rem_id = items.getItem_id();
                Log.d("rem-id:",rem_id);
                Log.d("rem-naam:",rem_naam);

                sharedPreferences= getSharedPreferences("fav3", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(rem_id);
                editor.apply();





                switch(tab_title){
                    case "Users" :Log.d("andar_Users:",tab_title);

                                   // favuserlist.remove(items);

                                   //UsersFavFragment fragment = new UsersFavFragment(favuserlist);
                                    UsersFavFragment fragment = new UsersFavFragment(sharedPreferences,tab_title);




                        break;

                    case "Pages" :  Log.d("andar_Pages:",tab_title);
                                    //favpagelist.remove(items);
                                    //PagesFavFragment ob2 = new PagesFavFragment(favpagelist);
                                    PagesFavFragment ob2 = new PagesFavFragment(sharedPreferences,tab_title);
                        break;

                    case "Events" : Log.d("andar_Events:",tab_title);
                                    //faveventlist.remove(items);
                                    //EventsFavFragment ob3 = new EventsFavFragment(faveventlist);
                                    EventsFavFragment ob3 = new EventsFavFragment(sharedPreferences,tab_title);
                        break;

                    case "Places" : Log.d("andar_Places:",tab_title);
                                    //favplacelist.remove(items);
                                    //PlacesFavFragment ob4 = new PlacesFavFragment(favplacelist);
                                    PlacesFavFragment ob4 = new PlacesFavFragment(sharedPreferences,tab_title);
                        break;

                    case "Groups" : Log.d("andar_Groups:",tab_title);
                                    //favgrouplist.remove(items);
                                    //GroupsFavFragment ob5 = new GroupsFavFragment(favgrouplist);
                                    GroupsFavFragment ob5 = new GroupsFavFragment(sharedPreferences,tab_title);
                        break;

                }

                break;

            case R.id.action_share:
                Log.d("Share_setting","mani");
                Toast.makeText(this,"You selected Share",Toast.LENGTH_LONG).show();
                Share_Bro();
                break;

            case android.R.id.home: finish();
                break;

        }

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    private void Share_Bro()
    {
        FacebookSdk.sdkInitialize(getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

        List<String> permissionNeeds = Arrays.asList("publish_actions");

        manager = LoginManager.getInstance();

        manager.logInWithPublishPermissions(this,permissionNeeds);

        manager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                publishImage();
            }

            @Override
            public void onCancel() {
                Log.d("Cancel","no man");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("Error","error bro");
            }
        });

    }

    private void publishImage()
    {
        try {
            URL url = new URL(share_img);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            myBitmap = BitmapFactory.decodeStream(input);
        }
        catch(Exception e){

        }

        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(myBitmap)
                .setCaption(share_naam)
                .build();

        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();

        ShareApi.share(content,null);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }



}

