package com.example.m6.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.m6.R;
import com.example.m6.model.Goods;
import com.example.m6.model.Player;
import com.example.m6.viewmodels.c;

/**
 *
 */
public class BuyGoodsActivity extends AppCompatActivity implements BuyDialog.BuyDialogListener {
    private Player player;
    private int water;
    private int fur;
    private int food;
    private int ore;
    private int firearm;
    private int game;
    private int medicine;
    private int machine;
    private int narcortics;
    private int robot;
    private TextView credit;
    private TextView bay;
    private Button buy_water;
    private Button buy_furs;
    private Button buy_food;
    private Button buy_ore;
    private Button buy_games;
    private Button buy_firearms;
    private Button buy_medicine;
    private Button buy_machine;
    private Button buy_narcortics;
    private Button buy_robot;
    private String inputStr = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_goods);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        player = (Player) getIntent().getSerializableExtra("player");

        Button menuButton = findViewById(R.id.buy_menu_button);
        menuButton.setText("Menu");
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });

        credit = findViewById(R.id.buy_credit);
        credit.setText(String.valueOf(player.getCredit()) + " Cr");

        bay = findViewById(R.id.buy_bays);
        bay.setText(String.valueOf(player.getCargo()) + "/" + player.getSpaceship().getBay());

        setupPrice();


        Button waterMax = findViewById(R.id.buy_max_water);
        clickMaxButton(waterMax, water, Goods.WATER);

        Button furMax = findViewById(R.id.buy_max_furs);
        clickMaxButton(furMax, fur, Goods.FURS);

        Button foodMax = findViewById(R.id.buy_max_food);
        clickMaxButton(foodMax, food, Goods.FOOD);

        Button oreMax = findViewById(R.id.buy_max_ore);
        clickMaxButton(oreMax, ore, Goods.ORE);

        Button firearmMax = findViewById(R.id.buy_max_firearms);
        clickMaxButton(firearmMax, firearm, Goods.FIREARMS);

        Button gameMax = findViewById(R.id.buy_max_games);
        clickMaxButton(gameMax, game, Goods.GAMES);

        Button medicineMax = findViewById(R.id.buy_max_medicine);
        clickMaxButton(medicineMax, medicine, Goods.MEDICINE);

        Button machineMax = findViewById(R.id.buy_max_machine);
        clickMaxButton(machineMax, machine, Goods.MACHINES);

        Button narcorticsMax = findViewById(R.id.buy_max_narcortics);
        clickMaxButton(narcorticsMax, narcortics, Goods.NARCOTICS);

        Button robotMax = findViewById(R.id.buy_max_robots);
        clickMaxButton(robotMax, robot, Goods.ROBOTS);

        buy_water = findViewById(R.id.buy_num_water);
        clickNumButton(buy_water, water, Goods.WATER);



        buy_furs = findViewById(R.id.buy_num_furs);
        clickNumButton(buy_furs, fur, Goods.FURS);

        buy_food = findViewById(R.id.buy_num_food);
        clickNumButton(buy_food, food, Goods.FOOD);

        buy_ore = findViewById(R.id.buy_num_ore);
        clickNumButton(buy_ore, ore, Goods.ORE);

        buy_games = findViewById(R.id.buy_num_game);
        clickNumButton(buy_games, game, Goods.GAMES);

        buy_firearms = findViewById(R.id.buy_num_firearms);
        clickNumButton(buy_firearms, firearm, Goods.FIREARMS);

        buy_medicine = findViewById(R.id.buy_num_medicine);
        clickNumButton(buy_medicine, medicine, Goods.MEDICINE);

        buy_machine = findViewById(R.id.buy_num_machine);
        clickNumButton(buy_machine, machine, Goods.MACHINES);

        buy_narcortics = findViewById(R.id.buy_num_narcotics);
        clickNumButton(buy_narcortics, narcortics, Goods.NARCOTICS);

        buy_robot = findViewById(R.id.buy_num_robot);
        clickNumButton(buy_robot, robot, Goods.ROBOTS);

        setupStock();
        //this is test for stock that the planet has
        for (Goods g : Goods.values()) {
            Log.d("stock", g.toString() + " " + String
                    .valueOf(player.getCurrentplanet().getStock().get(g.toString().toLowerCase()))
                    + " in " + player.getCurrentplanet().getName());
        }

    }
    private void clickMaxButton(Button button, final int price, final Goods goods) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsAble(goods)) {
                    int max = Math.min(player.getSpaceship().getBay()
                            - player.getCargo(), player.getCredit() / price);
                    if (max > 0) {
                        player.getInven().put(goods.toString().toLowerCase(),
                                player.getInven().get(goods.toString().toLowerCase())+max);
                        player.setCargo(player.getCargo() + max);
                        player.setCredit(player.getCredit() - (max * price));
                        bay.setText(String.valueOf(player.getCargo()) + "/"
                                + player.getSpaceship().getBay());
                        credit.setText(String.valueOf(player.getCredit()) + " Cr");
                        Toast.makeText(getApplicationContext(), "You bought "
                                + max + " " + goods.toString(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "You can not buy anymore. Check your bay or credit.",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                        openAlert();
                }
            }
        });
    }
    private void clickNumButton(final Button button, final int price, final Goods goods){
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(IsAble(goods)){
                    openBuy(goods.toString(), price);
                } else {
                    openAlert();
                }
            }
        });
    }

    private void setupPrice() {
        String price;

        TextView waterPrice = findViewById(R.id.buy_price_water);
        if (IsAble(Goods.WATER)){
            water = c.calculatePrice(Goods.WATER, player.getCurrentplanet());
            price = water + " cr";
        }
        else {
            price = "N/A";
        }
        waterPrice.setText(price);

        TextView furPrice = findViewById(R.id.buy_price_furs);
        if (IsAble(Goods.FURS)){
            fur = c.calculatePrice(Goods.FURS, player.getCurrentplanet());
            price = fur + " cr";
        }  else {
            price = "N/A";
        }
        furPrice.setText(price);

        TextView foodPrice = findViewById(R.id.buy_price_food);
        if (IsAble(Goods.FOOD)) {
            food = c.calculatePrice(Goods.FOOD, player.getCurrentplanet());
            price = food +" cr";
        } else {
            price = "N/A";
        }
        foodPrice.setText(price);

        TextView orePrice = findViewById(R.id.buy_price_ore);
        if (IsAble(Goods.ORE)) {
            ore = c.calculatePrice(Goods.ORE, player.getCurrentplanet());
            price = ore + " cr";
        } else {
            price = "N/A";
        }
        orePrice.setText(price);

        TextView gamePrice = findViewById(R.id.buy_price_games);
        if (IsAble(Goods.GAMES)) {
            game = c.calculatePrice(Goods.GAMES, player.getCurrentplanet());
            price = game +" cr";
        } else {
            price = "N/A";
        }
        gamePrice.setText(price);

        TextView firearmPrice = findViewById(R.id.buy_price_firearms);
        if (IsAble(Goods.FIREARMS)) {
            firearm = c.calculatePrice(Goods.FIREARMS, player.getCurrentplanet());
            price = firearm+" cr";
        } else {
            price = "N/A";
        }
        firearmPrice.setText(price);

        TextView medicinePrice = findViewById(R.id.buy_price_medicine);
        if (IsAble(Goods.MEDICINE)){
            medicine = c.calculatePrice(Goods.MEDICINE, player.getCurrentplanet());
            price = medicine + " cr";
        } else {
            price = "N/A";
        }
        medicinePrice.setText(price);

        TextView machinePrice = findViewById(R.id.buy_price_machine);
        if (IsAble(Goods.MACHINES)) {
            machine = c.calculatePrice(Goods.MACHINES, player.getCurrentplanet());
            price = machine +" cr";
        } else {
            price = "N/A";
        }
        machinePrice.setText(price);

        TextView narcorticsPrice = findViewById(R.id.buy_price_narcortics);
        if (IsAble(Goods.NARCOTICS)){
            narcortics = c.calculatePrice(Goods.NARCOTICS, player.getCurrentplanet());
            price = narcortics+ " cr";
        } else {
            price = "N/A";
        }
        narcorticsPrice.setText(price);

        TextView robotPrice = findViewById(R.id.buy_price_robots);
        if (IsAble(Goods.ROBOTS)){
            robot = c.calculatePrice(Goods.ROBOTS, player.getCurrentplanet());
            price = robot +" cr";
        } else {
            price = "N/A";
        }
        robotPrice.setText(price);

    }

    private void setupStock(){
        String n;


        if(IsAble(Goods.WATER)){
            n = String.valueOf(player.getCurrentplanet().getStock().get(Goods.WATER.toString().toLowerCase()));
//            Log.d("stock", n);
        } else {
            n="N/A";

        }

        buy_water.setText(n);

        if(IsAble(Goods.FURS)){
            n = String.valueOf(player.getCurrentplanet().getStock().get(Goods.FURS.toString().toLowerCase()));
        } else {
            n="N/A";
        }
        buy_furs.setText(n);

        if(IsAble(Goods.FOOD)){
            n = String.valueOf(player.getCurrentplanet().getStock().get(Goods.FOOD.toString().toLowerCase()));
        } else {
            n="N/A";
        }
        buy_food.setText(n);

        if(IsAble(Goods.ORE)){
            n = String.valueOf(player.getCurrentplanet().getStock().get(Goods.ORE.toString().toLowerCase()));
        } else {
            n="N/A";
        }
        buy_ore.setText(n);

        if(IsAble(Goods.GAMES)){
            n = String.valueOf(player.getCurrentplanet().getStock().get(Goods.GAMES.toString().toLowerCase()));
        } else {
            n="N/A";
        }
        buy_games.setText(n);

        if(IsAble(Goods.FIREARMS)){
            n = String.valueOf(player.getCurrentplanet().getStock().get(Goods.FIREARMS.toString().toLowerCase()));
        } else {
            n="N/A";
        }
        buy_firearms.setText(n);

        if(IsAble(Goods.MEDICINE)){
            n = String.valueOf(player.getCurrentplanet().getStock().get(Goods.MEDICINE.toString().toLowerCase()));
        } else {
            n="N/A";
        }
        buy_medicine.setText(n);

        if(IsAble(Goods.MACHINES)){
            n = String.valueOf(player.getCurrentplanet().getStock().get(Goods.MACHINES.toString().toLowerCase()));
        } else {
            n="N/A";
        }
        buy_machine.setText(n);

        if(IsAble(Goods.NARCOTICS)){
            n = String.valueOf(player.getCurrentplanet().getStock().get(Goods.NARCOTICS.toString().toLowerCase()));
        } else {
            n="N/A";
        }
        buy_narcortics.setText(n);

        if(IsAble(Goods.ROBOTS)){
            n = String.valueOf(player.getCurrentplanet().getStock().get(Goods.ROBOTS.toString().toLowerCase()));
        } else {
            n="N/A";
        }
        buy_robot.setText(n);


    }


    private boolean IsAble(Goods goods) {
        return (player.getCurrentplanet().getTechLevel() - goods.getMTLP()) >= 0;
    }
    private void openAlert(){
        AlertWindow alert = new AlertWindow();
        alert.show(getSupportFragmentManager(), "alert");
    }
    private void openBuy(String goods, int price) {
        DialogFragment frag = new BuyDialog();

        Bundle bundle = new Bundle();
        bundle.putString("goodstype", goods);
        bundle.putInt("price", price);
        frag.setArguments(bundle);


        frag.show(getSupportFragmentManager(), "dialog");

    }

    @Override
    public void onInputData(String input) {
        Toast.makeText(this, input+" items is purchased", Toast.LENGTH_LONG).show();
        inputStr =(input);
    }

    @Override
    public void buyItem(String goods, int price) {
        int max = Math.min(Math.min(player.getSpaceship().getBay()
                - player.getCargo(), player.getCredit() / price),
                player.getCurrentplanet().getStock().get(goods.toLowerCase()));

        if (max > 0) {
            if(((player.getCredit() - (Integer.parseInt(inputStr) * price)) >= 0)
                    && ((player.getCargo() + Integer.parseInt(inputStr))
                    <= player.getSpaceship().getBay())) {
                player.getInven().put(goods.toLowerCase(),
                        player.getInven().get(goods.toLowerCase())+Integer.parseInt(inputStr));
                player.setCargo(player.getCargo() + Integer.parseInt(inputStr));
                player.setCredit(player.getCredit() - (Integer.parseInt(inputStr) * price));
                bay.setText(String.valueOf(player.getCargo()) + "/"
                        + player.getSpaceship().getBay());
                credit.setText(String.valueOf(player.getCredit()) + " Cr");
                player.getCurrentplanet().getStock().put(goods.toLowerCase(),
                        player.getCurrentplanet().getStock().get(goods.toLowerCase())
                                -Integer.parseInt(inputStr));
                setupStock();
                Toast.makeText(getApplicationContext(), "You bought " + inputStr
                        + " " + goods, Toast.LENGTH_LONG).show();
            } else if((player.getCredit() - (Integer.parseInt(inputStr) * price)) <= 0) {
                Toast.makeText(getApplicationContext(), "You do not have enough credit."
                        , Toast.LENGTH_LONG).show();
            } else if((player.getSpaceship().getBay() - player.getCargo()) <= 0){
                Toast.makeText(getApplicationContext(),
                        "You do not have enough room in your cargo.",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "you are trying to buy more than planet has",
                        Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(),
                    "You can not buy anymore. Check your bay or credit.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void openMenu() {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("player", player);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Discard Or Not");
        builder.setMessage("Do you want to discard this? ");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

}
