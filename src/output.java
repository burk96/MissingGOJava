import java.util.Scanner;
import java.util.Set;

class output {
    @SuppressWarnings("Duplicates")
    static void textOut(Set<pokemon> db) {
        int oCounter = 0;   //Num of Pokemon that are out
        int spCounter = 0;  //Num of Pokemon that are special release
        int caughtCounter = 0;
        int oneCount = 0;   //Num of Pokemon that are Gen 1
        int twoCount = 0;   //Num of Pokemon that are Gen 2
        int threeCount = 0; //Num of Pokemon that are Gen 3
        int fourCount = 0;  //Num of Pokemon that are Gen 4
        int threeUnreleasedCount = 0;
        int fourUnreleasedCount = 0;
        Scanner keyboard = new Scanner(System.in);
        int uInput;

        //Count the counters
        for (pokemon current : db) {
            if (current.isOut()) {
                //System.out.println(current);
                oCounter++;
                if (current.isSpRelease())
                    spCounter++;
                if (current.getGen() == 1 && current.isCaught())
                    oneCount++;
                if (current.getGen() == 2 && current.isCaught())
                    twoCount++;
                if (current.getGen() == 3 && current.isCaught())
                    threeCount++;
                if (current.getGen() == 4 && current.isCaught())
                    fourCount++;
                if (current.isCaught())
                    caughtCounter++;
            } else if (!current.isOut()) {
                if (current.getGen() == 3)
                    threeUnreleasedCount++;
                if (current.getGen() == 4)
                    fourUnreleasedCount++;
            }
        }

        //Input loop
        do {
            System.out.println("Enter '1' to see your overall progress");
            System.out.println("Enter '2' to see your progress for each generation");
            System.out.println("Enter '3' to see what Pokemon are reasonably obtainable");
            System.out.println("Enter '4' to see what Pokemon needs a Sinnoh Stone");
            System.out.println("Enter '5' to see Special Release Pokemon");
            System.out.println("Enter '6' to see Pokemon who have not been released yet");
            System.out.println("Enter '7' to see Pokemon which can be obtained through evolution");
            System.out.println("Enter '0' to exit");
            uInput = keyboard.nextInt();

            //Overall progress
            if (uInput == 1) {
                System.out.println("You have caught " + caughtCounter + " pokemon");
                System.out.println("Pokedex completion:");
                percentPrint((db.size() - caughtCounter), db.size());
                System.out.println("\n" + (db.size() - caughtCounter) + " Pokemon Remaining, including the " +
                        (db.size() - oCounter) + " unreleased");
                System.out.println(oCounter + " Pokemon are currently in the game");
            }

            //Progress in each gen
            if (uInput == 2) {
                System.out.println("\n" + oneCount + "  are from Generation 1");
                percentPrint(151- oneCount, 151);
                System.out.println(twoCount + "  are from Generation 2");
                percentPrint(100 - twoCount, 100);
                System.out.println(threeCount + " are from Generation 3");
                percentPrint((135 - threeUnreleasedCount) - threeCount, 135 - threeUnreleasedCount);
                System.out.println(fourCount + " are from Generation 4");
                percentPrint((107 - fourUnreleasedCount) - fourCount, 107 - fourUnreleasedCount);
            }

            //Pokemon that are reasonably obtainable
            if (uInput == 3) {
                int g = 0;
                System.out.println("\n" + spCounter + " have some sort of special release (Regional, Legendary, etc.)");
                System.out.println("So about " + (oCounter - spCounter) + " are reasonably obtainable");


                System.out.println("\nThese are:");
                for (pokemon current : db) {
                    if (current.isOut() && !current.isSpRelease() && !current.isCaught()) {
                        g = genPrinter(g, current);
                    }
                }
            }

            //Pokemon that need a Sinnoh Stone
            if (uInput == 4) {
                int a = 0;
                for (pokemon current : db) {
                    if (current.isOut() && current.needsSStone() && !current.isCaught()) {
                        System.out.println(current);
                        a++;
                    }
                }
                System.out.println("There are " + a + " Pokemon that require a Sinnoh Stone");
            }

            //Special Release Pokemon
            if (uInput == 5) {
                int g = 0;
                for (pokemon current : db) {
                    if (current.isOut() && current.isSpRelease() && !current.isCaught()) {
                        g = genPrinter(g, current);
                    }
                }
            }

            //Pokemon that have not been released
            if (uInput == 6) {
                int g = 0;
                for (pokemon current : db) {
                    if (!current.isOut()) {
                        g = genPrinter(g, current);
                    }
                }
                System.out.println("Niantic still needs to release " + (db.size() - oCounter) + " Pokemon!");
            }

            System.out.print("\n");

            //Evolve to
            if (uInput == 7) {
                int g = 0;
                for (pokemon current : db) {
                    if (current.canEvolve() && current.isOut() && !current.isCaught()) {
                        g = genPrinter(g, current);
                    }
                }
                System.out.println();
            }
        } while (uInput != 0);
    }

    private static int genPrinter(int g, pokemon current) {
        if (current.getGen() > g) {
            System.out.println("-- Gen " + current.getGen() + " --");
        }

        g = current.getGen();

        System.out.println(current);
        return g;
    }

    private static void percentPrint (int is, int of) {
        is = of - is;
        int percent = (int)((is * 100.0f) / of);
        int roundedPercent = percent / 5;
        System.out.print("[");
        for (int i = 0; i < roundedPercent; i++) {
            System.out.print("#");
        }
        int spaces = 20 - roundedPercent;
        while (spaces != 0){
            System.out.print(" ");
            spaces--;
        }
        System.out.print("] " + percent + "% complete \n");
    }
}
