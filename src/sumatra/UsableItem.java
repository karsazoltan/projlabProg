package sumatra;

import java.io.OutputStream;

/**
 * Absztrakt osztály a manuálisan használható eszközök modellezésére
 */
public abstract class UsableItem extends Item {

    public UsableItem(String objName) {
        super(objName);
    }

    /**
     * Az eszköz használatát reprezentáló függvény. Absztrakt, a leszármazottak ezt
     * a függvényt kell felüldefiniálják a működés végrehajtásához.
     * @param t A mező, amin az eszközt birtokló játékos az eszközt használni kívánja.
     */
    public abstract void use(Tile t);

    /**
     * Az eszközt hozzárendeli a paraméterként kapott játékoshoz.
     * @param p azon játékos, aki a tárgyat birtokolni fogja, vagy a tárgy valamilyen akciót hajt rajta végre
     */
    @Override
    public void giveToPlayer(Player p) {
        Skeleton.printLine(objName, "giveToPlayer()");
        p.addUsableItem(this);
        Skeleton.returned();
    }

    @Override
    public void printData(OutputStream stream) {

    }
}
