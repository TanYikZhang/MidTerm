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

            /**
             * Getting JSON Object from Web Using okHttp
             */
            JSONObject jsonObject = JSON.getDataFromWeb();

            try {
                /**
                 * Check Whether Its NULL???
                 */
                if (jsonObject != null) {
                    /**
                     * Check Length...
                     */
                    if(jsonObject.length() > 0) {
                        /**
                         * Getting Array named "contacts" From MAIN Json Object
                         */
                        JSONArray array = jsonObject.getJSONArray("game");

                        /**
                         * Check Length of Array...
                         */
                        int lenArray = array.length();
                        if(lenArray > 0) {
                            for(int jIndex = 0; jIndex < lenArray; jIndex++) {

                                /**
                                 * Creating Every time New Object
                                 * and
                                 * Adding into List
                                 */
                                Game games = new Game();

                                /**
                                 * Getting Inner Object from contacts array...
                                 * and
                                 * From that We will get Name of that Contact
                                 *
                                 */
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

                                /**
                                 * Adding name and phone concatenation in List...
                                 */
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
            /**
             * Checking if List size if more than zero then
             * Update ListView
             */
            if(arrayListGame.size() > 0) {
                adapter.notifyDataSetChanged();
            }
        }
    }
}
