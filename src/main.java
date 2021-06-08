import java.util.Set;

class main {
    public static void main(String[] args){
        Set<pokemon> db = loader.loadPokemon();

        output.textOut(db);
    }
}
