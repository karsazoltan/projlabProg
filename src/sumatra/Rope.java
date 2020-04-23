package sumatra;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Absztrakt kötél osztály, a leszármazottak reprezentálják a tényleges kötelet, és a hiányzó kötelet.
 */
public abstract class Rope extends AutomaticItem {
    /**
     * A kötelet hozzárendeli a paraméterként kapott játékoshoz.
     * @param p azon játékos, aki a tárgyat birtokolni fogja, vagy a tárgy valamilyen akciót hajt rajta végre
     */
    @Override
    public void giveToPlayer(Player p) {
        p.addRope(this);
    }

    /**
     * Játékosmentő absztrakt függvény, a leszármazottak definiálják a függvény működését.
     * @param p A kimentendő játékos.
     * @param t A kimentendő játékos mezője.
     * @param newTile A kötelet birtokló játékos mezője.
     */
    public abstract boolean save(Player p, Tile t, Tile newTile);

    protected String type;

    @Override
    public void printData(OutputStream stream, String prefix) {
        PrintWriter pw = new PrintWriter(stream);
        pw.println(prefix + "rope " + type);
        pw.flush();
    }
}
