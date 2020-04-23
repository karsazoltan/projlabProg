package sumatra;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Általános absztrakt osztály, a játékban található tárgyak modellezésére
 */
public abstract class Item implements Printable {
    /** A tárgyat (magát) hozzárendeli egy játékoshoz
     * @param p azon játékos, aki a tárgyat birtokolni fogja, vagy a tárgy valamilyen akciót hajt rajta végre
     */
    public abstract void giveToPlayer(Player p);
}