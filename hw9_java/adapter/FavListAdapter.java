package com.example.dhairyapujara.hw9_final.adapter;

/**
 * Created by Dhairya Pujara on 23-04-2017.
 */

import android.content.Context;
import android.content.Intent;

import android.os.AsyncTask;
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
import com.example.dhairyapujara.hw9_final.DetailFavActivity;
import com.example.dhairyapujara.hw9_final.KeyItem;
import com.example.dhairyapujara.hw9_final.R;
import com.example.dhairyapujara.hw9_final.RequestHandler;
import com.squareup.picasso.Picasso;


import java.util.HashMap;
import java.util.List;

/**
 * Created by Dhairya Pujara on 15-04-2017.
 */

public class FavListAdapter extends ArrayAdapter<KeyItem> {
    List<KeyItem> products;
    List<String> fav_list;
    ImageView iv_det;
    ImageView iv_fav;
    String id_adapter;
    String fav_id_adapter;
    String tab_title;

    public FavListAdapter(Context context, int resource, List<KeyItem> objects,String title){
        super(context,resource,objects);
        products = objects;
        tab_title = title;



    }


    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.test_button,parent,false);
        }

        KeyItem ob = products.get(position);

            iv_fav = (ImageView)convertView.findViewById(R.id.fav_chu);
            iv_fav.setImageResource(R.drawable.favorites_on);


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

            Log.d("detail_id_che:", String.valueOf(position));

            KeyItem item = products.get(position);
            id_adapter = item.getItem_id();
            Log.d("pressed_Id:",id_adapter);

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

                Log.d("response_albums", String.valueOf(s));
                Intent in = new Intent(getContext(),DetailFavActivity.class);
                in.putExtra("json_albpos",s);
                in.putExtra("res_id",id);
                in.putExtra("title",tab_title);
                getContext().startActivity(in);
            }
        }
        AlbPost ob = new AlbPost();
        ob.execute();
    }
}
