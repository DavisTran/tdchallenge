package com.helloworld.davistran.tds;

import android.content.Context;
import android.graphics.Color;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.Locale;


/**
 * Created by Davis on 11/20/2016.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private String[] mDataSet;
    private int[] mDataSetTypes;
    private ArrayList<NearMeInfo> mNearMeInfo;

    protected Context context;
    private int lastExpandedPosition = -1;

    public static final int ACCOUNTS = 0;
    public static final int TRANSACTIONS = 1;
    public static final int NEARME = 2;
    public static final int MAIN = 3;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ViewHolder(View itemView)
        {
            super(itemView);
        }
    }

    public class ImageViewHolder extends ViewHolder {
        public FloatingActionButton fbTransfer;
        public FloatingActionButton fbNFC;
        public FloatingActionButton fbPayment;

        public ImageViewHolder(View itemView)
        {
            super(itemView);
            fbTransfer = (FloatingActionButton)itemView.findViewById(R.id.fb_transfer);
            fbNFC = (FloatingActionButton)itemView.findViewById(R.id.fb_nfc);
            fbPayment = (FloatingActionButton)itemView.findViewById(R.id.fb_payment);
        }
    }

    public class AccountsViewHolder extends ViewHolder {
        public CardView cv;
        public Button cheqBtn;
        public Button saveBtn;
        public Button ccBtn;
        public Button tfsaBtn;

        public AccountsViewHolder(View itemView)
        {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            cheqBtn = (Button)itemView.findViewById(R.id.chequeBtn);
            saveBtn = (Button)itemView.findViewById(R.id.saveBtn);
            ccBtn = (Button)itemView.findViewById(R.id.ccBtn);
            tfsaBtn = (Button)itemView.findViewById(R.id.tfsaBtn);
        }
    }

    public class TransactionViewHolder extends ViewHolder {
        public CardView cv2;
        //pie chart in this one http://www.achartengine.org/content/download.html
        public TransactionViewHolder(View itemView)
        {
            super(itemView);
            cv2 = (CardView)itemView.findViewById(R.id.cv2);

            PieChart mPieChart = (PieChart) cv2.findViewById(R.id.piechart);

            mPieChart.addPieSlice(new PieModel("Gas", 15, Color.parseColor("#FE6DA8")));
            mPieChart.addPieSlice(new PieModel("Restaurants", 25, Color.parseColor("#56B7F1")));
            mPieChart.addPieSlice(new PieModel("Shopping", 35, Color.parseColor("#CDA67F")));
            mPieChart.addPieSlice(new PieModel("Groceries", 9, Color.parseColor("#FED70E")));

            mPieChart.startAnimation();
        }
    }

    public class NearMeViewHolder extends ViewHolder {
        public CardView cv3;
        public ExpandableListView info;
        public ImageButton btn1, btn2;

        //populate with locations
        public NearMeViewHolder(final View itemView)
        {
            super(itemView);
            cv3 = (CardView)itemView.findViewById(R.id.cv3);
            info = (ExpandableListView) itemView.findViewById(R.id.nearme_list);
            final Drawable d = itemView.getResources().getDrawable(R.drawable.tdlogo);
            cv3.getLayoutParams().height = 400;
//            info.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//                @Override
//                public void onGroupExpand(int groupPosition) {
//                    if(lastExpandedPosition != -1 && groupPosition != lastExpandedPosition)
//                    {
//                        info.collapseGroup(lastExpandedPosition);
//                    }
//                    lastExpandedPosition = groupPosition;
//
//                    int height = 0;
//                    for(int i =0; i < (groupPosition + 1); i++)
//                    {
//                        height +=info.getChildAt(i).getHeight();
//                        height += info.getDividerHeight();
//                    }
//
//                    //get heigh of the google maps and add that to the height
//                    cv3.getLayoutParams().height = height + d.getIntrinsicHeight();
//                    cv3.setFocusableInTouchMode(true);
//                    //info.getLayoutParams().height = (height+6) * 10;
//                }
//            });
//
//            info.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//                @Override
//                public void onGroupCollapse(int groupPosition) {
//                    cv3.getLayoutParams().height = 400;
//                    //info.setFocusable(true);
//                    //info.getLayoutParams().height = height;

            info.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                @Override
                public void onGroupExpand(final int groupPosition) {
                    if(lastExpandedPosition != -1 && groupPosition != lastExpandedPosition)
                    {
                        info.collapseGroup(lastExpandedPosition);
                    }
                    lastExpandedPosition = groupPosition;

                    int height = 0;
                    for(int i =0; i < (groupPosition + 1); i++)
                    {
                        height += info.getChildAt(i).getHeight();
                        height += info.getDividerHeight();
                    }

                    //get heigh of the google maps and add that to the height
                    cv3.getLayoutParams().height = height + d.getIntrinsicHeight();
                    cv3.setFocusableInTouchMode(true);
                    //info.getLayoutParams().height = (height+6) * 10;

                }
            });

            info.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
                @Override
                public void onGroupCollapse(int groupPosition) {
                    cv3.getLayoutParams().height = 400;
                    //info.setFocusable(true);
                    //info.getLayoutParams().height = height;
                }
            });

//            btn1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    switch(info.getSelectedItemPosition())
//                    {
//                        case 0:
//                            String uri = String.format(Locale.ENGLISH, "geo:%f%f",43.0200373, -81.2161815);
//                            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//                            context.startActivity(i);
//                            break;
//                        case 1:
//                            String uri2 = String.format(Locale.ENGLISH, "geo:%f%f",43.0348858, -81.2581032);
//                            Intent i2 = new Intent(Intent.ACTION_VIEW, Uri.parse(uri2));
//                            context.startActivity(i2);
//                            break;
//                        case 2:
//                            String uri3 = String.format(Locale.ENGLISH, "geo:%f%f",43.0067415, -81.2406151);
//                            Intent i3 = new Intent(Intent.ACTION_VIEW, Uri.parse(uri3));
//                            context.startActivity(i3);
//                            break;
//                    }
//                }
//            });
//
//            btn2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    switch(info.getSelectedItemPosition())
//                    {
//                        case 0:
//                            Intent i = new Intent(context, MapsActivity.class);
//                            i.putExtra("longitude", "43.0200373");
//                            i.putExtra("latitude", "-81.2161815");
//                            context.startActivity(i);
//                            break;
//                        case 1:
//                            Intent i2 = new Intent(context, MapsActivity.class);
//                            i2.putExtra("longitude", "43.0348858");
//                            i2.putExtra("latitude", "-81.2581032");
//                            context.startActivity(i2);
//                            break;
//                        case 2:
//                            Intent i3 = new Intent(context, MapsActivity.class);
//                            i3.putExtra("longitude", "43.0067415");
//                            i3.putExtra("latitude", "-81.2406151");
//                            context.startActivity(i3);
//                            break;
//                    }
//                }
//            });
        }
    }

    public CustomAdapter(String[] dataSet, int[] dataSetTypes, ArrayList<NearMeInfo> nearme, Context context)
    {
        mDataSet = dataSet;
        mDataSetTypes = dataSetTypes;
        mNearMeInfo = new ArrayList<NearMeInfo>(nearme);
        this.context = context.getApplicationContext();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if(viewType == ACCOUNTS)
        {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_accounts, parent, false);
            return new AccountsViewHolder(v);
        }else if(viewType == TRANSACTIONS)
        {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_transactions, parent, false);
            return new TransactionViewHolder(v);
        }else if(viewType == MAIN)
        {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_main, parent, false);
            return new ImageViewHolder(v);
        }else
        {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_nearme, parent, false);
            return new NearMeViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(holder.getItemViewType() == ACCOUNTS)
        {
            //set information here
            AccountsViewHolder hold = (AccountsViewHolder)holder;
            //String text = mDataSet[position];
            hold.saveBtn.setText("Savings\n\n" + MainActivity.Savings);
            hold.cheqBtn.setText("Chequing\n\n" + MainActivity.Cheque);
            hold.ccBtn.setText("MasterCard\n\n" + MainActivity.MasterCard);
            hold.tfsaBtn.setText("TFSA\n\n" + MainActivity.TFSA);
        }else if(holder.getItemViewType() == TRANSACTIONS)
        {
            TransactionViewHolder hold = (TransactionViewHolder) holder;
            //more info here
        }else if(holder.getItemViewType() == NEARME)
        {
            NearMeViewHolder hold = (NearMeViewHolder) holder;
            ArrayList<NearMeInfo> nearMeArray = new ArrayList<NearMeInfo>(mNearMeInfo);

            NearMeAdapter adapter = new NearMeAdapter(context, nearMeArray);
            hold.info.setAdapter(adapter);
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }

    @Override
    public int getItemViewType(int position){
        return mDataSetTypes[position];
    }
}
