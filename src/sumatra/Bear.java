package sumatra;

import java.io.OutputStream;

public class Bear extends Creature{

    public Bear(){ //TODO Ide is startTile és index ~ Attila
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