package sumatra;

import java.util.ArrayList;

/**
 * Búvárruha leszármazott, azt szimbolizálja, hogy a játékosnak nincsen búvárruhája.
 */
public class NoDivingSuit extends DivingSuit {

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
        Skeleton.printLine(objName, "fallInWater()");
        ArrayList<Player> players = p.getTile().getNeighingPlayers();

        for (Player q : players) {
            q.saveMe(p, p.getTile());
            //TODO Bool visszatérés?
        }
        Skeleton.returned();
    }
}
