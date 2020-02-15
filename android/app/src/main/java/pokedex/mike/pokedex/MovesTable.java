package pokedex.mike.pokedex;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;

public class MovesTable extends TableLayout {
    private String directory;
    private ArrayList<TableButton> buttons;

    public MovesTable(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public MovesTable(Context context) {
        super(context);
        init();
    }

    private void init() {
        TableRow tabRow = new TableRow(getContext());
        ArrayList<String> buttonLabels = new ArrayList<>();
        buttons = new ArrayList<>();

        buttonLabels.add("Abilities");
        buttonLabels.add("TMS");
        buttonLabels.add("HMS");
        for(int i = 0; i < buttonLabels.size(); i++){
            buttons.add(new TableButton(getContext()));
            buttons.get(i).setText(buttonLabels.get(i));
        }

        for(int i = 0; i < buttonLabels.size(); i++){
            buttons.get(i).setButtons(buttons);
            tabRow.addView(buttons.get(i));
        }

        buttons.get(0).select();
        addView(tabRow);
    }

    public void setDirectory(String directory){
        this.directory = directory;
    }

}
