package com.example.dhairyapujara.hw9_final.adapter;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dhairyapujara.hw9_final.Config;
import com.example.dhairyapujara.hw9_final.DetailActivity;
import com.example.dhairyapujara.hw9_final.KeyItem;
import com.example.dhairyapujara.hw9_final.R;
import com.example.dhairyapujara.hw9_final.RequestHandler;
import com.squareup.picasso.Picasso;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dhairya Pujara on 15-04-2017.
 */

public class ProductListAdapter extends ArrayAdapter<KeyItem> {
    List<KeyItem> products;
    List<String> fav_list;
    ImageView iv_det;
    ImageView iv_fav;
    String id_adapter;
    String fav_id_adapter;
    String tab_title;

    String id_star;
    SharedPreferences sharedPreferences;

    public ProductListAdapter(Context context, int resource, List<KeyItem> objects,String title){
        super(context,resource,objects);
        products = objects;
        tab_title = title;
    }

    public ProductListAdapter(Context context, int resource, List<KeyItem> objects, String title, String id)
    {
        super(context,resource,objects);
        products = objects;
        tab_title = title;
        id_star = id;

        if(id_star != null) {
            Log.d("navu constructor", id_star);
        }


    }


    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.test_button,parent,false);
        }

        KeyItem ob = products.get(position);

        /*sharedPreferences= getContext().getSharedPreferences("fav3", Context.MODE_PRIVATE);
        //Log.d("sp:", String.valueOf(sharedPreferences));

        Map<String,?> keys = sharedPreferences.getAll();

        for(Map.Entry<String,?> entry : keys.entrySet()){

            if(entry.getKey().equals(ob.getItem_id()) && entry.getValue().equals(ob.getItem_title())){
                Log.d("key_prolist",entry.getKey());
                Log.d("key_ob",ob.getItem_id());
                iv_fav = (ImageView)convertView.findViewById(R.id.fav_chu);
                iv_fav.setImageResource(R.drawable.favorites_on);

            }


        }*/




        TextView name_text = (TextView) convertView.findViewById(R.id.tv_name);
        name_text.setText(ob.getItem_name());

        String imageUri = ob.getItem_img();
        final ImageView iv = (ImageView)convertView.findViewById(R.id.test_button_image);
        Picasso.with(getContext()).load(imageUri).into(iv);

        iv_det = (ImageView)convertView.findViewById(R.id.detail_chu);
        iv_det.setOnClickListener(det_but);

        notifyDataSetChanged();

        //Picasso.with(activity).load(file).placeholder(R.drawable.rtrt).fit().centerCrop().into(image);

        return convertView;
    }

    View.OnClickListener det_but = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            View parentRow = (View) v.getParent();
            ListView listView = (ListView) parentRow.getParent();
            final int position = listView.getPositionForView(parentRow);

            //Log.d("detail_id_che:", String.valueOf(position));

            KeyItem item = products.get(position);
            id_adapter = item.getItem_id();
            Log.d("pressed_Id:",id_adapter);
            Log.d("pressed_Title:",tab_title);


            Fetch_Albums_Posts(id_adapter);

        }
    };

    public void Fetch_Albums_Posts(final String id)
    {
        class AlbPost extends AsyncTask<Void,Void,String> {

            HashMap<String, String> params1 = new HashMap<String, String>();


            @Override
            protected String doInBackground(Void... v) {
                params1.put(Config.KEY_ID,id);
                RequestHandler rh = new RequestHandler();
                String res = rh.performPostCall(Config.URL_GET_DATA, params1);
                return res;

            }
            @Override
            protected void onPostExecute(String s) {

                //Log.d("response_albums", String.valueOf(s));
                Intent in = new Intent(getContext(),DetailActivity.class);
                in.putExtra("json_albpos",s);
                in.putExtra("res_id",id);
                in.putExtra("title",tab_title);
                Log.d("Detail_passe_Title:",tab_title);
                getContext().startActivity(in);
            }
        }
        AlbPost ob = new AlbPost();
        ob.execute();
    }
}



/*private Bitmap getBitmapFromAsset(String s)
    {
        AssetManager as = getContext().getAssets();
        InputStream stream = null;

        try {
            stream = as.open(s);
            return BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }*/
