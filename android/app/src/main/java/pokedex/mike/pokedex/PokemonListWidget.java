package pokedex.mike.pokedex;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class PokemonListWidget extends RelativeLayout {
    private String name;
    private int number;
    private int type1;
    private int type2;

    private Context context;



    private TextView nameView;
    private TextView numberView;
    private TextView type1View;
    private TextView type2View;

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

        /* NAME AND NUMBER*/
        {
            RelativeLayout typePositionWrapper = new RelativeLayout(context);
            LayoutParams positionParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            positionParams.addRule(RelativeLayout.CENTER_VERTICAL);
            typePositionWrapper.setLayoutParams(positionParams);
            this.addView(typePositionWrapper);


            LinearLayout typeWrapper = new LinearLayout(context);
            LinearLayout.LayoutParams typeParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            typeWrapper.setOrientation(LinearLayout.HORIZONTAL);
            typeWrapper.setLayoutParams(typeParams);
            typePositionWrapper.addView(typeWrapper);


            /* NUMBER */
            {
                TextView textView = new TextView(context);
                textView.setText(name);
                LayoutParams textParams = new LayoutParams(70, 70);

                textParams.setMargins(20, 20, 20, 20);
                textView.setLayoutParams(textParams);
                textView.setTypeface(null, Typeface.BOLD);
                textView.setTextSize(15);
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.WHITE);
                textView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_background));
                textView.setGravity(Gravity.CENTER);

                numberView = textView;
                typeWrapper.addView(textView);
            }

            /* NAME */
            {
                TextView textView = new TextView(context);
                textView.setText(name);
                LayoutParams textParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
                textView.setLayoutParams(textParams);
                textView.setBackgroundColor(Color.LTGRAY);
                textView.setTypeface(null, Typeface.BOLD);
                textView.setTextSize(20);
                textView.setGravity(Gravity.CENTER );

                nameView = textView;
                typeWrapper.addView(textView);
            }
        }



        /* Types */
        {
            RelativeLayout typePositionWrapper = new RelativeLayout(context);
            LayoutParams positionParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            positionParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            positionParams.addRule(RelativeLayout.CENTER_VERTICAL);
            typePositionWrapper.setLayoutParams(positionParams);


            LinearLayout typeWrapper = new LinearLayout(context);
            LinearLayout.LayoutParams typeParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            typeParams.gravity = (Gravity.CENTER_VERTICAL | Gravity.RIGHT);
            typeWrapper.setOrientation(LinearLayout.VERTICAL);
            typeParams.setMargins(20, 20, 20, 20);
            typeWrapper.setLayoutParams(typeParams);
            typePositionWrapper.addView(typeWrapper);

            {
                TextView textView = new TextView(context);
                textView.setText(name);
                LayoutParams textParams = new LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT);
                textParams.setMargins(20, 20, 20, 20);


                textView.setLayoutParams(textParams);
                textView.setTypeface(null, Typeface.BOLD);
                textView.setTextSize(15);
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.GREEN);
                textView.setText("FIRE");
                textView.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_background));

                type1View = textView;
                typeWrapper.addView(textView);
            }

            {
                TextView textView = new TextView(context);
                textView.setText(name);
                LayoutParams textParams = new LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT);
                textParams.setMargins(20, 20, 20, 20);


                textView.setLayoutParams(textParams);
                textView.setTypeface(null, Typeface.BOLD);
                textView.setTextSize(15);
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.GREEN);
                textView.setText("ELECTRIC");
                textView.setBackground(ContextCompat.getDrawable(context, R.drawable.rectangle_background));

                type2View = textView;
                typeWrapper.addView(textView);
            }


            this.addView(typePositionWrapper);
        }



        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 250);

        params.setMargins(0, 1, 0, 1);

        setLayoutParams(params);

        this.setBackgroundColor(Color.LTGRAY);
    }

    public void setName(String name) {
        this.name = name;
        nameView.setText(name);
    }
    public void setNumber(int number) {
        this.number = number;
        numberView.setText("" + number);
    }
    public void setType1(int type) {
        this.type1 = type;
        type1View.setText(PokemonType.getString(type1));
    }
    public void setType2(int type) {
        this.type2 = type;
        if (type == PokemonType.UNKNOWN) {
            type2View.setVisibility(View.GONE);
        }
        type2View.setText(PokemonType.getString(type2));
    }


    public String getName() {return name;}
    public int getNumber() {return number;}

}