package sumatra;

import java.util.ArrayList;

public class Tent extends Building {
    Tile owner;

    /**
     * // TODO EZT Írd meg pls. ~ Attila
     */
    public Tent(Tile t) {
        super();
        owner = t;
        World.getInstance().registerTent(this); // ~ Attila - regisztráljuk magunkat pls
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

    public void destroy() {
        owner.setBuilding(new NoBuilding("nobuilding"));
    }
}
