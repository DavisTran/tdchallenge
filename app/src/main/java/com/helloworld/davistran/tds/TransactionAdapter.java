package com.helloworld.davistran.tds;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Davis on 11/22/2016.
 */

public class TransactionAdapter extends ArrayAdapter<TransactionInfo> {

    private String tempDate = "";
    private ListView lvD;

    public TransactionAdapter(Context context, ArrayList<TransactionInfo> transactionInfos, ListView lv) {
        super(context, 0, transactionInfos);
        lvD = lv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TransactionInfo transinfo = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.info_transaction_row, parent, false);
        }

        RelativeLayout rl = (RelativeLayout)convertView.findViewById(R.id.transLayout);

        TextView date = (TextView)convertView.findViewById(R.id.transactionDATE);
        TextView id = (TextView)convertView.findViewById(R.id.transactionID);
        TextView cost = (TextView) convertView.findViewById(R.id.transactionCOST);

        if(!tempDate.equals(transinfo.getDate())) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)date.getLayoutParams();
            params.topMargin = 30;
            date.setLayoutParams(params);
            date.setText("" + transinfo.getDate());

        }else
        {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)date.getLayoutParams();
            params.height = 0;
            date.setLayoutParams(params);
        }

        id.setText("" + transinfo.getId());
        cost.setText("" + transinfo.getCost());

        tempDate = transinfo.getDate();

        return convertView;
    }
}

