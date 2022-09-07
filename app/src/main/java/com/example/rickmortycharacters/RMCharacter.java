package com.example.rickmortycharacters;

import org.json.JSONException;
import org.json.JSONObject;

public class RMCharacter {
    private String name;
    private String img;
    private String gender;

    public RMCharacter(JSONObject json) {
        try {
            this.name = json.getString("name");
            this.img = json.getString("image");
            this.gender = json.getString("gender");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //region GETTERS AND SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    //endregion
}
