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
     * @param heal mennyivel tudja növelni a játékos hőmennyiségét
     */
    public Food(int heal) {
        healup = heal;
        itemtype = "food";
    }
    public Food() { this(1); }
    /** Ezen Item hatását rögtön kifejti, és elpusztul: Növeli a játékos életerejét 1-el
     * @param p a tárgyat felveő játékos
     */
    public void giveToPlayer(Player p) {
        p.heal(healup);
    }

    /**
     * Kiírja a megvalósító osztály adatait az átadott streamre
     *
     * @param stream ahova kiírjuk az adatokat
     * @param prefix Előtag (általában sok space)
     */
    @Override
    public void printData(OutputStream stream, String prefix) {}
}