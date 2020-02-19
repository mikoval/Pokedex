package pokedex.mike.pokedex;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

@SuppressLint("AppCompatCustomView")
public class TableButton extends Button {
    private View target;
    private int SELECTED_COLOR = Color.GRAY;
    private int BASE_COLOR = Color.LTGRAY;
    private ArrayList<TableButton> buttons;
    private View view;

    public TableButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TableButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();


    }
    public TableButton(Context context) {
        super(context);
        init();
    }

    private void init() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                for(TableButton button: buttons) {
                    button.deselect();
                }
                select();
            }
        });
        deselect();
    }

    public void select() {
        setBackgroundColor(SELECTED_COLOR);
        if(view != null) {
            view.setVisibility(VISIBLE);
        }
    }

    public void deselect() {
        setBackgroundColor(BASE_COLOR);
        if(view != null){
            view.setVisibility(GONE);

        }
    }

    public void setButtons(ArrayList<TableButton> list) {
        buttons = list;
    }

    public void setView (View view) {
        this.view = view;
    }

}
