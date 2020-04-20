package sumatra;

//public class Creature implements Printable{
public abstract class Creature implements Printable{
    /**
     * A mező melyen a lény jelenleg tartózkodik.
     */
    protected Tile tile;

    abstract void playRound();

    void move(Tile newTile){
        if( tile.isNeighbor(newTile) ){
            tile.remove(this);
            newTile.accept(this);
            tile = newTile;
        }
    }

    /**
     * Visszaadja azt a mezőt, amin jelenleg van a lény.
     * @return mező melyen jelenleg van a lény.
     */ 
    public Tile getTile(){
        return tile;
    }

    abstract void collideWith(Creature c);
    abstract void hitBy(Bear b);
    abstract void fallInWater();
    abstract void damage(int amount);
}