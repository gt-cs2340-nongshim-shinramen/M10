package com.example.m6.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.m6.R;
import com.example.m6.model.Player;
import com.example.m6.model.Resource;
import com.example.m6.model.TechLevel;

/**
 * Information page of the Current Planet
 */
@SuppressWarnings("ALL")
public class CurrentPlanetActivity extends AppCompatActivity {

    private Button menuButton;
    private TextView name;
    private TextView techLevel;
    private TextView resource;
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_planet);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        player = (Player) getIntent().getSerializableExtra("player");
        name = findViewById(R.id.system_name);
        techLevel = findViewById(R.id.system_techLevel);
        resource = findViewById(R.id.system_resource);
        menuButton = findViewById(R.id.menu_button);


        name.setText(player.getCurrentplanet().getName());
        techLevel.setText(TechLevel.values()[player.getCurrentplanet().getTechLevel()].toString());
        resource.setText(Resource.values()[player.getCurrentplanet().getResource()].toString());

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });


    }

    /**
     * This method opens the menu page
     */
    public void openMenu() {
        player.setWarped(false);
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("player", player);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        if (player.getWarped()) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Cannot move back");
            builder.setMessage("You cannot go back before warped");
            builder.show();
        } else {
            super.onBackPressed();
        }
    }
}
