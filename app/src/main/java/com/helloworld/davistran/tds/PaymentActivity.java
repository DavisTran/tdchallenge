package com.helloworld.davistran.tds;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {

    Spinner fromSpinner;
    EditText amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        fromSpinner = (Spinner)findViewById(R.id.fromPayment);

        ArrayList<String> al = new ArrayList<String>();
        al.add("Chequing");
        al.add("Savings");
        al.add("MasterCard");
        al.add("TFSA");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, al);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromSpinner.setAdapter(adapter);

        getSupportActionBar().setTitle("Making a Payment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(this, MainActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        startActivity(i, options.toBundle());
    }

    public void makePayment(View view) {
        switch(view.getId())
        {
            case R.id.makePayment:
                //make transfer ehre
                amount = (EditText)findViewById(R.id.paymentAmount);
                String amnt = "";
                if(amount.getText().toString() != "" || !amount.getText().toString().isEmpty())
                {
                    amnt = amount.getText().toString();
                    Toast.makeText(this, "Transfer of " +amnt  + " Completed", Toast.LENGTH_SHORT);
                    finish();
                }else{
                    Toast.makeText(this, "Please enter an ammount...", Toast.LENGTH_SHORT);
                    amount.requestFocus();
                }

                break;
        }

    }
}
