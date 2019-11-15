package com.example.midterm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    private ListView ListViewGame;
    private ArrayList<Game> arrayListGame= new ArrayList<>();
    private GameAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ListViewGame = findViewById(R.id.lvgame);
        new GetDataTask().execute();

        adapter = new GameAdapter(arrayListGame,this);


        ListViewGame.setAdapter(adapter);

        ListViewGame.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Game game = (Game)parent.getAdapter().getItem(position);

                Intent i = new Intent(Home.this,GameProfile.class);
                i.putExtra("gamename",game.getGamename());
                i.putExtra("rating",game.getRating());
                i.putExtra("price",game.getPrice());
                i.putExtra("description",game.getDescription());
                startActivity(i);
            }
        });
    }
    /**
     * Creating Get Data Task for Getting Data From Web
     */
    class GetDataTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /**
             * Progress Dialog for User Interaction
             */
            dialog = new ProgressDialog(Home.this);
            dialog.setTitle("Please wait");
            dialog.setMessage("Getting JSON");
            dialog.show();
        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            JSONObject jsonObject = JSON.getDataFromWeb();

            try {
                if (jsonObject != null) {
                    if(jsonObject.length() > 0) {
                        JSONArray array = jsonObject.getJSONArray("game");

                        int lenArray = array.length();
                        if(lenArray > 0) {
                            for(int jIndex = 0; jIndex < lenArray; jIndex++) {
                                Game games = new Game();

                                JSONObject game = array.getJSONObject(jIndex);
                                String name = game.getString("name");
                                String rating = game.getString("rating");
                                String price = game.getString("price");
                                String description=game.getString("description");
///////////////////////////////////////////////////////////////////////////////////////////////////
                                games.setGamename(name);
                                games.setRating(rating);
                                games.setPrice(price);
                                games.setDescription(description);

                                arrayListGame.add(games);
                            }
                        }
                    }
                } else {

                }
            } catch (JSONException je) {
                Log.i(JSON.TAG, "" + je.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();

            if(arrayListGame.size() > 0) {
                adapter.notifyDataSetChanged();
            }
        }
    }
}
