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
     * Lyukas jégtábla konstruktora
     * @param snowAmount A jégtáblán lévő hó mélysége
     */
    public HoleTile(int snowAmount) {
        super(snowAmount);
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
}
