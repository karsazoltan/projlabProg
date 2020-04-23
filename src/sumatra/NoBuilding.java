package sumatra;

import java.util.ArrayList;

/**
 * Építmény leszármazott, azt szimbolizálja, hogy az adott mezőn nincs építmény.
 */
public class NoBuilding extends Building {
    /**
     * Default ctor
     */
    public NoBuilding() {
        type = "none";
    }

    /**
     * A birtokos mezőt érintő hóvihar bekövetkezésekor fut le. A paraméterként kapott játékosok
     * testhőmérsékletét egyel csökkenti.
     * @param ps A Buildinget tartalmazó Tile-on álló játékosok tömbje.
     */
    @Override
    public void onStorm(ArrayList<Creature> ps) {
        for (Creature p : ps) {
            p.damage(1);
        }
    }

    /**
     * A lények hatást gyakorolnak egymásra
     * @param newCreature - az új lény
     * @param creaturs - eddig itt tartózkodott lények
     */
    @Override
    public void newCreature(Creature newCreature, ArrayList<Creature> creaturs) {
        for(Creature c : creaturs) {
            c.collideWith(newCreature);
            newCreature.collideWith(c);
        }
    }
}
