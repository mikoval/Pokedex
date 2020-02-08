package pokedex.mike.pokedex;

import android.util.Log;

public class PokemonType {
    public static final int UNKNOWN =  0;
    public static final int Bug =      1;
    public static final int Dragon =   2;
    public static final int Electric = 3;
    public static final int Fighting = 4;
    public static final int Fire =     5;
    public static final int Flying =   6;
    public static final int Ghost =    7;
    public static final int Grass =    8;
    public static final int Ground =   9;
    public static final int Ice =      10;
    public static final int Normal =   11;
    public static final int Poison =   12;
    public static final int Psychic =  13;
    public static final int Rock =     14;
    public static final int Water =    15;

    static int parseInt(String type) {
        type = type.toLowerCase();
        //Log.d("FINDME: " , "GETTING TYPE: " + type);
        if(type.equals("bug")) { return Bug; }
        if(type.equals("dragon")) { return Dragon; }
        if(type.equals("electric")) { return Electric; }
        if(type.equals("fighting")) { return Fighting; }
        if(type.equals("fire")) { return Fire; }
        if(type.equals("flying")) { return Flying; }
        if(type.equals("ghost")) { return Ghost; }
        if(type.equals("grass")) { return Grass; }
        if(type.equals("ground")) { return Ground; }
        if(type.equals("ice")) { return Ice; }
        if(type.equals("normal")) { return Normal; }
        if(type.equals("poison")) { return Poison; }
        if(type.equals("psychic")) { return Psychic; }
        if(type.equals("rock")) { return Rock; }
        if(type.equals("water")) { return Water; }
        Log.d("FINDME: " , "UNABLE TO GET TYPE");

        return UNKNOWN;

    }

    static String getString(int type) {
        if(type == Bug) { return "Bug"; }
        if(type == Dragon) { return "Dragon"; }
        if(type == Electric) { return "Electric"; }
        if(type == Fighting) { return "Fighting"; }
        if(type == Fire) { return "Fire"; }
        if(type == Flying) { return "Flying"; }
        if(type == Ghost) { return "Ghost"; }
        if(type == Grass) { return "Grass"; }
        if(type == Ground) { return "Ground"; }
        if(type == Ice) { return "Ice"; }
        if(type == Normal) { return "Normal"; }
        if(type == Poison) { return "Poison"; }
        if(type == Psychic) { return "Psychic"; }
        if(type == Rock) { return "Rock"; }
        if(type == Water) { return "Water"; }

        return "UNKNOWN";
    }

    static int getColor(int type) {
        if(type == Bug) { return 0xFFC4EE8D; }
        if(type == Dragon) { return 0xFF7F00FF; }
        if(type == Electric) { return 0xFFFFFF33; }
        if(type == Fighting) { return 0xFF9B3400; }
        if(type == Fire) { return 0xFFFF0000; }
        if(type == Flying) { return 0xFF66B2FF; }
        if(type == Ghost) { return 0xFF004C99; }
        if(type == Grass) { return 0xFF00CC00; }
        if(type == Ground) { return 0xFFFFCC7D; }
        if(type == Ice) { return 0xFF6666FF; }
        if(type == Normal) { return 0xFFA0A0A0; }
        if(type == Poison) { return 0xFF990099; }
        if(type == Psychic) { return 0xFFFF3399; }
        if(type == Rock) { return 0xFFBDA47E; }
        if(type == Water) { return 0xFF3333FF; }

        return 0xFF000000;
    }






}
