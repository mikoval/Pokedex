package pokedex.mike.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ToolbarActivity {

    private PokemonScroller pokemonScroller;
    ArrayList<PokemonWrapper> pokemonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pokemonList = loadPokemon();
        createPokemonScroller(pokemonList);

        ImageView v = (ImageView) findViewById(R.id.image_view);
        v.setImageBitmap(image);

        for(int i = 0; i < pokemonList.size(); i++){
            PokemonWrapper p = pokemonList.get(i);
            final List<Classifier.Recognition> results =
                    classifier.recognizeImage(p.image, 0);
            Classifier.Recognition first = results.get(0);
            Classifier.Recognition second = results.get(1);
            Classifier.Recognition third = results.get(2);

            Log.d("FINDME: " , String.format("%s = %s : %f, %s : %f, %s : %f",
                    p.name,
                    first.getTitle(),first.getConfidence(),
                    second.getTitle(), second.getConfidence(),
                    third.getTitle(), third.getConfidence()));
        }

    }

    private void createPokemonScroller(ArrayList<PokemonWrapper> arr) {
        pokemonScroller = (PokemonScroller) findViewById(R.id.my_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        pokemonScroller.setLayoutManager(layoutManager);

        PokemonListAdapter  mAdapter = new PokemonListAdapter(this, arr, layoutManager);
        pokemonScroller.setAdapter(mAdapter);
    }

    private ArrayList<PokemonWrapper> loadPokemon() {
        ArrayList<PokemonWrapper> pokemonList = new ArrayList<>();

        AssetManager assetManager = getAssets();
        try {
            String[] dirs = assetManager.list("list");
            for(int i = 0; i < dirs.length; i++){
                //Log.d("FINDME: LOADING :" , dirs[i]);
                String baseDir = "list/" + dirs[i];
                PokemonWrapper pokemon = new PokemonWrapper(baseDir, this);
                pokemonList.add((pokemon));


            }
        }
        catch (IOException e) {
            Log.d("FINDME: ", e.getMessage());
        }
        return pokemonList;
    }

}
