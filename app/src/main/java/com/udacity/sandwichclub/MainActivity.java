package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
    implements SandwichAdapter.SandwichAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private SandwichAdapter mSandwichAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.sandwiches_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mSandwichAdapter = new SandwichAdapter(this);
        mRecyclerView.setAdapter(mSandwichAdapter);

        String[] sandwicheNames = getResources().getStringArray(R.array.sandwich_names);
        String[] sandwichImages = getResources().getStringArray(R.array.sandwich_images);
        mSandwichAdapter.setSandwichData(sandwicheNames, sandwichImages);
    }

    @Override
    public void OnClick(int position) {
        launchDetailActivity(position);
    }

    private void launchDetailActivity(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POSITION, position);
        startActivity(intent);
    }
}
