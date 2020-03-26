package sumatra;

/**
 * Általános absztrakt osztály, a játékban található tárgyak modellezésére
 */
public abstract class Item {
    protected String objName;

    public Item(String objName) {
        this.objName = objName;
    }

    /** A tárgyat (magát) hozzárendeli egy játékoshoz
     * @param p azon játékos, aki a tárgyat birtokolni fogja, vagy a tárgy valamilyen akciót hajt rajta végre
     */
    public abstract void giveToPlayer(Player p);
}