package sumatra;
import java.util.ArrayList;

/**
 * Búvárruha leszármazott, azt szimbolizálja, hogy a játékosnak nincsen búvárruhája.
 */
public class NoDivingSuit extends DivingSuit {
    /**
     * Default ctor
     */
    public NoDivingSuit() {
        type = "none";
    }

    /**
     * A ruhát birtokló játékos vízbeesésekor lefutó függvény. Ha a játékos ruhája ez, akkor
     * a játékost ki kell mentenie egy, valamelyik szomszédos mezőn álló játékosnak, különben
     * a játék véget ér és a játékosok vesztenek.
     * @param p A ruhát birtokló játékos.
     */
    @Override
    public boolean fallInWater(Player p) {
        System.out.println("> You have no diving suit!");
        ArrayList<Creature> creatures = p.getTile().getNeighingCreatures();
        // eleinte úgy vesszük, hogy vízbe esik
        boolean result = true; 
        for (Creature q : creatures) {
            // ha valaki kimenti
            if (q.saveMe(p, p.getTile())) {
                // akkor a result false, mert nem esik bele igazából
                System.out.println("> You got saved!");
                result = false;
            }
            
        }
        if (result) System.out.println("> No players with ropes nearby!");
        return result;
    }
}
