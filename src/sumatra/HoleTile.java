package sumatra;

/**
 * Lyukas jégtáblát reprezentáló osztály
 */
public class HoleTile extends Tile {

    /**
     * Elfogadja a táblára lépő játékost
     * @param p A tábláról lépő játékos
     */
    @Override
    public void accept(Player p) {
        Skeleton.printLine(this.objName, "accept()");
        players.add(p);
        p.fallInWater();
        Skeleton.returned();
    }

    /**
     * Szkeleton konstruktor, meg lehet adni neki az objektum nevét
     * @param objName Az objektum, mint változó neve
     */
    public HoleTile(String objName) {
        super(objName);
    }

    /**
     * Lyukra próbál tárgyat elhelyezni
     * @param i Az elhelyezendő tárgy
     * @return Nem lehet tárgyat lyukra rakni
     */
    @Override
    public boolean placeItem(Item i) {
        Skeleton.printLine(this.objName, "placeItem()");
        Skeleton.returned();
        return false;
    }

    /**
     * Lyukra Igloot épít
     */
    @Override
    public void buildIgloo() {
        Skeleton.printLine(this.objName, "buildIgloo()");
        Skeleton.returned();
    }
}
