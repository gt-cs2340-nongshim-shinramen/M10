package com.example.m6.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.m6.R;
import com.example.m6.model.Goods;
import com.example.m6.model.Player;
import com.example.m6.model.Universe;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for configuring information for a new player.
 */
@SuppressWarnings("ALL")
public class ConfigurationActivity extends AppCompatActivity {

    private EditText edit_name;
    private EditText edit_pilot_point;
    private EditText edit_fighter_point;
    private EditText edit_trader_point;
    private EditText edit_engineer_point;
    private Spinner difficultySpinner;

    private Button save;
    private Player player;
    private TextView remaining;
    private final int totalPoint = 16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_configuration);

        edit_name = findViewById(R.id.name_text);
        edit_pilot_point = findViewById(R.id.pilot_point_text);
        edit_fighter_point = findViewById(R.id.fighter_point_text);
        edit_trader_point = findViewById(R.id.trader_point_text);
        edit_engineer_point = findViewById(R.id.engineer_point_text);
        difficultySpinner = findViewById(R.id.difficulty_spinner);
        save = findViewById(R.id.save_button);


        remaining = findViewById(R.id.remaining);
        remainingChange(edit_pilot_point);
        remainingChange(edit_fighter_point);
        remainingChange(edit_trader_point);
        remainingChange(edit_engineer_point);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,
                R.layout.brand_dropdown, Player.validDifficulty);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.brand_dropdown);
        difficultySpinner.setAdapter(spinnerArrayAdapter);

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String name = edit_name.getText().toString();
                String sPilot_point = edit_pilot_point.getText().toString();
                String sFighter_point = edit_fighter_point.getText().toString();
                String sTrader_point = edit_trader_point.getText().toString();
                String sEngineer_point = edit_engineer_point.getText().toString();


                if(name.trim().isEmpty()||sPilot_point.trim().isEmpty()
                        ||sFighter_point.trim().isEmpty()||sTrader_point.trim().isEmpty()
                        ||sEngineer_point.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "input field can not be blank",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                int nPilot_point = Integer.parseInt(sPilot_point);
                int nFighter_point = Integer.parseInt(sFighter_point);
                int nTrader_point = Integer.parseInt(sTrader_point);
                int nEngineer_point = Integer.parseInt(sEngineer_point);
                int sum = nFighter_point + nPilot_point + nTrader_point + nEngineer_point;
                String difficulty = difficultySpinner.getSelectedItem().toString();

                if (sum != 16) {
                    Toast.makeText(getApplicationContext(),
                            "the sum of skill point should be 16", Toast.LENGTH_LONG).show();
                } else {
                    player = new Player(name, nPilot_point, nFighter_point, nTrader_point,
                            nEngineer_point, difficulty, new Universe(), createMap());
                    String showStat = "player "+player.getName()+" is successfully created";
                    Toast.makeText(getApplicationContext(), showStat, Toast.LENGTH_LONG).show();
                    openUniverse();
                    finish();
                }


            }
        });
    }

    /**
     * This method makes sure the user doesn't assign more than 16 skill points.
     * @param editText The text of the skill points allotted.
     */
    public void remainingChange(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!editText.getText().toString().equals("")){
                    int pilot = (!edit_pilot_point.getText().toString().equals(""))?
                            Integer.parseInt(edit_pilot_point.getText().toString()):0;
                    int fighter = (!edit_fighter_point.getText().toString().equals(""))?
                            Integer.parseInt(edit_fighter_point.getText().toString()):0;
                    int trader = (!edit_trader_point.getText().toString().equals(""))?
                            Integer.parseInt(edit_trader_point.getText().toString()):0;
                    int engineer = (!edit_engineer_point.getText().toString().equals(""))?
                            Integer.parseInt(edit_engineer_point.getText().toString()):0;

                    int currPoint= pilot+fighter+trader+engineer;

                    remaining.setText(String.valueOf(totalPoint-currPoint));
                }
                if(Integer.parseInt(remaining.getText().toString())<0){
                    Toast.makeText(getApplicationContext(), "you can not assign more than "
                            + "16 points", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * This method opens the next page which is the Universe.
     */
    public void openUniverse() {
        Intent intent = new Intent(this, player_information.class);
        intent.putExtra("player", player);
        startActivity(intent);
        finish();
    }

    /**
     * This method creates a hash map of the goods/resources.
     * @return hash map of the goods/resources
     */
    public Map<String, Integer> createMap() {
        Map<String, Integer> myMap = new HashMap<>();
        for(Goods g: Goods.values()){
            myMap.put(g.toString().toLowerCase(), 0);
        }
        return myMap;
    }

}


