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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.nio.charset.Charset;

public class NFCWriteActivity extends AppCompatActivity {

    NFCManager nfcManager;
    private ProgressBar spinner;
    private ViewSwitcher switcher;
    private String sender;
    private String amnt, message, password, recip, type;
    private TextView sendTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

        switcher = (ViewSwitcher)findViewById(R.id.my_switcher);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);
        sendTV = (TextView)findViewById(R.id.sendTV);
        getSupportActionBar().setTitle("Sending NFC E-Transfer");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent in = getIntent();
        Bundle extras = in.getExtras();
        recip = extras.getString("recipient");
        amnt = extras.getString("amount");
        message = extras.getString("message");
        password = extras.getString("password");
        type = extras.getString("type");

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
        String msg = amnt + " " + message + " " + password + " " + recip;
        sendTV.setText("NFC E-Transfer Sent: \nAccount Type: " + type +"\nRecipient: " + recip + "\nAmount: " + amnt + "\nMessage: " + message );
        switch(type)
        {
            case "Chequing":
                MainActivity.Cheque -= Double.parseDouble(amnt);
                break;
            case "Savings":
                MainActivity.Savings -= Double.parseDouble(amnt);
                break;
            case "MasterCard":
                MainActivity.MasterCard -= Double.parseDouble(amnt);
                break;
            case "TFSA":
                MainActivity.TFSA -= Double.parseDouble(amnt);
                break;
        }
        switcher.showNext();
        nfcManager.setWrittenData(msg);
        nfcManager.writeDataToTag(intent,msg);
        spinner.setVisibility(View.GONE);
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

    @Override
    public void onBackPressed()
    {
        finish();
    }
}
