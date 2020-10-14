package com.example.databseexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ModifyRecord extends AppCompatActivity {


    private EditText nameText;
    private EditText addressText;

    private long _id;

    private DatabaseHelper myHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_record);

        myHelper = new DatabaseHelper(this);
        myHelper.open();

        nameText = (EditText) findViewById(R.id.name_edittext);
        addressText = (EditText) findViewById(R.id.address_edittext);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String desc = intent.getStringExtra("address");

        _id = Long.parseLong(id);

        nameText.setText(name);
        addressText.setText(desc);
    }

    private void returnToMainActivity()
    {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    public void updateButtonPressed(View view) {
        String name = nameText.getText().toString();
        String address = addressText.getText().toString();

        myHelper.updateRecord(_id, name, address);
        returnToMainActivity();
    }

    public void deleteButtonPressed(View view) {
        myHelper.deleteRecord(_id);
        returnToMainActivity();

    }
}