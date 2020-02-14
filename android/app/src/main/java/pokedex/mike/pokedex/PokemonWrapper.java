package pokedex.mike.pokedex;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PokemonWrapper {
    String directory;
    Context context;

    int number;
    String name;
    int primaryType;
    int secondaryType;
    Bitmap image;
    String height;
    String weight;
    public PokemonWrapper(String directory, Context context) {
        this.directory = directory;
        this.context = context;
        init();
    }

    private void init() {
        AssetManager assetManager = context.getAssets();
        try {
            String[] files = assetManager.list(directory);
            for(int j = 0; j < files.length; j++) {
                //Log.d("FINDME: EXECUTING: ", files[j] + " == info.txt ? " + (files[j].equals("info.txt")));
                if (files[j].equals("info.txt")) {
                    String filePath = directory + "/info.txt";
                    //Log.d("FINDME: ", filePath);
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(assetManager.open(filePath)));
                    String mLine;

                    while ((mLine = reader.readLine()) != null) {
                        String arr[] = mLine.split(":");
                        if (arr.length != 2) {
                            continue;
                        }
                        if (arr[0].equals("name")) {
                            name = arr[1];
                        }
                        if (arr[0].equals("number")) {
                            number = Integer.parseInt(arr[1]);
                        }
                        if (arr[0].equals("type_primary")) {
                            primaryType = PokemonType.parseInt(arr[1]);
                        }
                        if (arr[0].equals("type_secondary")) {
                            secondaryType = PokemonType.parseInt(arr[1]);
                        }

                        if (arr[0].equals("Height")) {
                            height = arr[1];
                        }

                        if (arr[0].equals("Weight")) {
                            weight = arr[1];
                        }
                    }
                }

                if (files[j].equals("img.png")) {
                    String filePath = directory + "/img.png";
                    InputStream is = assetManager.open(filePath);
                    image = BitmapFactory.decodeStream(is);
                }
            }
        }
        catch (IOException e) {
            Log.d("FINDME: ", e.getMessage());
        }




    }

}
