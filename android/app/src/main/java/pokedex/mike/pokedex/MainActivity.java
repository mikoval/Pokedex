package pokedex.mike.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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


       //
        // testTensorflow();

    }

    public void testTensorflow() {
        new Thread(new Runnable() {
            public void run() {
                int wrong = 0;
                int total = 0;
                for(int i = 0; i < pokemonList.size(); i++){
                    final PokemonWrapper p = pokemonList.get(i);


                    Bitmap input = p.image.copy(p.image.getConfig(), true);
                    input.eraseColor(Color.WHITE);
                    Canvas canvas = new Canvas(input);
                    canvas.drawBitmap(p.image, 0f, 0f, null);
                    final List<Classifier.Recognition> results =
                            classifier.recognizeImage(input, 0);
                    final Classifier.Recognition first = results.get(0);
                    Classifier.Recognition second = results.get(1);
                    Classifier.Recognition third = results.get(2);

                    String pokemon = p.directory.split("/")[1].trim();
                    total++;
                    if(!pokemon.trim().equals(first.getTitle().trim())) {
                        wrong++;
                        Log.d("FINDME: " , String.format("%s = %s : %f, %s : %f, %s : %f",
                                p.directory,
                                first.getTitle(),first.getConfidence(),
                                second.getTitle(), second.getConfidence(),
                                third.getTitle(), third.getConfidence()));
                    }




                    // Get a handler that can be used to post to the main thread
                    Handler mainHandler = new Handler(Looper.getMainLooper());

                    Runnable myRunnable = new Runnable() {
                        @Override
                        public void run() {
                            ImageView v = (ImageView) findViewById(R.id.image_view);
                            v.setImageBitmap(classifier.inputImageBuffer.getBitmap());
                            TextView t = (TextView) findViewById(R.id.text_view);
                            t.setText(p.name + " , " + first.getTitle() + ", " + first.getConfidence());
                        } // This is your code
                    };
                    mainHandler.post(myRunnable);

                }
                Log.d("FINDME", "GOT WRONG : " + wrong + "/" + total);
            }
        }).start();

    }

    public static Bitmap createTrimmedBitmap(Bitmap bmp) {
        int imgHeight = bmp.getHeight();
        int imgWidth  = bmp.getWidth();
        int smallX=0,largeX=imgWidth,smallY=0,largeY=imgHeight;
        int left=imgWidth,right=imgWidth,top=imgHeight,bottom=imgHeight;
        for(int i=0;i<imgWidth;i++)
        {
            for(int j=0;j<imgHeight;j++)
            {
                if(bmp.getPixel(i, j) != Color.TRANSPARENT){
                    if((i-smallX)<left){
                        left=(i-smallX);
                    }
                    if((largeX-i)<right)
                    {
                        right=(largeX-i);
                    }
                    if((j-smallY)<top)
                    {
                        top=(j-smallY);
                    }
                    if((largeY-j)<bottom)
                    {
                        bottom=(largeY-j);
                    }
                }
            }
        }
        bmp=Bitmap.createBitmap(bmp,left,top,imgWidth-left-right, imgHeight-top-bottom);

        return bmp;

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
