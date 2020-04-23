package sumatra;

import java.util.ArrayList;

/**
 * Absztrakt építmény osztály. Minden Tile-nak van egy Buildinge, ami meghatározza, hogy hóvihar esetén
 * a mezőn álló játékosokkal mi történik. Ez a lehetséges építmények közös őse.
 */
public abstract class Building {
    /**
     * Szkeleton konstruktor, meg lehet adni neki az objektum nevét
     */
    public Building() {}

    /**
     * Absztrakt függvény, hóvihar bekövetkezése esetén fut le. A Buildinget tartalmazó mező játékosaira
     * van hatással, de a konkrét következmények a leszármazottól függenek.
     * @param ps A Buildinget tartalmazó Tile-on álló játékosok tömbje.
     */
    public abstract void onStorm(ArrayList<Creature> ps);

    /**
     * Absztrakt függvény, az épület hogy reagál egy új lény érkezésére
     * @param newCreature - az új lény
     * @param creatures - eddig itt tartózkodott lények
     */
    public abstract void newCreature(Creature newCreature, ArrayList<Creature> creatures);

    protected String type;

    public String getBuildingType() {
        return type;
    }
}
