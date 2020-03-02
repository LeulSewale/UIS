package com.leul.uersmanagment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogIN extends AppCompatActivity {
    Button loginBtn;
    TextView registerTV;
    EditText urname,pass;
    DatabaseHelper db;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        urname = findViewById(R.id.username_L);
        pass = findViewById(R.id.password_L);
        loginBtn = findViewById(R.id.login_btn);

        db = new DatabaseHelper(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = urname.getText().toString().trim();
                String pwd = pass.getText().toString().trim();
                Boolean res = db.checkUser(user,pwd);
                if (user.isEmpty()){
                    urname.setError("user name not entered");
                    urname.requestFocus();
                }
                else if (pwd.isEmpty()){
                    pass.setError("password not entered");
                    pass.requestFocus();
                }
                else {
                    if (res == true) {
                        sharedPreferences = getSharedPreferences("mydata", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("uname", urname.getText().toString());
                        editor.putString("pass", pass.getText().toString());
                        editor.commit();
                        Intent intent = new Intent(LogIN.this, UserPage.class);
                        startActivity(intent);
                        Toast.makeText(LogIN.this, "Successfully logged", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LogIN.this, "Please enter correct username and password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        registerTV = findViewById(R.id.register_L);
        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIN.this,Regester.class);
                startActivity(intent);
            }
        });
    }
}
