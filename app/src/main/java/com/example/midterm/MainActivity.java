package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.security.MessageDigest;

public class MainActivity extends AppCompatActivity {
    private EditText EditUsername, EditPassword;
    private Button btnLogin;
    private String username = "", password=setSHA256("12345678");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListener();
    }

    private void findViews() {
        EditUsername = findViewById(R.id.edit_username);
        EditPassword = findViewById(R.id.edit_password);
        btnLogin = findViewById(R.id.btn_login);
    }

    private void setListener(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUsername = EditUsername.getText().toString();
                String inputPassword = setSHA256(EditPassword.getText().toString());

                if (inputUsername.equals(username) && inputPassword.equals(password)) {
                    Toast.makeText(MainActivity.this, "Login Succesfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, Home.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Login Fail", Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    private String setSHA256(String x){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(x.getBytes());

            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }

    }
}