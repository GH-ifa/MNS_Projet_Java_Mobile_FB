package com.example.rickmortycharacters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class RMCharactersListAdapter extends RecyclerView.Adapter<RMCharactersListAdapter.CharactersViewHolder> {

    private ArrayList<RMCharacter> charactersList;

    public static class CharactersViewHolder extends RecyclerView.ViewHolder {

        TextView itemCharacterName;
        TextView itemCharacterGender;
        ImageView itemCharacterImg;

        public CharactersViewHolder(@NonNull View itemView) {
            super(itemView);
            itemCharacterName = itemView.findViewById(R.id.characterName);
            itemCharacterGender = itemView.findViewById(R.id.characterGender);
            itemCharacterImg = itemView.findViewById(R.id.characterImg);
        }
    }

    public RMCharactersListAdapter(ArrayList<RMCharacter> charactersList) {
        this.charactersList = charactersList;
    }

    @NonNull
    @Override
    public CharactersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_rmcharacter,parent,false);

        CharactersViewHolder viewHolder = new CharactersViewHolder(view);
        StrictMode.ThreadPolicy policy =          new StrictMode.ThreadPolicy.Builder().permitAll().build();         StrictMode.setThreadPolicy(policy);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CharactersViewHolder holder, int position) {
        holder.itemCharacterName.setText(charactersList.get(position).getName());
        holder.itemCharacterGender.setText(charactersList.get(position).getGender());
        holder.itemCharacterImg.setImageBitmap(this.getBitmapFromURL(charactersList.get(position).getImg()));
    }

    @Override
    public int getItemCount() {
        return charactersList.size();
    }


    public Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }
}