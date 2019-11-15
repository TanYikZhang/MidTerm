package com.example.midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class GameProfile extends AppCompatActivity {

    private String Gamename,Rating,Price,Description;
    private TextView tvGamename,tvRating,tvPrice,tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamedetail);

        Gamename = getIntent().getStringExtra("gamename");
        Rating= getIntent().getStringExtra("rating");
        Price = getIntent().getStringExtra("price");
        Description = getIntent().getStringExtra("description");

        tvGamename = findViewById(R.id.gamename);
        tvGamename.setText(Gamename);
        tvRating = findViewById(R.id.rating);
        tvRating.setText(Rating+"/10");
        tvPrice = findViewById(R.id.price);
        tvPrice.setText("$"+Price);
        tvDescription = findViewById(R.id.description);
        tvDescription.setText(Description);
    }
}
