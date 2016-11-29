package com.helloworld.davistran.tds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

public class NFCFormActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText recipientET, amountET, messageET, passwordET;
    ViewSwitcher switcher;
    View btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfcform);

        switcher = (ViewSwitcher)findViewById(R.id.my_switcher3);
        recipientET = (EditText)findViewById(R.id.recipientET);
        amountET = (EditText)findViewById(R.id.amountET);
        messageET = (EditText)findViewById(R.id.messageET);
        passwordET = (EditText)findViewById(R.id.passwordET);
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
                if(!recipientET.getText().toString().matches("") && !amountET.getText().toString().matches("")
                        && !messageET.getText().toString().matches("") && !passwordET.getText().toString().matches("")) {
                    Intent nfc = new Intent(this, NFCWriteActivity.class);
                    nfc.putExtra("recipient", recipientET.getText().toString());
                    nfc.putExtra("amount", amountET.getText().toString());
                    nfc.putExtra("message", messageET.getText().toString());
                    nfc.putExtra("password", passwordET.getText().toString());
                    nfc.putExtra("type", spinner.getSelectedItem().toString());
                    startActivity(nfc);
                }else
                    Toast.makeText(this, "Please make sure all the fields are filled appropriately", Toast.LENGTH_SHORT).show();
                break;
            case R.id.NFCCancel:
                switcher.showPrevious();
                btn = findViewById(R.id.receiveNFC);
                btn.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void NFCChoice(View view) {
        switch(view.getId())
        {
            case R.id.sendNFC:
                switcher.showNext();
                btn = findViewById(R.id.receiveNFC);
                btn.setVisibility(View.GONE);
                break;
            case R.id.receiveNFC:
                Intent i = new Intent(NFCFormActivity.this, NFCReadActivity.class);
                startActivity(i);
                break;
        }
    }
}
