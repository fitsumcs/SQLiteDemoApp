package com.example.databseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddRecoredActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText addressEditText;

    private DatabaseHelper myHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recored);

        nameEditText = (EditText) findViewById(R.id.name_edittext);
        addressEditText = (EditText) findViewById(R.id.address_edittext);

        myHelper = new DatabaseHelper(this);
        myHelper.open();
    }

    public void addButtonPressed(View view) {
        String name = nameEditText.getText().toString();
        String address = addressEditText.getText().toString();

        myHelper.addRecord(name, address);

        finish();


    }
}