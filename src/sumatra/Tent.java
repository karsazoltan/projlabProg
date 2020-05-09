package sumatra;

import java.util.ArrayList;

/**
 * Sátor osztály.
 */
public class Tent extends Building {
    Tile owner;
    /**
     * Alap konstruktor a tulajdonos tile átadásával
     * @param t a tulajdonos mező
     */
    public Tent(Tile t) {
        super();
        owner = t;
        World.getInstance().registerTent(this);
        type = "tent";
    }
    /**
     * Konstruktor adott lépésben létrehozott sátorhoz
     * @param t A sátor jégtáblája.
     * @param tps A létrehozás lépése.
     */
    public Tent(Tile t, int tps) {
        super();
        owner = t;
        World.getInstance().registerTent(this, tps);
        type = "tent";
    }
    /**
     * A tile, sátor viselkedése vihar esetén.
     * @param ps A Buildinget tartalmazó Tile-on álló játékosok tömbje.
     */
    @Override
    public void onStorm(ArrayList<Creature> ps) {

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

    /**
     * Szétrombolja önmagát
     */
    public void destroy() {
        System.out.println("> The tent has fallen apart on tile " + World.getInstance().getTileIndex(owner) + "!");
        owner.setBuilding(new NoBuilding());
    }
}
