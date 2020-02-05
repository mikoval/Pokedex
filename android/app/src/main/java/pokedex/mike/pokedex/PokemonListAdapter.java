package pokedex.mike.pokedex;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.MyViewHolder> {
    private ArrayList<PokemonWrapper> mDataset;
    private Context context;
    private RecyclerView.LayoutManager mManager;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public PokemonListWidget view;
        public MyViewHolder(PokemonListWidget v) {
            super(v);
            view = v;

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PokemonListAdapter(Context context, ArrayList<PokemonWrapper> myDataset, RecyclerView.LayoutManager manager) {
        this.context = context;
        mDataset = myDataset;
        mManager = manager;

        //int half = Integer.MAX_VALUE / 2;
        //manager.scrollToPosition(half - half % myDataset.size());


    }

    // Create new views (invoked by the layout manager)
    @Override
    public PokemonListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        PokemonListWidget v = new PokemonListWidget(context);
        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.view.setNumber(mDataset.get(position).number);
        holder.view.setName(mDataset.get(position).name);
        holder.view.setType1(mDataset.get(position).primaryType);
        holder.view.setType2(mDataset.get(position).secondaryType);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}