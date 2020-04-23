package sumatra;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * 
 */
public abstract class FlarePart extends Item {
    /** Beregisztrálja a felvett rakétarészt
     * @param p a rakétarészt felvevő játékos
     */
    @Override
    public void giveToPlayer(Player p) {
        World.getInstance().registerFlarePart(this);
    }

    /**
     * Kiírja a megvalósító osztály adatait az átadott streamre
     * @param stream ahova kiírjuk az adatokat
     */
    @Override
    public void printData(OutputStream stream, String prefix) {
        PrintWriter pw = new PrintWriter(stream);
        pw.println(prefix + itemtype);
        pw.flush();
    }
}