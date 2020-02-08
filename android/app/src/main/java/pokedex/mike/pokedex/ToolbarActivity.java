package pokedex.mike.pokedex;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.Interpreter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import pokedex.mike.pokedex.Classifier.Device;
import pokedex.mike.pokedex.Classifier.Model;


public class ToolbarActivity extends Activity {
    private static final Logger LOGGER = new Logger();
    protected static Classifier classifier;
    private static int imageSizeX, imageSizeY;
    final String MODEL_PATH = "model/mymodel.tflite";
    final String LABEL_PATH = "model/mymodel.label";
    protected  static Interpreter tflite;
    protected Bitmap image = null;

    private Model getModel() {
        return Model.FLOAT;
    }
    private Device getDevice() {
        return Device.CPU;
    }
    private int getNumThreads() {
        return -1;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(classifier == null) {
            recreateClassifier(getModel(), getDevice(), getNumThreads());
            if (classifier == null) {
                LOGGER.e("No classifier on preview!");
                return;
            }

        }
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title_bar);

        Button button = (Button) findViewById(R.id.camera);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"CAMERA",Toast.LENGTH_SHORT).show();
                dispatchTakePictureIntent();
            }
        });
    }
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }



    private void recreateClassifier(Model model, Device device, int numThreads) {
        if (classifier != null) {
            LOGGER.d("Closing classifier.");
            classifier.close();
            classifier = null;
        }
        if (device == Device.GPU && model == Model.QUANTIZED) {
            LOGGER.d("Not creating classifier: GPU doesn't support quantized models.");

            return;
        }
        try {
            LOGGER.d(
                    "Creating classifier (model=%s, device=%s, numThreads=%d)", model, device, numThreads);
            classifier = Classifier.create(this, model, device, numThreads);
        } catch (IOException e) {
            LOGGER.e(e, "Failed to create classifier.");
        }

        // Updates the input image size.
        imageSizeX = classifier.getImageSizeX();
        imageSizeY = classifier.getImageSizeY();
        Log.d("FINDME: ", "" + imageSizeX + ", " + imageSizeY);
        AssetManager assetManager = getAssets();
        String filePath = "list/136_Flareon" + "/img.png";
        //Bitmap image = null;
        try {
            InputStream is = assetManager.open(filePath);
            image = BitmapFactory.decodeStream(is);

        } catch (IOException e) {
            Log.d("FINDME: " , e.getMessage());
        }
        final List<Classifier.Recognition> results =
                classifier.recognizeImage(image, 0);
        Classifier.Recognition first = results.get(0);
        Classifier.Recognition second = results.get(1);
        Classifier.Recognition third = results.get(2);

        //Log.d("FINDME: " , String.format("RESULTS = %s : %f, %s : %f, %s : %f",
        //        first.getTitle(),first.getConfidence(),
         //       second.getTitle(), second.getConfidence(),
         //       third.getTitle(), third.getConfidence()));

    }
}
