package com.example.m6.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.m6.R;
import com.example.m6.model.Planet;
import com.example.m6.model.Player;

/**
 *
 */
@SuppressWarnings("ALL")
public class Warp extends AppCompatActivity {
    private Player player;
    // --Commented out by Inspection (4/4/2019 2:10 PM):WarpAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warp);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        player = (Player)getIntent().getSerializableExtra("player");

        WarpAdapter adapter = new WarpAdapter(player);
        initRecyclerView(adapter);
        adapter.setOnDestinationClickListener(new WarpAdapter.OnDestinationClickListener() {
            @Override
            public void onDestinationClicked(Planet planet, TextView rfuel) {
                int fuel = Integer.parseInt(rfuel.getText().toString());
                Log.d("1111", rfuel.getText().toString());
                player.setCurrentplanet(planet);
                player.setFuel(player.getFuel()-fuel);
                moveToNewPlanet();
            }
        });
        Button menuButton = findViewById(R.id.button_warp_menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });



        Log.d("player", player.getName());
    }
    private void openMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("player", player);
        startActivity(intent);
    }
    private void moveToNewPlanet(){
        Intent intent = new Intent(this, CurrentPlanetActivity.class);
        player.setWarped(true);
        intent.putExtra("player", player);
        startActivity(intent);
        finish();
    }
    private void initRecyclerView(WarpAdapter adapter){
        RecyclerView recyclerView  = findViewById(R.id.warp_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

}
