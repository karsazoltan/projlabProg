package sumatra;

import java.util.ArrayList;

public class Tent extends Building {

    /**
     * Alap ctor, meg lehet adni neki az objektum nevét
     * @param objName Az objektum, mint változó neve
     */
    public Tent(String objName) {
        super(objName);
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

    public void destroy(Tile t) {
        t.setBuilding(new NoBuilding("nobuilding"));
    }
}
