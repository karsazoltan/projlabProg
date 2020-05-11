package sumatra;

/**
 * Általános absztrakt osztály, a játékban található tárgyak modellezésére
 */
public abstract class Item implements Printable {
    /** A tárgyat (magát) hozzárendeli egy játékoshoz
     * @param p azon játékos, aki a tárgyat birtokolni fogja, vagy a tárgy valamilyen akciót hajt rajta végre
     */
    public abstract void giveToPlayer(Player p);

    /**
     * grafikus kezeléshez, vagy nem a játékot leíró fájl során használandó
     */
    protected String itemtype = "none";
    public String toString() {
        return itemtype;
    }
}