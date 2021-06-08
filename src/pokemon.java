class pokemon implements Comparable<pokemon> {
    private final String name;
    private final int gen;
    private final int nDex;
    private final char out;
    private final char evolveTo;
    private final char regional;
    private final char legendary;
    private final char special;
    private final char sinStone;
    private final char have;

    pokemon(String[] input){
        this.name = input[0];
        this.gen = Integer.parseInt(input[1]);
        this.nDex = Integer.parseInt(input[2]);
        this.out = input[3].charAt(0);
        this.evolveTo = input[4].charAt(0);
        this.regional = input[5].charAt(0);
        this.legendary = input[6].charAt(0);
        this.special = input[7].charAt(0);
        this.sinStone = input[8].charAt(0);
        this.have = input[9].charAt(0);
    }

    public String toString() {
        return "#" + nDex + " " + name;
    }

    public int compareTo(pokemon pokemon){
        return nDex - pokemon.nDex;
    }

    public String getName(){
        return name;
    }

    public int getGen(){
        return gen;
    }

    public int getnDex(){
        return nDex;
    }

    public boolean isOut() {
        return out == 'Y' || out == 'y';
    }

    public boolean canEvolve() {
        return evolveTo == 'Y' || evolveTo == 'y';
    }

    public boolean isRegional() {
        return regional == 'Y' || regional == 'y';
    }

    public boolean isLegendary() {
        return legendary == 'Y' || legendary == 'y';
    }

    public boolean isSpRelease() {
        return special == 'Y' || special == 'y';
    }

    public boolean needsSStone() {
        return sinStone == 'Y' || sinStone == 'y';
    }

    public boolean isCaught(){return have == 'Y' || have == 'y';}
}
