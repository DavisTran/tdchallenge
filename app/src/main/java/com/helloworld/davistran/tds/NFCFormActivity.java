package com.helloworld.davistran.tds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class NFCFormActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText recipientET, amountET, messageET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfcform);
        recipientET = (EditText)findViewById(R.id.recipientET);
        amountET = (EditText)findViewById(R.id.amountET);
        messageET = (EditText)findViewById(R.id.messageET);
        spinner = (Spinner)findViewById(R.id.accountSpinner);
        ArrayList<String> al = new ArrayList<String>();
        al.add("Chequing");
        al.add("Savings");
        al.add("MasterCard");
        al.add("TFSA");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, al);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        getSupportActionBar().setTitle("NFC E-Transfer");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }

    public void startNFC(View view) {
        switch(view.getId())
        {
            case R.id.startNFC:
                Intent nfc = new Intent(this, NFCWriteActivity.class);
                nfc.putExtra("recipient", recipientET.getText().toString());
                nfc.putExtra("amount", amountET.getText().toString());
                nfc.putExtra("message", messageET.getText().toString());
                startActivity(nfc);
                break;
        }
    }
}
