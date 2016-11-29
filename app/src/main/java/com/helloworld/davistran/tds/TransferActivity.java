package com.helloworld.davistran.tds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class TransferActivity extends AppCompatActivity {

    Spinner fromSpinner;
    Spinner toSpinner;
    String from;
    String to;
    EditText amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        fromSpinner = (Spinner)findViewById(R.id.fromAccount);
        toSpinner = (Spinner)findViewById(R.id.toAccount);

        toSpinner.setEnabled(false);
        toSpinner.setClickable(false);

        ArrayList<String> al = new ArrayList<String>();
        al.add("Chequing");
        al.add("Savings");
        al.add("MasterCard");
        al.add("TFSA");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, al);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromSpinner.setAdapter(adapter);

        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String type = fromSpinner.getSelectedItem().toString();
                ArrayList<String> al2 = new ArrayList<String>();
                switch(type)
                {
                    case "Chequing":
                        al2.add("Savings");
                        al2.add("MasterCard");
                        al2.add("TFSA");
                        from = "Chequing";
                        break;
                    case "Savings":
                        al2.add("Chequing");
                        al2.add("MasterCard");
                        al2.add("TFSA");
                        from = "Savings";
                        break;
                    case "MasterCard":
                        al2.add("Savings");
                        al2.add("Chequing");
                        al2.add("TFSA");
                        from = "MasterCard";
                        break;
                    case "TFSA":
                        al2.add("Savings");
                        al2.add("MasterCard");
                        al2.add("Chequing");
                        from = "TFSA";
                        break;
                }

                toSpinner.setEnabled(true);
                toSpinner.setClickable(true);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, al2);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                toSpinner.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getSupportActionBar().setTitle("Transfering Withing Accounts");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }

    public void makeTransfer(View view) {
        switch(view.getId())
        {
            case R.id.makeTransfer:
                //make transfer ehre
                amount = (EditText)findViewById(R.id.transferAmount);
                String amnt = "";
                if(amount.getText().toString() != "" || !amount.getText().toString().isEmpty())
                {
                    amnt = amount.getText().toString();
                    switch (from)
                    {
                        case "Chequing":
//                            if(toSpinner.getSelectedItem().toString().matches("Savings")) {
//                                MainActivity.Cheque -= Double.parseDouble(amnt);
//                                MainActivity.Savings += Double.parseDouble(amnt);
//                            }
                            break;
                        case "Savings":
                            break;
                        case "MasterCard":
                            break;
                        case "TFSA":
                            break;
                    }
                    Toast.makeText(this, "Transfer of " +amnt  + " Completed", Toast.LENGTH_SHORT);
                    finish();
                }else {
                    Toast.makeText(this, "Please enter an ammount...", Toast.LENGTH_SHORT);
                    amount.requestFocus();
                }

                break;
        }
    }
}
