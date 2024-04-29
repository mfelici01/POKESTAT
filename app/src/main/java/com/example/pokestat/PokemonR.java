package com.example.pokestat;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class PokemonR extends InfoPokemon{

    public static class PokeRequest extends AsyncTask<Void,Integer,Void> {
        private String name;
        private String restype;
        private String resname;
        private String resweight;
        private String ressize;
        private Context context;
        private TextView textView3;
        private TextView textView5;
        private TextView textView8;
        private TextView textView9;


        public PokeRequest(Context context,String name,TextView textView3, TextView textView5, TextView textView8, TextView textView9) {
            this.context=context;
            this.name = name;
            restype = "<none>";
            resname = "<none>";
            resweight = "<none>";
            ressize = "<none>";
            this.textView3 = textView3;
            this.textView5 = textView5;
            this.textView8 = textView8;
            this.textView9 = textView9;

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect("https://www.pokepedia.fr/" + name).get();
                Element tableinfo = doc.selectFirst("table.tableaustandard");

                Elements names = tableinfo.select("th.entêtesection");
                for (Element e : names) {
                    resname = e.ownText();
                    Log.v(MainActivity.POKESTAT_TAG,"Entete section: " + resname);
                }

                Log.v(MainActivity.POKESTAT_TAG,"=====>>>>>  FINAL Entete section: " + resname);

                Elements rows = tableinfo.select("tr");
                for (Element row : rows) {
                    Log.v(MainActivity.POKESTAT_TAG,"=====>>>>>  new line. ");
                    if(row.select("a[title*=taille]").size() > 0) {
                        Element target = row.selectFirst("td");
                        if(target != null) {
                            ressize = target.ownText();
                            Log.v(MainActivity.POKESTAT_TAG,"=====>>>>>  Find a size: " + ressize);
                        }
                        else
                            Toast.makeText(this.context,R.string.error_no_dom_entity, Toast.LENGTH_LONG).show();
                    }

                    if(row.select("a[title*=poids]").size() > 0) {
                        Element target = row.selectFirst("td");
                        if(target != null) {
                            resweight = target.ownText();
                            Log.v(MainActivity.POKESTAT_TAG,"=====>>>>>  Find a weight: " + resweight);
                        }
                        else
                            Toast.makeText(this.context,R.string.error_no_dom_entity, Toast.LENGTH_LONG).show();
                    }

                }


                Elements elems = tableinfo.select("a[title*=type]");
                ArrayList<String> types = new ArrayList<>();
                for (Element e: elems) {
                    if(!e.attr("title").equalsIgnoreCase("Type")) {
                        String rawtype = e.attr("title");
                        String type = rawtype.replace(" (type)","");
                        types.add(type);
                        Log.v(MainActivity.POKESTAT_TAG,"\tFind type: " +type);
                    }
                }
                restype = types.stream().collect(Collectors.joining(" - "));
            } catch (IOException e) {
                Log.e(MainActivity.POKESTAT_TAG,"Error during connection...",e);
                // e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            // ATTENTION, il faut adapter le code ci-dessous avec vos controles graphiques.
            textView3.setText(resname);
            textView5.setText(restype);
            textView8.setText(ressize);
            textView9.setText(resweight);

            Toast.makeText(this.context, R.string.end_request, Toast.LENGTH_SHORT).show();

            // c'est ici que vous devrez ajouter l'écriture de votre fichier en FIN de sujet!!!
        }
    }
}
