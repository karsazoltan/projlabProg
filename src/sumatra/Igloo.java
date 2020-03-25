package sumatra;

/**
 * Építmény leszármazott, azt szimbolizálja, hogy az adott mezőn van iglu. Ekkor a hóvihar káros hatásaitól
 * a mezőn álló játékosok védve vannak.
 */
public class Igloo extends Building {
    /**
     * A birtokos mezőt érintő hóvihar bekövetkezésekor fut le. Nem csinál semmit, ugyanis azokon a mezőkön,
     * ahol iglu van, a játékosok a hóvihartól védve vannak, nem csökken a testhőjük.
     * @param ps A Buildinget tartalmazó Tile-on álló játékosok tömbje.
     */
    @Override
    public void onStorm(ArrayList<Player> ps) { }
}
