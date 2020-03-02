package com.leul.uersmanagment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserPage extends AppCompatActivity {
    TextView textViewUN,textViewPass;
    EditText editTextPass;
    SharedPreferences sharedPreferences;
    static final String DEFAULT="N/A";
    DatabaseHelper dataBaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        textViewUN = findViewById(R.id.un1);
        textViewPass = findViewById(R.id.pass1);
        editTextPass = findViewById(R.id.et_delete_row);
        dataBaseHelper = new DatabaseHelper(this);

    }

    public void load(View view) {
        sharedPreferences = getSharedPreferences("mydata", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("uname",DEFAULT);
        String password = sharedPreferences.getString("pass",DEFAULT);
        if (textViewUN.equals(DEFAULT)||textViewPass.equals(DEFAULT)){
            Toast.makeText(this, "no data is found", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "the data is found", Toast.LENGTH_SHORT).show();
        textViewUN.setText(username);
        textViewPass.setText(password);
    }

    public void logout(View view) {
        startActivity(new Intent(this,LogIN.class));
    }

    public void gotoRV(View view) {
        startActivity(new Intent(UserPage.this,UserList.class));

    }
    public void delete(View view) {
        Integer deleteRows = dataBaseHelper.deleteData(editTextPass.getText().toString());
        if (deleteRows>0) {
            editTextPass.setText("");
            Toast.makeText(this, "data deleted", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "data not deleted, please enter password?", Toast.LENGTH_SHORT).show();
    }
}

