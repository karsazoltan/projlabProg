package sumatra;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Bear extends Creature{

    public Bear(Tile startTile, int pindex){
        super(startTile, pindex);
        type = "Polar bear";
    }

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
    void playRound() {       
        // Move in random direction.
        ArrayList<Tile> neighbours = tile.getNeighbors();
        int targetTileIdx = ThreadLocalRandom.current().nextInt(neighbours.size());
        move( neighbours.get( targetTileIdx ) );
    }

    @Override
    void collideWith(Creature c) {
        c.hitBy(this);
    }

    @Override
    void fallInWater() {}

    @Override
    void damage(int amount) {}

    @Override
    void hitBy(Bear b) { }

    @Override
    boolean saveMe(Player p, Tile t) { 
        return false; 
    }
}