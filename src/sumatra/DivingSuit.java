package sumatra;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Búvárruha absztakt osztály. A leszármazottak reprezentálják a tényleges búvárruhát, illetve a hiányzó búvárruhát.
 */
public abstract class DivingSuit extends AutomaticItem {
    /**
     * Absztrakt függvény arra az eseményre, amikor a ruhát birtokló játékos vízbe esik.
     * A leszármazottak írják le az ekkor lefutó eseményeket.
     * @param p A ruhát birtokló játékos.
     */
    public abstract boolean fallInWater(Player p);

    /**
     * A búvárruhát hozzárendeli a paraméterként kapott játékoshoz.
     * @param p azon játékos, aki a tárgyat birtokolni fogja, vagy a tárgy valamilyen akciót hajt rajta végre
     */
    @Override
    public void giveToPlayer(Player p) {
        p.addDivingSuit(this);
    }

    /**
     * a suit típusa(kíráshoz és beolvasáshoz)
     */
    protected String type;

    @Override
    public void printData(OutputStream stream, String prefix) {
        PrintWriter pw = new PrintWriter(stream);
        pw.println(prefix + "suit " + type);
        pw.flush();
    }
}
