package com.helloworld.davistran.tds;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

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
        View img = convertView.findViewById(R.id.nearme_busy);

        name.setText(nearme.getnName());
        dist.setText(nearme.getnDistance());
        Drawable dot = ResourcesCompat.getDrawable(parent.getResources(), R.drawable.dot, null);
        switch(nearme.getnBusy())
        {
            case "slow":
                dot.setTint(Color.RED);
                break;
            case "moderate":
                dot.setTint(Color.YELLOW);
                break;
            case "fast":
                dot.setTint(Color.GREEN);
                break;
            default:
                //grey
                //img.setBackgroundResource(R.drawable.tdlogo);
                break;
        }
        img.setBackground(dot);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.info_nearme_row_details, null);
        }

        final String child = (String)getChild(groupPosition, childPosition);

        //ImageView imgur = (ImageView)convertView.findViewById(R.id.childmap);
        ImageButton btn1 = (ImageButton)convertView.findViewById(R.id.gmapsBtn);
        ImageButton btn2 = (ImageButton)convertView.findViewById(R.id.tdmapsBtn);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(child)
                {
                    case "TD1":
                        String uri = String.format(Locale.ENGLISH, "geo:%f%f",43.0200373, -81.2161815);
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        ctx.startActivity(i);
                        break;
                    case "TD2":
                        String uri2 = String.format(Locale.ENGLISH, "geo:%f%f",43.0348858, -81.2581032);
                        Intent i2 = new Intent(Intent.ACTION_VIEW, Uri.parse(uri2));
                        i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        ctx.startActivity(i2);
                        break;
                    case "TD3":
                        String uri3 = String.format(Locale.ENGLISH, "geo:%f%f",43.0067415, -81.2406151);
                        Intent i3 = new Intent(Intent.ACTION_VIEW, Uri.parse(uri3));
                        i3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        ctx.startActivity(i3);
                        break;
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(child)
                {
                    case "TD1":
                        Intent i = new Intent(ctx, MapsActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("longitude", "43.0200373");
                        i.putExtra("latitude", "-81.2161815");
                        ctx.startActivity(i);
                        break;
                    case "TD2":
                        Intent i2 = new Intent(ctx, MapsActivity.class);

                        i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i2.putExtra("longitude", "43.0348858");
                        i2.putExtra("latitude", "-81.2581032");
                        ctx.startActivity(i2);
                        break;
                    case "TD3":
                        Intent i3 = new Intent(ctx, MapsActivity.class);
                        i3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i3.putExtra("longitude", "43.0067415");
                        i3.putExtra("latitude", "-81.2406151");
                        ctx.startActivity(i3);
                        break;
                }
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
