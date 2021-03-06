package com.leul.uersmanagment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Regester extends AppCompatActivity {
    EditText fullname,email,phone,username,password,gender;
    DatabaseHelper addRecord;
    RadioGroup radioGroup;
    RadioButton buttonMale,buttonFemale;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regester);
        fullname = findViewById(R.id.fullname_R);
        email = findViewById(R.id.email_R);
        phone = findViewById(R.id.phonenum_R);
        username = findViewById(R.id.username_R);
        password = findViewById(R.id.password_R);
        radioGroup = findViewById(R.id.rgMaleAndFemale);
        buttonMale = findViewById(R.id.rbMale);
        buttonFemale = findViewById(R.id.rbFemale);

        addRecord = new DatabaseHelper(this);

    }
    public void register(View view) {
        String fn =fullname.getText().toString();
        String em =email.getText().toString();
        String pn =phone.getText().toString();
        String un =username.getText().toString();
        String pa =password.getText().toString();
        String ge = ((RadioButton) findViewById(
                radioGroup.getCheckedRadioButtonId())).getText().toString();

        DatabaseHelper db = new DatabaseHelper(this);
        if (fn.isEmpty()){
            fullname.setError("full name is not entered");
            fullname.requestFocus();
        }
        else if (em.isEmpty()) {
            email.setError("email is not entered");
            email.requestFocus();
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(em).matches()){
            email.setError("Invalid Email format");
            email.requestFocus();
        }
        else if (pn.isEmpty()) {
            phone.setError("phone number is not entered");
            phone.requestFocus();
        }
        else if (un.isEmpty()) {
            username.setError("user name is not entered");
            username.requestFocus();
        }

        else if (pa.isEmpty()) {
            password.setError("password is not entered");
            password.requestFocus();
        }
        else {
            String res= db.userRegistered(fn,em,pn,un,ge,pa);
            Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
            sharedPreferences = getSharedPreferences("mydata", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("uname", username.getText().toString());
            editor.putString("pass", password.getText().toString());
            editor.commit();
            startActivity(new Intent(this,UserPage.class));
            fullname.setText("");
            email.setText("");
            phone.setText("");
            username.setText("");
            password.setText("");
        }


    }
}
