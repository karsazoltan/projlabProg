package sumatra;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/** Medve osztály */
public class Bear extends Creature{

    /**
     * Konstruktor
     * @param startTile A medve kezdő jégtáblája
     * @param pindex A medve indexe
     */
    public Bear(Tile startTile, int pindex){
        super(startTile, pindex);
        type = "Polar bear";
    }

    /**
     * Null-t ad vissza, ezt a játék megnyerése megállapitásánál kell használni, amibe a medve nem számít bele.
     */
    @Override
    public Tile getTile() { 
        return null; 
    }

    /**
     * Kiírja a megvalósító osztály adatait az átadott streamre
     *
     * @param stream ahova kiírjuk az adatokat
     * @param prefix Előtag (általában sok space)
     */
    @Override
    public void printData(OutputStream stream, String prefix) {
        PrintWriter pw = new PrintWriter(stream);
        pw.println(prefix + index + " polarbear " + World.getInstance().getTileIndex(tile));
        pw.flush();
    }

    /**
     * Visszaad egy lista sztringet a medve állapotáról, a megjelenítéshez kell.
     * @return egy lista sztring.
     */
    @Override
    public ArrayList<String> getDisplayData() {
        return new ArrayList<>();
    }

    /**
     * A medve lejátsza a körét, feltételezve, hogy a játék automata módban van.
     */
    @Override
    void playRound() {       
        // Move in random direction.
        ArrayList<Tile> neighbours = tile.getNeighbors();
        if (neighbours.size() == 0) {
            System.out.println("> Polar bear did not move, as it didn't find any neighbouring tiles");
            World.getInstance().playerFinished();
            return;
        }
        int targetTileIdx = ThreadLocalRandom.current().nextInt(neighbours.size());
        move( neighbours.get( targetTileIdx ) );
        System.out.println("> Polar bear " + index + " moved to tile " + targetTileIdx);
        World.getInstance().playerFinished();
    }

    /**
     * A medve lejátsza a körét, feltételezve, hogy a játék managed módban van.
     */
    @Override
    void playManagedRound() {
        Interpreter.requestBearMovement(this, tile);
        World.getInstance().playerFinished();
    }

    /**
     * Medveként ütközik egy másik lénnyel.
     * @param c a másik lény.
     */
    @Override
    void collideWith(Creature c) {
        c.hitBy(this);
    }

    /** A medve beleesik a vízbe. Mivel a medvének a víz nem árt, nem történik semmi.*/
    @Override
    void fallInWater() {}

    /** 
     * A medvét megpróbálja sebezni valami (hóvihar), de a medvét ez nem zavarja, tehát nem történik semmi.
     * @param amount ez most nem csinál semmit, mert nem sebződik a medve. 
     */
    @Override
    void damage(int amount) {}

    /**
     * A medvének nekimegy egy másik, medve, nem történik semmi.
     */
    @Override
    void hitBy(Bear b) { }

    /**
     * Egy játékos azt akarja, hogy a medve mentse meg őt. A medve nem csinál ilyet, tehát nem történik semmi.
     */
    @Override
    boolean saveMe(Player p, Tile t) { 
        return false; 
    }
}