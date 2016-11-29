package com.helloworld.davistran.tds;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by Davis on 11/28/2016.
 */

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.newInstance("Welcome the new TD Banking Application", "With this new banking application we have greatly improved user experience and added new features. Lets Begin...", R.drawable.tdlogo, Color.parseColor("#34B233")));
        addSlide(AppIntroFragment.newInstance("Login Page", "Here you can login using your online registered TD account or register by clicking on the register button.", R.drawable.loginpic, Color.parseColor("#34B233")));
        addSlide(AppIntroFragment.newInstance("Home Page - First Card", "This is the home page, here you can see many cards.\nFirst Card: Here you can see three buttons:\n1. The first button is a new feature called NFC E-Transfer (allows you to E-Transfer through NFC)." +
                "\n2. The second button allows you to transfer money within your accounts.\n3. The third button allows you to make payments.", R.drawable.card1, Color.parseColor("#34B233")));
        addSlide(AppIntroFragment.newInstance("Home Page - Second Card", "This card provides a brief summary of all your bank accounts.", R.drawable.accounts, Color.parseColor("#34B233")));
        addSlide(AppIntroFragment.newInstance("Home Page - Third Card", "This card also has a new feature which provides you with a summary of your expenses in a visual form.", R.drawable.card3, Color.parseColor("#34B233")));
        addSlide(AppIntroFragment.newInstance("Home Page - Fourth Card", "This card has a new feature that provides you with information about the three closes TD banks to you. It tells you how busy, the distance, and type of TD utility that is there.", R.drawable.nearby, Color.parseColor("#34B233") ));
        addSlide(AppIntroFragment.newInstance("That's All!", "Thank you TD application user, we hope you enjoy your experience", R.drawable.tdlogo, Color.parseColor("#34B233")));

        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
        //showSkipButton(false);
        //setProgressButtonEnabled(false);
    }
    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
        // Do something when users tap on Done button.
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);

        // Do something when the slide changes.
    }
}
