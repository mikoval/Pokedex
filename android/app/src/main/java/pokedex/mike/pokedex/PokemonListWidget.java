package pokedex.mike.pokedex;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.transition.Visibility;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class PokemonListWidget extends RelativeLayout {
    private PokemonWrapper pokemon;

    private final Context context;



    private TextView nameView;
    private TextView numberView;
    private TextView type1View;
    private TextView type2View;

    private ImageView imageView;

    LayoutInflater mInflater;

    public PokemonListWidget(final Context context) {
        super(context);
        this.context = context;
        init();

    }
    public PokemonListWidget(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }
    public PokemonListWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        mInflater = LayoutInflater.from(context);
        View contentView = mInflater.inflate(R.layout.widget, this, true);
        nameView = contentView.findViewById(R.id.name);
        numberView = contentView.findViewById(R.id.number);
        type1View = contentView.findViewById(R.id.type1);
        type2View = contentView.findViewById(R.id.type2);
        imageView = contentView.findViewById(R.id.image);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PokemonActivity.class);
                intent.putExtra("pokemon", pokemon.directory);
                context.startActivity(intent);

            }
        });
    }

    private void setName(String name) {
        nameView.setText(name);
    }
    private void setNumber(int number) {
        numberView.setText("" + number);
    }
    private void setType1(int type) {
        type1View.setText(PokemonType.getString(type));
        RelativeLayout wrapper = ((RelativeLayout) type1View.getParent());
        GradientDrawable background = (GradientDrawable)wrapper.getBackground();
        background.setColor(PokemonType.getColor(type));
    }
    private void setType2(int type) {
        if (type == PokemonType.UNKNOWN) {
            type2View.setVisibility(View.GONE);
        } else {
            type2View.setVisibility(View.VISIBLE);
        }
        type2View.setText(PokemonType.getString(type));
        RelativeLayout wrapper = ((RelativeLayout) type2View.getParent());
        GradientDrawable background = (GradientDrawable)wrapper.getBackground();
        background.setColor(PokemonType.getColor(type));
    }

    private void setImage(Bitmap image) {
        imageView.setImageBitmap(image);

    }
    public void setPokemon(PokemonWrapper pokemon) {
        this.pokemon = pokemon;
        setName(pokemon.name);
        setNumber(pokemon.number);
        setType1(pokemon.primaryType);
        setType2(pokemon.secondaryType);
        setImage(pokemon.image);

    }


}