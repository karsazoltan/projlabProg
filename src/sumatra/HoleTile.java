package sumatra;

/**
 * Lyukas jégtáblát reprezentáló osztály
 */
public class HoleTile extends Tile {

    /**
     * Elfogadja a táblára lépő élőlényt
     * @param c A táblára lépő élőlény
     */
    @Override
    public void accept(Creature c) {
        creatures.add(c);
        c.fallInWater();
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
        return false;
    }

    /**
     * Lyukra Igloot épít
     */
    @Override
    public void buildIgloo() {
    }

    /**
     * Kiírja a tile adatait felhasználóbarát módon - a tiles parancs kimenetéhez kell.
     */
    public void listGameInfo() {
        int idx = World.getInstance().getTileIndex(this);
        String type = (is_capacity_known) ? "H" : "?";
        String visibleitem = (snowlayers == 0 && item != null) ? ", visible item" : "";
        System.out.println(idx + ": " + type + visibleitem);
    }
}
