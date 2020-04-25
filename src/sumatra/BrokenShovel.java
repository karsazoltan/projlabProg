package sumatra;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Ásó manuálisan használható eszköz, használatával egyszerre két réteg hó is letakarítható egy mezőről.
 */
public class BrokenShovel extends UsableItem {
    int use_num;
    /**
     * Alap konstruktor a maximáli használati szám beállításával
     * @param maxuse használati esetek max. száma
     */
    public BrokenShovel(int maxuse) {
        use_num = maxuse;
        itemtype = "brokenshovel";
    }
    public BrokenShovel() {
        this(3);
    }
    /**
     * Ásó használata függvény. A paraméterként kapott mezőről leás két réteg havat.
     * @param t A mező, amin az eszközt birtokló játékos az eszközt használni kívánja.
     */
    @Override
    public void use(Tile t) {
        if(amortization())
            t.removeSnow(2);
    }

    /**
     * Az ásó kopásának felelőssége
     * @return még használható/nem
     */
    private boolean amortization() {
        if (use_num > 0){
            use_num--;    
            System.out.println("    > Uses remaining: " + use_num);
            return true;
        }
        else
        {
            System.out.println("    > This shovel has been used too much, it's completely broken!");
            return false;
        }
    }
    /**
     * Kiírja a megvalósító osztály adatait az átadott streamre
     *
     * @param stream ahova kiírjuk az adatokat
     * @param prefix Előtag (általában sok space)
     */
    @Override
    public void printData(OutputStream stream, String prefix) {
        PrintWriter pw = new PrintWriter(stream);
        pw.println(prefix + "brokenshovel " + use_num);
        pw.flush();
    }
}