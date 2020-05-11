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
        System.out.println("> It's a hole, you fall into water!");
        c.fallInWater();
        updateViews();
    }

    /**
     * Lyukas jégtábla konstruktora
     * @param snowAmount A jégtáblán lévő hó mélysége
     */
    public HoleTile(int id, int snowAmount) {
        super(id, snowAmount);
        type = "hole";
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
     * Lyukra épít: semmi sem történik
     */
    @Override
    public void setBuilding(Building b) {

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
