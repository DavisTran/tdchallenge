package com.helloworld.davistran.tds;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
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

//        FloatingActionButton fbNFC = (FloatingActionButton) findViewById(R.id.fb_nfc);
//        FloatingActionButton fbTransfer = (FloatingActionButton) findViewById(R.id.fb_payment);
//        FloatingActionButton fbPayment = (FloatingActionButton) findViewById(R.id.fb_transfer);
//        fbNFC.setVisibility(View.INVISIBLE);
//        fbTransfer.setVisibility(View.INVISIBLE);
//        fbPayment.setVisibility(View.INVISIBLE);
        setupWindowAnimation();
    }

    private void setupWindowAnimation(){
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.BOTTOM);
        slideTransition.setDuration(700);
        slideTransition.excludeTarget(R.id.heroContainer, true);
        slideTransition.excludeTarget(R.id.toolbar, true);
        slideTransition.excludeTarget(android.R.id.statusBarBackground, true);
        slideTransition.excludeTarget(android.R.id.navigationBarBackground, true);
        slideTransition.excludeTarget(R.id.fb_nfc, true);
        slideTransition.excludeTarget(R.id.fb_payment, true);
        slideTransition.excludeTarget(R.id.fb_transfer, true);
        slideTransition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                ScaleAnimation anim = new ScaleAnimation(0,1,0,1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                anim.setFillBefore(true);
                anim.setFillAfter(true);
                anim.setFillEnabled(true);
                anim.setDuration(500);
                anim.setInterpolator(new OvershootInterpolator());

                FloatingActionButton fbNFC = (FloatingActionButton) findViewById(R.id.fb_nfc);
                FloatingActionButton fbTransfer = (FloatingActionButton) findViewById(R.id.fb_payment);
                FloatingActionButton fbPayment = (FloatingActionButton) findViewById(R.id.fb_transfer);
                fbNFC.bringToFront();
                fbTransfer.bringToFront();
                fbPayment.bringToFront();
                fbNFC.setAnimation(anim);
                fbTransfer.setAnimation(anim);
                fbPayment.setAnimation(anim);
            }

            @Override
            public void onTransitionCancel(Transition transition) {
            }

            @Override
            public void onTransitionPause(Transition transition) {
            }

            @Override
            public void onTransitionResume(Transition transition) {
            }
        });
        getWindow().setEnterTransition(slideTransition);
        getWindow().setReenterTransition(slideTransition);
        getWindow().setReturnTransition(slideTransition);
        getWindow().setExitTransition(new Explode());
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
                RelativeLayout heroContainer = (RelativeLayout) findViewById(R.id.heroContainer);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, heroContainer, "heroContainer");
                startActivity(i, options.toBundle());
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

    public void accountSelected(View view) {
//        animateButtonsOut();
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        Intent i = new Intent(this, AccountActivity.class);
        switch(view.getId())
        {
            case R.id.saveBtn:
                i.putExtra("accountType", "Savings");
                break;
            case R.id.chequeBtn:
                i.putExtra("accountType", "Chequing");
                break;
            case R.id.ccBtn:
                i.putExtra("accountType", "MasterCard");
                break;
            case R.id.tfsaBtn:
                i.putExtra("accountType", "TFSA");
                break;
        }

        startActivity(i, options.toBundle());
    }

    private void animateButtonsOut() {
        ScaleAnimation anim = new ScaleAnimation(1,0,1,0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setFillBefore(true);
        anim.setFillAfter(true);
        anim.setFillEnabled(true);
        anim.setDuration(500);
        anim.setInterpolator(new AccelerateInterpolator());

        FloatingActionButton fbNFC = (FloatingActionButton) findViewById(R.id.fb_nfc);
        FloatingActionButton fbTransfer = (FloatingActionButton) findViewById(R.id.fb_payment);
        FloatingActionButton fbPayment = (FloatingActionButton) findViewById(R.id.fb_transfer);

        fbNFC.setAnimation(anim);
        fbTransfer.setAnimation(anim);
        fbPayment.setAnimation(anim);
    }

    public void FABSelected(View view) {
        switch(view.getId())
        {
            case R.id.fb_nfc:
                Intent nfc = new Intent(this, NFCFormActivity.class);
                //nfc.putExtra();
                startActivity(nfc);
                break;
            case R.id.fb_payment:
                Intent pay = new Intent(this, NFCFormActivity.class);
                //nfc.putExtra();
                startActivity(pay);
                break;
            case R.id.fb_transfer:
                Intent trans = new Intent(this, NFCFormActivity.class);
                //nfc.putExtra();
                startActivity(trans);
                break;
        }
    }
}
