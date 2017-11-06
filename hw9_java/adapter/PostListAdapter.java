package com.example.dhairyapujara.hw9_final.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dhairyapujara.hw9_final.KeyItem;
import com.example.dhairyapujara.hw9_final.PostItem;
import com.example.dhairyapujara.hw9_final.R;
import com.example.dhairyapujara.hw9_final.RelativeLayoutButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


/**
 * Created by Dhairya Pujara on 19-04-2017.
 */

public class PostListAdapter extends ArrayAdapter<PostItem> {
    List<PostItem> products;
    TextView name_post;
    TextView time_post;
    TextView msg_post;
    ImageView img_post;

    public PostListAdapter(Context context, int resource, List<PostItem> objects){
        super(context,resource,objects);
        products = objects;
    }


    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.post_item_view,parent,false);
        }

        PostItem ob = products.get(position);


        //RelativeLayoutButton btn = new RelativeLayoutButton(getContext(),R.id.button1);
        name_post = (TextView) convertView.findViewById(R.id.postname);
        name_post.setText(ob.getPost_name());

        time_post = (TextView)convertView.findViewById(R.id.posttime);
        time_post.setText(ob.getPost_time());

        msg_post = (TextView)convertView.findViewById(R.id.postmsg);
        msg_post.setText(ob.getPost_msg());


        img_post = (ImageView)convertView.findViewById(R.id.postimg);

        Picasso.with(getContext()).load(ob.getPost_img()).into(img_post);

        //new DownLoadImageTask(img_post).execute(ob.getPost_img());





        return convertView;
    }

    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }
}
