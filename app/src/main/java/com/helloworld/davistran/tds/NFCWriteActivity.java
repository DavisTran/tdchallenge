package com.helloworld.davistran.tds;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Build;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.nio.charset.Charset;

public class NFCWriteActivity extends AppCompatActivity {

    NFCManager nfcManager;
    private ProgressBar spinner;
    private ViewSwitcher switcher;
    private String sender;
    private String amnt, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

        switcher = (ViewSwitcher)findViewById(R.id.my_switcher);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);

        Intent in = getIntent();
        Bundle extras = in.getExtras();
        amnt = extras.getString("amount");
        message = extras.getString("message");

        nfcManager = new NFCManager(this);
        nfcManager.onActivityCreate();

        nfcManager.setOnTagWriteListener(new NFCManager.TagWriteListener() {
            @Override
            public void onTagWritten() {
                Toast.makeText(NFCWriteActivity.this, "Tag Written: " + nfcManager.getWrittenData(), Toast.LENGTH_LONG).show();
            }
        });
        nfcManager.setOnTagWriteErrorListener(new NFCManager.TagWriteErrorListener() {
            @Override
            public void onTagWriteError(NFCWriteException exception) {
                Toast.makeText(NFCWriteActivity.this, exception.getType().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    // fires on NFC card detected
    @Override
    public void onNewIntent(Intent intent){
        //sColor = getIntent().getExtras().getString("mycolor");
        String msg = amnt + ", " + message;
        nfcManager.setWrittenData(msg);
        nfcManager.writeDataToTag(intent,msg);
    }

    @Override
    protected void onResume() {
        super.onResume();
        nfcManager.onActivityResume();
    }

    @Override
    protected void onPause() {
        nfcManager.onActivityPause();
        super.onPause();
    }

    public void onClick(View view) {
        // go back to the mainActivity
        Intent intent = new Intent(NFCWriteActivity.this,MainActivity.class);
        startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
