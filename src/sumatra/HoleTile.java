package sumatra;

/**
 * Lyukas jégtáblát reprezentáló osztály
 */
public class HoleTile extends Tile {
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
