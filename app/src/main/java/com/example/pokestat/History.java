package com.example.pokestat;

import static com.example.pokestat.MainActivity.POKESTAT_TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class History extends InfoPokemon {
    Set<String> searchedPokemonName;

    public History(Set<String> searchedPokemonName) {
        this.searchedPokemonName=searchedPokemonName;
        Log.d(MainActivity.POKESTAT_TAG, "searchedPokemonName constructeur  "+this.searchedPokemonName);
    }
    /*public void write_historic_in_file() {
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File fileout = new File(folder, "pokestat_historic.txt");
        try (FileOutputStream fos = new FileOutputStream(fileout)) {
            PrintStream ps = new PrintStream(fos);
            ps.println("Start of my historic");

            // Write each Pokémon name to the file
            for (String pokemonName : searchedPokemonName) {
                ps.println(pokemonName);
            }

            ps.println("End of my historic");
            Log.d(POKESTAT_TAG, "Historic written to file successfully");

            ps.close();
        } catch (FileNotFoundException e) {
            Log.e(POKESTAT_TAG, "File not found", e);
            e.printStackTrace();
        } catch (IOException e) {
            Log.e(POKESTAT_TAG, "Error I/O", e);
            e.printStackTrace();
        }

    }*/

    // Fonction qui recharge un historique
    public void reload_historic(Context context) {
    // Récuperation de l’objet unique qui s’occupe de la sauvegarde
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
    // Récuperation de l’ancienne valeur ou d’une valeur par défaut
        searchedPokemonName = sharedPref.getStringSet("inputpokemonname", new TreeSet<String>());
        Log.d(MainActivity.POKESTAT_TAG, "searchedPokemonName  "+searchedPokemonName);
}

    // Fonction qui affiche l’historique a partir de l’attribut searchedPokemonName
    // Il faut donc avoir charge l’historique avant!
    public void display_historic() {
        Log.d(POKESTAT_TAG,"Historique ("+ (new Date())+ ") size= "+searchedPokemonName.size()+":  ");
        for (String item : searchedPokemonName) {
            Log.d(POKESTAT_TAG,"\t-␣" + item);
        }
    }

}
