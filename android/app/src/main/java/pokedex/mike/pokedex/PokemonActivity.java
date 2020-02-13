package pokedex.mike.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PokemonActivity extends ToolbarActivity {

    PokemonWrapper pokemon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
        TextView name = findViewById(R.id.name);
        Intent intent = getIntent();
        pokemon = new PokemonWrapper(intent.getStringExtra("pokemon"), this);
        Log.d("FINDME:", "DIRECTORY = " + pokemon.directory);
        name.setText(pokemon.name);
    }
}
