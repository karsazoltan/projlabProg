package sumatra;

import java.io.OutputStream;

/**
 * Az étel tárgyat reprezentáló osztály
 */
public class Food extends Item {
    /**
     * az élelem mennyi hőegységgel növeli a játékos skáláját
     */
    private int healup;

    /**
     * Bővített ctor
     * @param objName az élelem, tárgy neve
     * @param heal mennyivel tudja növelni a játékos hőmennyiségét
     */
    public Food(String objName, int heal) {
        super(objName);
        healup = heal;
    }
    /**
     * Default ctor
     */
    public Food(String objName)  {
        this(objName, 1);
    }
    public Food() { this("food", 1); }
    public Food(int heal) { this("food", heal); }
    /** Ezen Item hatását rögtön kifejti, és elpusztul: Növeli a játékos életerejét 1-el
     * @param p a tárgyat felveő játékos
     */
    public void giveToPlayer(Player p) {
        p.heal(healup);
    }
}