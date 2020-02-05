package pokedex.mike.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PokemonScroller pokemonScroller;
    ArrayList<PokemonWrapper> pokemonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pokemonList = loadPokemon();
        createPokemonScroller(pokemonList);


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
                Log.d("FINDME: LOADING :" , dirs[i]);
                String baseDir = "list/" + dirs[i];
                String[] files = assetManager.list(baseDir);
                for(int j = 0; j < files.length; j++) {
                    Log.d("FINDME: EXECUTING: " , files[j] + " == info.txt ? " + (files[j].equals("info.txt")));
                    if(files[j].equals("info.txt")) {
                        String filePath = baseDir + "/info.txt";
                        Log.d("FINDME: ", filePath);
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(assetManager.open( filePath)));
                        String mLine;

                        String name = "";
                        int number = 0;
                        int type1 = 0;
                        int type2 = 0;
                        while ((mLine = reader.readLine()) != null) {
                            String arr[] = mLine.split(":");
                            if(arr.length !=  2) {continue;}
                            if(arr[0].equals("name")) {name = arr[1];}
                            if(arr[0].equals("number")) {number = Integer.parseInt(arr[1]);}
                            if(arr[0].equals("type_primary")) {type1 = PokemonType.parseInt(arr[1]);}
                            if(arr[0].equals("type_secondary")) {type2 = PokemonType.parseInt(arr[1]);}
                        }
                        PokemonWrapper pokemon = new PokemonWrapper(filePath, number, name, type1, type2);
                        pokemonList.add((pokemon));
                    }
                }

            }
        }
        catch (IOException e) {
            Log.d("FINDME: ", e.getMessage());
        }
        return pokemonList;
    }

}
