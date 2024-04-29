package com.example.pokestat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    public static final String POKESTAT_TAG = "PokeStatTag";
    //private static final int DEFAULT_MAVALEUR = 3;
    //int mavaleur;
    /*@Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mavaleur", mavaleur);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* if(savedInstanceState != null && savedInstanceState.containsKey("mavaleur")) {
            mavaleur = savedInstanceState.getInt(("mavaleur"));
        }
        else {
            mavaleur = DEFAULT_MAVALEUR;

        }

        Log.d(POKESTAT_TAG, "mavaleur dans onCreate : " + mavaleur);*/

        Button searchButton = findViewById(R.id.button);
        EditText pokemonNameEditText = findViewById(R.id.editTextText);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = pokemonNameEditText.getText().toString();

                Intent intent = new Intent(MainActivity.this, InfoPokemon.class);
                intent.putExtra("inputpokemonname", name);
                startActivity(intent);
            }
        });

        Button exitButton = findViewById(R.id.button2);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart(); // ALWAYS call first the parent’s method!
        Log.d(MainActivity.POKESTAT_TAG, "OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(MainActivity.POKESTAT_TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(MainActivity.POKESTAT_TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(MainActivity.POKESTAT_TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(MainActivity.POKESTAT_TAG, "onDestroy() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(MainActivity.POKESTAT_TAG, "onRestart() called");
    }

}