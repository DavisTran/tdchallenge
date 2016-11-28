package com.helloworld.davistran.tds;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NFCReadActivity extends AppCompatActivity {

    NFCManager nfcManager;
    public static final int NOTIFICATION_ID = 1;
    Context thisContext;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfcread);

        nfcManager = new NFCManager(this);
        nfcManager.onActivityCreate();
        thisContext = this;


    }
}
