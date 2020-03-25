package sumatra;

/**
 * Búvárruha absztakt osztály. A leszármazottak reprezentálják a tényleges búvárruhát, illetve a hiányzó búvárruhát.
 */
public abstract class DivingSuit extends AutomaticItem {
    /**
     * Absztrakt függvény arra az eseményre, amikor a ruhát birtokló játékos vízbe esik.
     * A leszármazottak írják le az ekkor lefutó eseményeket.
     * @param p A ruhát birtokló játékos.
     */
    public abstract void fallInWater(Player p);

    /**
     * A búvárruhát hozzárendeli a paraméterként kapott játékoshoz.
     * @param p azon játékos, aki a tárgyat birtokolni fogja, vagy a tárgy valamilyen akciót hajt rajta végre
     */
    @Override
    public void giveToPlayer(Player p) {
        p.addDivingSuit(this);
    }
}
