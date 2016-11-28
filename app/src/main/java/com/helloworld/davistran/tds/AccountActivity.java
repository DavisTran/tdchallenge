package com.helloworld.davistran.tds;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {

    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        //DATABASE PSEUDO
        //PASS CARDNUMBER IN BUNDLE
        // QUERY CARD NUMBER AND ACCOUNT TYPE
        // CREATE OBJECTS FOR EACH SET
        // ADD TO ARRAY LIST
        //DISPLAY

        lv = (ListView)findViewById(R.id.transactionList);
        ArrayList<TransactionInfo> al = new ArrayList<TransactionInfo>();

        al.add(new TransactionInfo("COSTCO-5123-32", "11-11-2016", 56.12));
        al.add(new TransactionInfo("WALMART-12032", "11-07-2016", 12.12));
        al.add(new TransactionInfo("SHOPPERS-DRUG-MART-10512-3", "11-07-2016", 16.62));
        al.add(new TransactionInfo("JYSK-1001", "11-01-2016", 101.12));
        al.add(new TransactionInfo("TIM-HORTONS-2012-52", "10-26-2016", 6.12));
        al.add(new TransactionInfo("WENDYS-10512-31", "10-26-2016", 12.31));
        al.add(new TransactionInfo("FRESHCO-1012-220", "10-23-2016", 72.51));
        al.add(new TransactionInfo("PET-PARADISE-10543-12", "10-21-2016", 31.16));
        al.add(new TransactionInfo("LA-FITNESS-1011-90", "10-11-2016", 45.51));
        al.add(new TransactionInfo("METRO-102-4152", "10-11-2016", 31.56));
        al.add(new TransactionInfo("ZARA-2405-10", "10-11-2016", 140.71));

        TransactionAdapter ta = new TransactionAdapter(this, al, lv);

        lv.setAdapter(ta);

        Intent in = getIntent();
        Bundle extras = in.getExtras();

        String accountType = extras.getString("accountType");

        getSupportActionBar().setTitle(accountType + " Transactions");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void onBackPressed()
    {
        finish();
    }
}
