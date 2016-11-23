package com.helloworld.davistran.tds;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Davis on 11/22/2016.
 */

public class NearMeAdapter extends BaseExpandableListAdapter {
    private Context ctx;
    private ArrayList<NearMeInfo> img;
    private LayoutInflater inflater;

    public NearMeAdapter(Context cont, ArrayList<NearMeInfo> x)
    {
        this.ctx = cont;
        this.img = x;
        this.inflater = (LayoutInflater) cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getGroupCount() {
        return img.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return img.get(groupPosition).getImg().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return img.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return img.get(groupPosition).getImg().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        NearMeInfo nearme = (NearMeInfo)getGroup(groupPosition);

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.info_nearme_row, parent, false);
        }

        TextView name = (TextView)convertView.findViewById(R.id.nearme_name);
        TextView dist = (TextView)convertView.findViewById(R.id.nearme_dist);
        ImageView img = (ImageView)convertView.findViewById(R.id.nearme_busy);

        name.setText(nearme.getnName());
        dist.setText(nearme.getnDistance());
        switch(nearme.getnBusy())
        {
            case "slow":
                //red
                img.setBackgroundResource(R.drawable.rdot);
                break;
            case "moderate":
                //orange
                img.setBackgroundResource(R.drawable.ydot);
                break;
            case "fast":
                //green
                img.setBackgroundResource(R.drawable.gdot);
                break;
            default:
                //grey
                //img.setBackgroundResource(R.drawable.tdlogo);
                break;
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.info_nearme_row_details, null);
        }

        String child = (String)getChild(groupPosition, childPosition);

        ImageView imgur = (ImageView)convertView.findViewById(R.id.childmap);

        switch(child)
        {
            case "TD":
                imgur.setBackgroundResource(R.drawable.tdlogo);
                break;
            default:
                imgur.setBackgroundResource(R.drawable.ic_menu_home);
                break;
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
