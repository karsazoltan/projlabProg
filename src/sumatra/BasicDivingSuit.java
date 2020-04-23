package sumatra;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Búvárruha leszármazott, azt szimbolizálja, hogy a játékosnak van búvárruhája.
 */
public class BasicDivingSuit extends DivingSuit {
    public BasicDivingSuit() {
        type = "basicdiving";
    }

    /**
     * A ruhát birtokló játékos vízbeesésekor lefutó függvény. Ha a játékos ruhája ez, akkor
     * nincsen semmi gond, a játékos egyszerűen túléli a vízbeesést.
     * @param p A ruhát birtokló játékos.
     */
    @Override
    public boolean fallInWater(Player p) {
        return false;
    }
}
