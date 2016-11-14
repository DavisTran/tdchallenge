package com.helloworld.davistran.tds;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        setupWindowAnimation();
        getWindow().setAllowEnterTransitionOverlap(false);
    }

    private void setupWindowAnimation(){
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.LEFT);
        slideTransition.setDuration(500);
        getWindow().setReenterTransition(slideTransition);
        getWindow().setExitTransition(slideTransition);
    }

    public void loginOnClick(View view) {
        switch(view.getId())
        {
            case R.id.btnLogin:
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
        }
    }
}
