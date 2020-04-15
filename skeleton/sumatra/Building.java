package sumatra;

import java.util.ArrayList;

/**
 * Absztrakt építmény osztály. Minden Tile-nak van egy Buildinge, ami meghatározza, hogy hóvihar esetén
 * a mezőn álló játékosokkal mi történik. Ez a lehetséges építmények közös őse.
 */
public abstract class Building {
    protected String objName;

    /**
     * Szkeleton konstruktor, meg lehet adni neki az objektum nevét
     * @param objName Az objektum, mint változó neve
     */
    public Building(String objName) {
        this.objName = objName;
    }

    /**
     * Absztrakt függvény, hóvihar bekövetkezése esetén fut le. A Buildinget tartalmazó mező játékosaira
     * van hatással, de a konkrét következmények a leszármazottól függenek.
     * @param ps A Buildinget tartalmazó Tile-on álló játékosok tömbje.
     */
    public abstract void onStorm(ArrayList<Player> ps);
}
