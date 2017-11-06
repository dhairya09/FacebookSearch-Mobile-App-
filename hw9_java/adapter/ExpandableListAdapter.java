package com.example.dhairyapujara.hw9_final.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dhairyapujara.hw9_final.R;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Dhairya Pujara on 18-04-2017.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    // private FragDrawer _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    Bitmap mIcon_val;
    URL newurl = null;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);
        //Log.d("childText:",childText);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item2, null);
        }


        final ImageView iv = (ImageView)convertView.findViewById(R.id.lblListItem);
        Picasso.with(this._context).load(childText).into(iv);


        //new DownLoadImageTask(iv).execute(childText);

        return convertView;
    }


    @Override
    public int getChildrenCount(int groupPosition) {
        if(!this._listDataChild.isEmpty())
        {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .size();
        }
        return -1;

    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}



//TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);
//txtListChild.setText(childText);




        /*try {
            //newurl = new URL(childText);
            //InputStream in = new java.net.URL(newurl.openStream());
            //mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
            //InputStream is = (InputStream) new URL(childText).getContent();
            //Drawable d = Drawable.createFromStream(is,null);
            //Bitmap bitmap = ((BitmapDrawable)d).getBitmap();

            ImageView iv = (ImageView)convertView.findViewById(R.id.lblListItem);
            iv.setImageBitmap(bitmap);
        } catch (Exception e) {
            Log.e("Error","Error che bha");
            e.printStackTrace();
        }*/
