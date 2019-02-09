package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    // DONE (1): Parse JSON Data from String and return Sandwich object
    public static Sandwich parseSandwichJson(String json) throws JSONException {
        String mainName;
        List<String> alsoKnownAs = new ArrayList<>();
        String placeOfOrigin;
        String description;
        String image;
        List<String> ingredients = new ArrayList<>();

        JSONObject sandwichJson = new JSONObject(json);
        JSONObject fullName = sandwichJson.getJSONObject("name");
        mainName = fullName.getString("mainName");

        JSONArray akaArray = fullName.getJSONArray("alsoKnownAs");
        if(akaArray != null){
            for(int i=0; i<akaArray.length(); i++){
                alsoKnownAs.add(akaArray.getString(i));
            }
        }

        placeOfOrigin = sandwichJson.getString("placeOfOrigin");
        description = sandwichJson.getString("description");
        image = sandwichJson.getString("image");

        JSONArray ingredientArray = sandwichJson.getJSONArray("ingredients");
        if(ingredientArray != null){
            for(int i=0; i<ingredientArray.length(); i++){
                ingredients.add(ingredientArray.getString(i));
            }
        }

        Sandwich sandwich = new Sandwich(mainName, alsoKnownAs,
                placeOfOrigin, description, image, ingredients);
        Log.v("JsonUtils", sandwich.getMainName() + "\n" + sandwich.getAlsoKnownAs()
                + "\n" + sandwich.getPlaceOfOrigin() + "\n" + sandwich.getDescription()
                + "\n" + sandwich.getImage() + "\n" + sandwich.getIngredients());
        return sandwich;
    }
}
