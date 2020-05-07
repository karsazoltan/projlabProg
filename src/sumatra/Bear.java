package sumatra;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Bear extends Creature{

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

    @Override
    public ArrayList<String> getDisplayData() {
        ArrayList<String> data = new ArrayList<String>();
        return data;
    }

    /**
     * A medve lejátsza a körét, feltételezve, hogy a játék automata módban van.
     */
    @Override
    void playRound() {       
        // Move in random direction.
        ArrayList<Tile> neighbours = tile.getNeighbors();
        int targetTileIdx = ThreadLocalRandom.current().nextInt(neighbours.size());
        move( neighbours.get( targetTileIdx ) );
        System.out.println("> Polar bear " + index + " moved to tile " + targetTileIdx);
        World.getInstance().playerFinished();
    }

    /**
     * A medve lejátsza a körét, feltételezve, hogy a játék managed módban van.
     */
    //@Override
    void playManagedRoundTextVersion() {
        Scanner input = World.getInstance().getInputScanner();
        System.out.print("> Polar bear: choose tile to move to: ");
        try {
            String line = input.nextLine().trim();
            System.out.println("    > Polar bear " + index + " moved to tile " + Integer.parseInt(line));
            move(World.getInstance().getTileAt(Integer.parseInt(line)));
        } catch (Exception e) {
            System.out.println("> Error: Invalid tile index!");
        }
    }

    /**
     * A lény lejátsza a körét, feltételezve, hogy a játék managed módban van.
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