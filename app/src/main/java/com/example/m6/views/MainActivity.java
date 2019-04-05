package com.example.m6.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.m6.R;

/**
 * Class for the main activity
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button conf_button = findViewById(R.id.conf_button);
        conf_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeConfig();
            }
        });
    }

    /**
     * This method creates the configuration
     */
    public void makeConfig() {
        Intent intent = new Intent(this, ConfigurationActivity.class);
        startActivity(intent);
        finish();
    }
}
