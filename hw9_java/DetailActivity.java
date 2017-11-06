package com.example.dhairyapujara.hw9_final;

/**
 * Created by Dhairya Pujara on 18-04-2017.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import com.example.dhairyapujara.hw9_final.fragment.UsersFragment;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.ShareApi;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.map;
import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.mapevent;
import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.mapgroup;
import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.mappage;
import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.mapplace;
import static com.example.dhairyapujara.hw9_final.fragment.Hash_Use.mapuser;

public class DetailActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private LoginManager manager;

    public static final String MyPREFERENCES = "MyPrefs" ;

    KeyItem items;
    String share_img;
    String share_naam;
    ShareDialog shareDialog;
    SharedPreferences sharedPreferences;

    Toolbar tool;
    TabLayout tab;
    ViewPager viewPage;
    ViewPagerAdapter viewPagerAdapter;

    String res_albpos="";
    String res_id = "";
    String tab_title="";

    Bitmap myBitmap;
    ImageView iv_fav;


    JSONObject obj2 = new JSONObject();


    public static List<KeyItem> favuserlist = new ArrayList<>();;
    public static List<KeyItem> favpagelist = new ArrayList<>();
    public static List<KeyItem> faveventlist = new ArrayList<>();
    public static List<KeyItem> favplacelist = new ArrayList<>();
    public static List<KeyItem> favgrouplist = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        facebookSDKInitialize();

        SharedPreferences sharedPreferences= getSharedPreferences("fav3", Context.MODE_PRIVATE);

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

        //Log.d("share_img",share_img);
        //Log.d("share_naam",share_naam);





    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
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
                Toast.makeText(this,"Added to Favorites!",Toast.LENGTH_LONG).show();

                //items = map.get(res_id);

                String name_fav = items.getItem_name();
                String id_fav = items.getItem_id();
                String img_fav = items.getItem_img();
                String title_fav = items.getItem_title();
                /*Log.d("fav_items:", String.valueOf(items));
                Log.d("fav_name:", String.valueOf(name_fav));
                Log.d("fav_id:", String.valueOf(id_fav));
                Log.d("fav_img:", String.valueOf(img_fav));*/
                Log.d("fav_title:", String.valueOf(title_fav));


                JSONObject ob1 = new JSONObject();
                try {
                    //ob1.put("id",id_fav);
                    //ob1.put("name",name_fav);
                    //ob1.put("url",img_fav);
                    ob1.put("title",title_fav);

                    //obj2.put("data",ob1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }



                //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                sharedPreferences= getSharedPreferences("fav3", Context.MODE_PRIVATE);
                //SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //editor.clear();
                //editor.putString(id_fav, String.valueOf(ob1));
                editor.putString(id_fav, title_fav);
                editor.commit();


                /*Set<String> mySet = new HashSet<>();
                mySet.add(id_fav);
                mySet.add(name_fav);
                mySet.add(img_fav);*/
                //setDefaults(id_fav,mySet,this);





                /*Map<String,?> keys = sharedPreferences.getAll();

                for(Map.Entry<String,?> entry : keys.entrySet()){
                    Log.d("map values",entry.getKey() + ": " +
                            entry.getValue().toString());
                }*/




               //iv_fav.setTag(123,R.drawable.favorites_on);

                //int tag_id = (Integer) iv_fav.getTag();

                //iv_fav.setImageResource(R.drawable.favorites_on);
                //iv_fav.setTag(R.drawable.favorites_on);

                //int drawable_id = (Integer)iv_fav.getTag();

                //Log.d("ob2_su_che", String.valueOf(obj2));

                switch(tab_title){
                    case "Users" :  favuserlist.add(items);
                                    //UsersFragment uf = new UsersFragment(id_fav);
                                    //UsersFavFragment ob = new UsersFavFragment(sharedPreferences);

                                     //items.getItem_id();



                                    //UsersFavFragment ob = new UsersFavFragment(favuserlist);
                                    Log.d("users ma:","maa");
                                    UsersFavFragment ob = new UsersFavFragment(sharedPreferences,tab_title);
                                    break;

                    case "Pages" : favpagelist.add(items);

                                    Log.d("pages ma:","maa");
                                     PagesFavFragment ob2 = new PagesFavFragment(sharedPreferences,tab_title);
                                    //PagesFavFragment ob2 = new PagesFavFragment(favpagelist);
                                    break;

                    case "Events" : faveventlist.add(items);

                                     Log.d("events ma:","maa");
                                    EventsFavFragment ob3 = new EventsFavFragment(sharedPreferences,tab_title);
                                    //EventsFavFragment ob3 = new EventsFavFragment(faveventlist);
                                    break;

                    case "Places" : favplacelist.add(items);
                                    Log.d("places ma:","maa");
                                  PlacesFavFragment ob4 = new PlacesFavFragment(sharedPreferences,tab_title);
                                     //PlacesFavFragment ob4 = new PlacesFavFragment(favplacelist);
                                     break;

                    case "Groups" : favgrouplist.add(items);
                                    Log.d("groups ma:","maa");
                                    GroupsFavFragment ob5 = new GroupsFavFragment(sharedPreferences,tab_title);
                                    //GroupsFavFragment ob5 = new GroupsFavFragment(favgrouplist);
                                    break;

                }

                break;

            case R.id.action_share:
                Log.d("Share_setting","mani");
                Toast.makeText(this,"Sharing "+share_naam+"!!",Toast.LENGTH_LONG).show();

                if (ShareDialog.canShow(ShareLinkContent.class)){

                    /*ShareLinkContent content = new ShareLinkContent.Builder()
                            .setContentUrl(Uri.parse("https://developers.facebook.com"))
                            .build();*/

                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentTitle(share_naam)
                            .setContentDescription("FB SEARCH FROM USC CSCI571")
                            .setContentUrl(Uri.parse("https://developers.facebook.com"))
                            .setImageUrl(Uri.parse(share_img))
                            .build();

                    shareDialog.show(linkContent);  // Show facebook ShareDialog<br />
                }

                //Share_Bro();
                break;

            case android.R.id.home: finish();
                break;

        }

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    public static void setDefaults(String key, Set<String> value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, String.valueOf(value));
        editor.commit();
    }

    private void facebookSDKInitialize()
    {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
    }


    private void Share_Bro()
    {
        //FacebookSdk.sdkInitialize(getApplicationContext());

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



 /*PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.example.dhairyapujara.hw9_final", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }*/


 /*user_res_id = (String) getIntent().getExtras().get("user_res_id");
        page_res_id = (String) getIntent().getExtras().get("page_res_id");
        event_res_id = (String) getIntent().getExtras().get("event_res_id");
        place_res_id = (String) getIntent().getExtras().get("place_res_id");
        group_res_id = (String) getIntent().getExtras().get("group_res_id");*/


 /*if(user_res_id != null){
            item = map.get(user_res_id);
            share_img = item.getItem_img();
            share_naam = item.getItem_name();

            viewPagerAdapter.addFragments(new PostsFragment(res_albpos,user_res_id),"Posts");
        }
        else
        if(page_res_id != null){
            item = map.get(page_res_id);
            share_img = item.getItem_img();
            share_naam = item.getItem_name();

            viewPagerAdapter.addFragments(new PostsFragment(res_albpos,page_res_id),"Posts");
        }
        else
        if(event_res_id != null){
            item = map.get(event_res_id);
            share_img = item.getItem_img();
            share_naam = item.getItem_name();

            viewPagerAdapter.addFragments(new PostsFragment(res_albpos,event_res_id),"Posts");
        }
        else
        if(place_res_id != null){
            item = map.get(place_res_id);
            share_img = item.getItem_img();
            share_naam = item.getItem_name();

            viewPagerAdapter.addFragments(new PostsFragment(res_albpos,place_res_id),"Posts");
        }
        else
        if(group_res_id != null){
            item = map.get(group_res_id);
            share_img = item.getItem_img();
            share_naam = item.getItem_name();

            viewPagerAdapter.addFragments(new PostsFragment(res_albpos,group_res_id),"Posts");
        }*/



