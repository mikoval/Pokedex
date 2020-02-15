package pokedex.mike.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PokemonActivity extends ToolbarActivity {

    PokemonWrapper pokemon;
    MovesTable movesTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
        TextView name = findViewById(R.id.name);
        Intent intent = getIntent();
        pokemon = new PokemonWrapper(intent.getStringExtra("pokemon"), this);
        Log.d("FINDME:", "DIRECTORY = " + pokemon.directory);
        name.setText(pokemon.name);

        ImageView image = findViewById(R.id.image);
        image.setImageBitmap(pokemon.image);

        TextView number = findViewById(R.id.number);
        number.setText("#" + pokemon.number);

        TextView type1 = findViewById(R.id.type1);
        type1.setText(PokemonType.getString(pokemon.primaryType));
        type1.setBackgroundColor(PokemonType.getColor(pokemon.primaryType));


        TextView type2 = findViewById(R.id.type2);
        type2.setText(PokemonType.getString(pokemon.secondaryType));
        type2.setBackgroundColor(PokemonType.getColor(pokemon.secondaryType));
        if(pokemon.secondaryType == PokemonType.UNKNOWN){
            type2.setVisibility(View.GONE);
            ((ViewGroup) type2.getParent()).removeView(type2);
        }

        TextView height = findViewById(R.id.height);
        height.setText("Height: " + pokemon.height);

        TextView weight = findViewById(R.id.weight);
        weight.setText("Weight: " + pokemon.weight);

        initializeMovesTable();
    }

    private void initializeMovesTable() {
        movesTable = findViewById(R.id.MovesTable);
        movesTable.setDirectory(pokemon.directory);
    }
}