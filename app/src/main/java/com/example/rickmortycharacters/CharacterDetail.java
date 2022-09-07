package com.example.rickmortycharacters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.JsonObjectRequest;

public class CharacterDetail extends AppCompatActivity {

    TextView itemCharacterName;
    TextView itemCharacterGender;
    ImageView itemCharacterImg;
    TextView itemCharacterSpecies;
    TextView itemCharacterStatus;

    Integer characterId;
    RMCharacter rmCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //bundle extra id
        this.characterId = 1;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.characterId = extras.getInt("charId");
        }

        Log.d("RMC", "Detail");

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(
                "https://rickandmortyapi.com/api/character/"+this.characterId,
                response -> {

                    this.rmCharacter = new RMCharacter(response);
                    itemCharacterName = findViewById(R.id.detailName);
                    itemCharacterGender = findViewById(R.id.detailGender);
                    itemCharacterImg = findViewById(R.id.detailImg);
                    itemCharacterSpecies = findViewById(R.id.detailSpecies);
                    itemCharacterStatus = findViewById(R.id.detailStatus);

                    itemCharacterName.setText(rmCharacter.getName());
                    itemCharacterGender.setText(rmCharacter.getGender());
                    itemCharacterImg.setImageBitmap(RMCharactersListAdapter.getBitmapFromURL(rmCharacter.getImg()));
                    itemCharacterSpecies.setText(rmCharacter.getSpecies());
                    itemCharacterStatus.setText(rmCharacter.getStatus());


                },
                error -> Log.d("volley",error.toString())
        );

        RequestManager.getInstance(this).addToRequestQueue(jsonArrayRequest);
    }
}