package com.helloworld.davistran.tds;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import org.w3c.dom.Text;

public class NFCReadActivity extends AppCompatActivity {

    NFCManager nfcManager;
    public static final int NOTIFICATION_ID = 1;
    private ProgressBar spinner;
    Context thisContext;
    String message;
    ViewSwitcher switcher;
    TextView textView;
    private AlertDialog.Builder dlgAlert;
    private int attempts = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfcread);

        spinner = (ProgressBar)findViewById(R.id.progressBar2);
        spinner.setVisibility(View.VISIBLE);
        switcher = (ViewSwitcher)findViewById(R.id.my_switcher2);
        final EditText et = new EditText(NFCReadActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        et.setLayoutParams(lp);

        getSupportActionBar().setTitle("Receiving NFC E-Transfer");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textView = (TextView)findViewById(R.id.nfcReceived);
        nfcManager = new NFCManager(this);
        nfcManager.onActivityCreate();
        thisContext = this;

        nfcManager = new NFCManager(this);
        nfcManager.onActivityCreate();

        nfcManager.setOnTagReadListener(new NFCManager.TagReadListener() {
            @Override
            public void onTagRead(String tagRead) {
                final String readMSG = tagRead;
                spinner.setVisibility(View.GONE);
                switcher.showNext();
                dlgAlert = new AlertDialog.Builder(NFCReadActivity.this);
                dlgAlert.setMessage("Please Enter The Password: ");
                dlgAlert.setView(et);
                dlgAlert.setTitle("NFC E-Transfer Password");
                dlgAlert.setPositiveButton("OK",  null);
                dlgAlert.setNegativeButton("CANCEL", null);
                dlgAlert.setCancelable(false);


                final AlertDialog mAlertDialog = dlgAlert.create();
                mAlertDialog.setOnShowListener(new DialogInterface.OnShowListener() {

                    @Override
                    public void onShow(DialogInterface dialog) {

                        Button b = mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        final String[] parts = readMSG.split("\\s+");

                        b.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                if(!(et.getText().toString().matches("")) && et.getText().toString().matches(parts[2])){
                                    mAlertDialog.dismiss();
                                    Toast.makeText(NFCReadActivity.this, "NFC E-Transfer Received", Toast.LENGTH_LONG).show();
                                    String message = "";
                                    if(parts.length < 2)
                                        message = "No Message";
                                    else
                                        message = parts[1];

                                    MainActivity.Cheque += Double.parseDouble(parts[0]);
//                                    switch(parts[4])
//                                    {
//                                        //should let them choose an account
//                                        case "Chequing":
//                                            MainActivity.Cheque -= Double.parseDouble(parts[]);
//                                            break;
////                                        case "Savings":
////                                            MainActivity.Savings -= Double.parseDouble(amnt);
////                                            break;
////                                        case "MasterCard":
////                                            MainActivity.MasterCard -= Double.parseDouble(amnt);
////                                            break;
////                                        case "TFSA":
////                                            MainActivity.TFSA -= Double.parseDouble(amnt);
////                                            break;
//
//                                    }
                                    textView.setText("NFC E-Transfer Received: \n" + "Amount: " + parts[0] + "\n" + "Message: " + message);
                                    //UPDATE ACCOUNT HERE
                                }else{
                                    if(attempts!=0) {
                                        attempts--;
                                        Toast.makeText(NFCReadActivity.this, "Please enter the correct password\n You have " + attempts + " attempts left", Toast.LENGTH_SHORT).show();
                                    }else
                                        finish();
                                }
                            }
                        });
                        Button b2 = mAlertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                        b2.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        });
                    }
                });
                mAlertDialog.show();
            }
        });
    }
    @Override
    public void onNewIntent(Intent intent) {
        nfcManager.readTag(intent);
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
