package pokedex.mike.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PokemonScroller pokemonScroller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createPokemonList();
    }

    private void createPokemonList() {
        pokemonScroller = (PokemonScroller) findViewById(R.id.my_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        pokemonScroller.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        ArrayList<PokemonWrapper> arr = new ArrayList();

        for(int i = 0; i < 20; i++){
            arr.add(new PokemonWrapper(i, "FOO"));
        }


        PokemonListAdapter  mAdapter = new PokemonListAdapter(this, arr, layoutManager);
        pokemonScroller.setAdapter(mAdapter);
    }

}
