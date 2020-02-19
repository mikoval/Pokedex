package pokedex.mike.pokedex;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MovesTable extends TableLayout {
    private String directory;
    private ArrayList<TableButton> buttons;
    GradientDrawable RECTANGLE;

    View abilitiesView;
    View tmsView;
    View hmsView;

    public MovesTable(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public MovesTable(Context context) {
        super(context);
        init();
    }

    private void init() {
        RECTANGLE = (GradientDrawable)getResources().getDrawable( R.drawable.rectangle_thin_background );
        RECTANGLE.setColor(Color.LTGRAY);


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

        addView(tabRow);

        abilitiesView = createAbilities();
        tmsView = createTMS();
        hmsView = createHMS();
        addView(abilitiesView);
        addView(tmsView);
        addView(hmsView);
        buttons.get(0).setView(abilitiesView);
        buttons.get(1).setView(tmsView);
        buttons.get(2).setView(hmsView);

        buttons.get(0).select();

    }

    public TableRow createTableTitle(String c1, String c2, String c3, int color, int textColor) {
        TableRow row = new TableRow(getContext());
        row.setWeightSum(1.1f);
        {
            TextView level = new TextView(getContext());
            level.setText(c1);
            level.setTextColor(textColor);
            level.setBackgroundColor(color);

            level.setTextAlignment(TEXT_ALIGNMENT_CENTER);
            level.setPadding(0,15, 0,15);

            TableRow.LayoutParams span = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);

            span.weight = 0.2f;
            span.bottomMargin = 1;
            span.topMargin = 1;

            level.setLayoutParams(span);

            row.addView(level);
        }

        {
            TextView ability = new TextView(getContext());
            ability.setText(c2);
            ability.setTextColor(textColor);
            ability.setTextAlignment(TEXT_ALIGNMENT_CENTER);
            ability.setBackgroundColor(color);

            ability.setPadding(0,15, 0,15);

            TableRow.LayoutParams span = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);

            span.weight = 0.6f;
            span.bottomMargin = 1;
            span.leftMargin = 1;
            span.rightMargin = 1;
            span.topMargin = 1;

            ability.setLayoutParams(span);

            row.addView(ability);
        }

        {
            TextView type = new TextView(getContext());
            type.setText(c3);
            type.setTextColor(textColor);
            type.setTextAlignment(TEXT_ALIGNMENT_CENTER);
            type.setBackgroundColor(color);

            type.setPadding(0,15, 0,15);


            TableRow.LayoutParams span = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);

            span.weight = 0.3f;
            span.bottomMargin = 1;
            span.topMargin = 1;


            type.setLayoutParams(span);

            row.addView(type);
        }
        return row;
    }

    public View createAbilities() {

        TableLayout view = new TableLayout(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        view.addView(createTableTitle("Level", "Move", "Type", TITLE_COLOR, TITLE_TEXT_COLOR));

        for(int i =0 ; i < 10; i++) {
            view.addView(createTableTitle("" + i, "FOO", "ELECTRIC", ROW_COLOR, ROW_TEXT_COLOR));
        }
        return view;

    }

    public View createTMS() {

        TableLayout view = new TableLayout(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        view.addView(createTableTitle("TM", "Move", "Type", TITLE_COLOR, TITLE_TEXT_COLOR));

        for(int i =0 ; i < 10; i++) {
            view.addView(createTableTitle("" + i, "TM", "FIRE", ROW_COLOR, ROW_TEXT_COLOR));
        }
        return view;

    }

    public View createHMS() {

        TableLayout view = new TableLayout(getContext());
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        view.addView(createTableTitle("HM", "Move", "Type", TITLE_COLOR, TITLE_TEXT_COLOR));

        for(int i =0 ; i < 5; i++) {
            view.addView(createTableTitle("" + i, "HM", "WATER", ROW_COLOR, ROW_TEXT_COLOR));
        }
        return view;

    }

    public void setDirectory(String directory){
        this.directory = directory;
    }

}
