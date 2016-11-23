package com.helloworld.davistran.tds;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.Gravity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerView;
    private CustomAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] mDataSet = {"Hello", "World", "Boo", "Hello"};
    private int mDataSetTypes[] = {3, 0, 1, 2};
    private Button chequeBtn;
    private Button saveBtn;
    private Button mcBtn;
    private Button tfsaBtn;
    private HashMap<NearMeInfo, String> TD_Locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupWindowAnimation();
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupToolbar();
        //toolbar.setFocusable(true);

//        ArrayList<NearMeInfo> nmArray= new ArrayList<NearMeInfo>();
//
//        nmArray.add(new NearMeInfo("TD ATM", "4 KM", "moderate"));
//        nmArray.add(new NearMeInfo("TD Bank", "6 KM", "fast"));
//        nmArray.add(new NearMeInfo("TD Building", "14 KM", "slow"));

        NearMeInfo i1 = new NearMeInfo("TD ATM", "4 KM", "moderate", new ArrayList<String>(){{add("TD");}});
        NearMeInfo i2 = new NearMeInfo("TD Bank", "6 KM", "fast", new ArrayList<String>(){{add("TD");}});
        NearMeInfo i3 = new NearMeInfo("TD Building", "14 KM", "slow", new ArrayList<String>(){{add("lol");}});

        ArrayList<NearMeInfo> nmArray= new ArrayList<NearMeInfo>();

        nmArray.add(i1);
        nmArray.add(i2);
        nmArray.add(i3);

        //recycler view
        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler);

        mLayoutManager = new LinearLayoutManager(MainActivity.this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CustomAdapter(mDataSet, mDataSetTypes, nmArray, this);

        mRecyclerView.setAdapter(mAdapter);
        //setup buttons
        //setupButtons();

        //drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupWindowAnimation(){
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.RIGHT);
        slideTransition.setDuration(500);
        getWindow().setReenterTransition(slideTransition);
        getWindow().setExitTransition(slideTransition);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

//    private void setupButtons(){
//        saveBtn = (Button)findViewById(R.id.saveBtn);
//        chequeBtn = (Button)findViewById(R.id.chequeBtn);
//        mcBtn = (Button)findViewById(R.id.mcBtn);
//        tfsaBtn = (Button)findViewById(R.id.tfsaBtn);
//
//        //setup height here
//        String cheq = "CHEQUING";
//        String save = "SAVINGS";
//        String mc = "MASTERCARD";
//        String tfsa = "TAX-FREE SAVINGS ACCOUNT";
//
//        SpannableStringBuilder ss = new SpannableStringBuilder();
//        SpannableString s = new SpannableString(cheq + "\n");
//        s.setSpan(new AbsoluteSizeSpan(9, true), 0, cheq.length(), 0);
//        s.setSpan(new ForegroundColorSpan(Color.parseColor("#EA7C07")), 0,cheq.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        ss.append(s);
//
//        String temp = "hello";
//
//        SpannableString s2 = new SpannableString(temp);
//        s2.setSpan(new AbsoluteSizeSpan(24, true), 0, temp.length(), 0);
//        ss.append(s2);
//        chequeBtn.setText(ss, TextView.BufferType.SPANNABLE);
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //3 dot menu
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch(item.getItemId())
        {
            case R.id.nav_logout:
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                break;
            case R.id.nav_contact:
                break;
            case R.id.nav_home:
                break;
            case R.id.nav_location:
                break;
            case R.id.nav_nfc:
                break;
            case R.id.nav_transfer:
                break;
            case R.id.nav_wallet:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
