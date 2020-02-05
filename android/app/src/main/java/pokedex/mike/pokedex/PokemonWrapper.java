package pokedex.mike.pokedex;

public class PokemonWrapper {
    String directory;
    int number;
    String name;
    int primaryType;
    int secondaryType;
    int picture;
    public PokemonWrapper(String directory, int number, String name, int type1, int type2) {
        this.number = number;
        this.name = name;
        this.primaryType = type1;
        this.secondaryType = type2;
        this.directory = directory;
    }
}
