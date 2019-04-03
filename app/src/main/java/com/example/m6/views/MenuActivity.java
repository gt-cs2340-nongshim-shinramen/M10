package com.example.m6.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.m6.R;
import com.example.m6.model.Player;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buyButton, sellButton, infoButton, playerButton;

    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        player = (Player)getIntent().getSerializableExtra("player");
        //this log checks whether player instance import successfully
        buyButton = findViewById(R.id.buy_button);
        buyButton.setOnClickListener(this);

        sellButton = findViewById(R.id.sell_button);
        sellButton.setOnClickListener(this);

        infoButton = findViewById(R.id.system_info_button);
        infoButton.setOnClickListener(this);

        playerButton = findViewById(R.id.player_info_button);
        playerButton.setOnClickListener(this);

    }

    public void openBuyGoods() {
        Intent intent = new Intent(this, BuyGoodsActivity.class);
        intent.putExtra("player", player);
        startActivity(intent);
    }

    public void openSellGoods() {
        Intent intent = new Intent(this, SellGoodsActivity.class);
        intent.putExtra("player", player);
        startActivity(intent);
    }
    public void openSystem(){
        Intent intent = new Intent(this, CurrentPlanetActivity.class);
        intent.putExtra("player", player);
        startActivity(intent);
    }
    public void openPlayerInformation(){
        Intent intent = new Intent(this, player_information.class);
        intent.putExtra("player", player);
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy_button:
                openBuyGoods();
                break;
            case R.id.sell_button:
                openSellGoods();
                break;
            case R.id.system_info_button:
                Log.d("test", "system_info_button is clicked");
                openSystem();
                break;
            case R.id.player_info_button:
                openPlayerInformation();
                break;
        }
    }
}
