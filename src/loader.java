import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class loader {
    static Set<pokemon> loadPokemon(){
        TreeSet<pokemon> result = new TreeSet<>();

        File filename = new File("PoGo Missing - Sheet1.csv");
        try{
            Scanner inputFile = new Scanner(filename);
            inputFile.nextLine(); //skip header
            while(inputFile.hasNextLine()){
                String[] splitString = inputFile.nextLine().split(",",-1);

                for (int i = 0; i < splitString.length; i++){
                    if (splitString[i].isEmpty())
                        splitString[i] = "?";
                }

                pokemon e = new pokemon(splitString);
                result.add(e);
            }
        } catch (FileNotFoundException e){
            System.out.println("Could not open file");
        }

        return result;
    }
}
