package com.example.pokestat;

import android.content.Intent;
import android.net.Uri;

import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class InfoPokemon extends AppCompatActivity {

    Set<String> searchedPokemonName;
    History history;

    private static final String SEARCHED_POKEMON_KEY = "inputpokemonname";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pokemon);

        if (savedInstanceState != null) {
            // Restaurer l'historique depuis l'état sauvegardé
            searchedPokemonName = new TreeSet<>(savedInstanceState.getStringArrayList(SEARCHED_POKEMON_KEY));
        } else {
            searchedPokemonName = new TreeSet<>();
        }

        Bundle extras = getIntent().getExtras();
        String pokemonName = null;
        if (extras != null) {
            pokemonName = extras.getString("inputpokemonname");
            Log.d(MainActivity.POKESTAT_TAG, "inputpokemonname "+pokemonName);
        }

        if (pokemonName != null) {
            // Ajouter la saisie à l'historique
            searchedPokemonName.add(pokemonName.toLowerCase());
        }
        history = new History(searchedPokemonName);

        history.display_historic();

        history.reload_historic(this);

        TextView textView3 = findViewById(R.id.textView3);
        TextView textView5 = findViewById(R.id.textView5);
        TextView textView8 = findViewById(R.id.textView8);
        TextView textView9 = findViewById(R.id.textView9);

        PokemonR.PokeRequest task = new PokemonR.PokeRequest(this,pokemonName,textView3, textView5, textView8, textView9);
        task.execute();
        Button button_S_W = findViewById(R.id.button3);

        assert pokemonName != null;
        String url = "https://www.pokepedia.fr/" + pokemonName.toLowerCase();

        button_S_W.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        Button back = findViewById(R.id.button4);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an intent to navigate back to MainActivity
                Intent intent = new Intent(InfoPokemon.this, MainActivity.class);
                intent.putStringArrayListExtra(SEARCHED_POKEMON_KEY, new ArrayList<>(searchedPokemonName));
                // Start the MainActivity
                InfoPokemon.this.finish();
            }
        });

        //History.display_historic();




    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Sauvegarder l'historique dans l'état
        outState.putStringArrayList(SEARCHED_POKEMON_KEY, new ArrayList<>(searchedPokemonName));
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restaurer l'historique depuis l'état sauvegardé
        searchedPokemonName = new TreeSet<>(savedInstanceState.getStringArrayList(SEARCHED_POKEMON_KEY));
    }


}