package com.example.rickmortycharacters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<RMCharacter> characters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("RMC", "début");

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(
                "https://rickandmortyapi.com/api/character",
                response -> {
                    characters = new ArrayList<>();
                    Log.d("RMC", "salut");
                    try {
                        JSONArray charactersJson = response.getJSONArray("results");

                        for(int i = 0; i < charactersJson.length(); i++ ) {
                            try {
                                JSONObject json = charactersJson.getJSONObject(i);
                                RMCharacter rmCharacter = new RMCharacter(json);
                                characters.add(rmCharacter);
                                Log.d("RMC", rmCharacter.getName());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        RecyclerView rvProductList = findViewById(R.id.rvCharactersList);
                        rvProductList.setLayoutManager(new LinearLayoutManager(this));
                        rvProductList.setAdapter(new RMCharactersListAdapter(characters));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                },
                error -> Log.d("volley",error.toString())
        );

        RequestManager.getInstance(this).addToRequestQueue(jsonArrayRequest);
    }

}