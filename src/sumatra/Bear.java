package sumatra;

import java.io.OutputStream;
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

    @Override
    public void printData(OutputStream stream) {
        // TODO Auto-generated method stub
        
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
}