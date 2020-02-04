package pokedex.mike.pokedex;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PokemonListWidget extends RelativeLayout {
    private String name;
    private int number;
    private Context context;



    private TextView textView;

    public PokemonListWidget(Context context) {
        super(context);
        this.context = context;

        init();

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void init() {
        textView = new TextView(context);
        textView.setText(name);
        LayoutParams textParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        textParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        // textParams.setMargins(20, 20, 20, 20);
        textView.setLayoutParams(textParams);
        textView.setBackgroundColor(Color.LTGRAY);
        // textView.setPadding(10, 10, 10, 10);
        textView.setTypeface(null, Typeface.BOLD);

        this.addView(textView);

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 250);

        params.setMargins(0, 1, 0, 1);

        setLayoutParams(params);

        this.setBackgroundColor(Color.LTGRAY);
    }

    public void setName(String name) {
        this.name = name;
        textView.setText(name);
    }
    public void setNumber(int number) {
        this.number = number;
        //textView.setText(name);
    }


    public String getName() {return name;}
    public int getNumber() {return number;}

}