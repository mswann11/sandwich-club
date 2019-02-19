package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);

        String json = sandwiches[position];

        try {
            sandwich = JsonUtils.parseSandwichJson(json);
            Log.v("DetailActivity", sandwich.getMainName());
        } catch (Exception e){
            e.printStackTrace();
            closeOnError();
            return;
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(imageIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    //DONE (2): Populate UI in activity_detail.xml
    private void populateUI() {
        TextView originTv = findViewById(R.id.origin_tv);
        if(sandwich.getPlaceOfOrigin() != null && !sandwich.getPlaceOfOrigin().equals("")) {
            originTv.setText(sandwich.getPlaceOfOrigin());
        } else {
            TextView originLabelTv = findViewById(R.id.origin_label_tv);
            View originLine = findViewById(R.id.origin_line);
            originLabelTv.setVisibility(View.GONE);
            originTv.setVisibility(View.GONE);
            originLine.setVisibility(View.GONE);
        }

        TextView alsoKnownTv = findViewById(R.id.also_known_tv);
        List<String> alsoKnownArray = sandwich.getAlsoKnownAs();
        if(alsoKnownArray.size() > 0) {
            alsoKnownTv.setText(alsoKnownArray.get(0));
            for(int i=1; i<alsoKnownArray.size(); i++) {
                alsoKnownTv.append(", ");
                alsoKnownTv.append(alsoKnownArray.get(i));
            }
        } else {
            TextView alsoKnownLabelTv = findViewById(R.id.also_known_label_tv);
            View alsoKnownLine = findViewById(R.id.also_known_line);
            alsoKnownLabelTv.setVisibility(View.GONE);
            alsoKnownTv.setVisibility(View.GONE);
            alsoKnownLine.setVisibility(View.GONE);
        }

        TextView descriptionTv = findViewById(R.id.description_tv);
        if(sandwich.getDescription() != null && !sandwich.getDescription().equals("")) {
            descriptionTv.setText(sandwich.getDescription());
            descriptionTv.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        } else {
            TextView descriptionLabelTv = findViewById(R.id.description_label_tv);
            descriptionLabelTv.setVisibility(View.GONE);
            descriptionTv.setVisibility(View.GONE);
        }

        TextView ingredientsTv = findViewById(R.id.ingredients_tv);
        List<String> ingredientsArray = sandwich.getIngredients();
        if(ingredientsArray.size() > 0) {
            ingredientsTv.setText(ingredientsArray.get(0));
            for (int i=1; i<ingredientsArray.size(); i++) {
                ingredientsTv.append(", ");
                ingredientsTv.append(ingredientsArray.get(i));
            }
        } else {
            TextView ingredientsLabelTv = findViewById(R.id.ingredients_label_tv);
            View ingredientsLine = findViewById(R.id.ingredients_line);
            ingredientsLabelTv.setVisibility(View.GONE);
            ingredientsTv.setVisibility(View.GONE);
            ingredientsLine.setVisibility(View.GONE);
        }
    }
}
