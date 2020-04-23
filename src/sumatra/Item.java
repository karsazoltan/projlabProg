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

<<<<<<< HEAD
    /**
     * Kiírja a megvalósító osztály adatait az átadott streamre
     * @param stream ahova kiírjuk az adatokat
     */
    @Override
    public void printData(OutputStream stream) {
        PrintWriter pw = new PrintWriter(stream);
        // TODO
=======
    protected String itemtype = "none";
    public String toString() {
        return itemtype;
>>>>>>> f6739c053a6c2506b54454216bcea3afa2d90088
    }
}