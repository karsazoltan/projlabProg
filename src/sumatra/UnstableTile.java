package sumatra;

import java.util.ArrayList;

/**
 * Instabil táblát reprezentáló osztály
 */
public class UnstableTile extends Tile {

    /**
     * Elfogadja a táblára lépő játékost
     * @param p A tábláról lépő játékos
     */
    @Override
    public void accept(Player p) {
        Skeleton.printLine(this.objName, "accept");

        players.add(p);
        boolean enoughCapacity = Skeleton.askQuestion("Elbírja a jégtábla a játékosokat?");
        if (!enoughCapacity) {
            flip();
        }

        Skeleton.returned();
    }

    /**
     * Szkeleton konstruktor, meg lehet adni neki az objektum nevét
     * @param objName Az objektum, mint változó neve
     */
    public UnstableTile(String objName) {
        super(objName);
    }

    /**
     * Az összes rajta álló játékost a vízbe dobja
     */
    public void flip() {
        Skeleton.printLine(this.objName, "flip");

        ArrayList<Player> players = getPlayers();
        for (Player p : players)
            p.fallInWater();

        Skeleton.returned();
    }
}
