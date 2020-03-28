package sumatra;

import java.util.ArrayList;

/**
 * Búvárruha leszármazott, azt szimbolizálja, hogy a játékosnak nincsen búvárruhája.
 */
public class NoDivingSuit extends DivingSuit {

    /**
     * Szkeleton konstruktor, meg lehet adni neki az objektum nevét
     * @param objName Az objektum, mint változó neve
     */
    public NoDivingSuit(String objName) {
        super(objName);
    }

    /**
     * A ruhát birtokló játékos vízbeesésekor lefutó függvény. Ha a játékos ruhája ez, akkor
     * a játékost ki kell mentenie egy, valamelyik szomszédos mezőn álló játékosnak, különben
     * a játék véget ér és a játékosok vesztenek.
     * @param p A ruhát birtokló játékos.
     */
    @Override
    public void fallInWater(Player p) {
        Skeleton.printLine(this.objName, "fallInWater()");
        ArrayList<Player> players = p.getTile().getNeighingPlayers();
        
        // eleinte úgy vesszük, hogy vízbe esik
        boolean result = false; 
        for (Player q : players) {
            // ha valaki kimenti
            if (q.saveMe(p, p.getTile())) {
                // akkor a result true
                result = true;
            }
            
        }

        // ha senki sem mentette ki, akkor vége
        if (!result) {
            World.getInstance().loseGame();
        }

        Skeleton.returned();
    }
}
