package pokedex.mike.pokedex;

import android.content.Context;

import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PokemonScroller extends RecyclerView {

    Context context;

    public PokemonScroller(Context context) {
        super(context);
        this.context = context;
    }


    @Override
    public boolean fling(int velocityX, int velocityY) {

        velocityX *= 0.5;
        return super.fling(velocityX, velocityY);
    }
    

}