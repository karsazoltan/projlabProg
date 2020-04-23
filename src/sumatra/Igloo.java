package sumatra;

import java.util.ArrayList;

/**
 * Építmény leszármazott, azt szimbolizálja, hogy az adott mezőn van iglu. Ekkor a hóvihar káros hatásaitól
 * a mezőn álló játékosok védve vannak.
 */
public class Igloo extends Building {

    /**
     * Szkeleton konstruktor, meg lehet adni neki az objektum nevét
     */
    public Igloo() {
        type = "igloo";
    }

    /**
     * A birtokos mezőt érintő hóvihar bekövetkezésekor fut le. Nem csinál semmit, ugyanis azokon a mezőkön,
     * ahol iglu van, a játékosok a hóvihartól védve vannak, nem csökken a testhőjük.
     * @param ps A Buildinget tartalmazó Tile-on álló játékosok tömbje.
     */
    @Override
    public void onStorm(ArrayList<Creature> ps) {}

    /**
     * A lények nem gyakorolnak hatást egymásra
     * @param newCreature - az új lény
     * @param creatures - eddig itt tartózkodott lények
     */
    @Override
    public void newCreature(Creature newCreature, ArrayList<Creature> creatures) {

    }
}
