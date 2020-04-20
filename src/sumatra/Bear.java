package sumatra;

import java.io.OutputStream;

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
        // TODO
        // Move in random direction.
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