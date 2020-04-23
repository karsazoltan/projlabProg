package sumatra;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.InputMismatchException;

//public class Creature implements Printable{
public abstract class Creature implements Printable{
    /**
     * A mező melyen a lény jelenleg tartózkodik.
     */
    protected Tile tile;
    /**
     * A lény globális sorszáma
     */
    protected int index;

    /**
     * Konstruktor, mely az egységes inicializálásért felel.
     * @param t A creature kezdő jégtáblája
     * @param pindex A creature globális sorszáma
     */
    public Creature(Tile t, int pindex) {
        tile = t;
        index = pindex;
    }

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

    /** Sztringként a creature neve. A tiles parancs kimenetéhez kell */
    protected String type;
    public void listGameInfo() {
        System.out.println("> " + type + " " + index + " is on tile " + World.getInstance().getTileIndex(tile));
    }

    /**
     * Betölt a bemeneti bufferedreaderből egy lényt.
     * @param br A bemeneti fájlt tartalmazó, megfelelő állapotban lévő bufferedreader.
     * @return A létrehozott élőlény.
     * @throws InputMismatchException Ha nem valid a betöltött fájl tartalma
     */
    public static Creature fromConfig(BufferedReader br) throws InputMismatchException {
        try {
            String[] firstline = br.readLine().trim().split(" ");
            Creature c;
            Tile t = World.getInstance().getTileAt(Integer.parseInt(firstline[2]));
            int idx = Integer.parseInt(firstline[0]);
            switch (firstline[1]) {
                case "polarbear": c = new Bear(t, idx); return c;
                case "researcher": c = new Researcher(t, idx); break;
                case "eskimo": c = new Eskimo(t, idx); break;
                default: throw new InputMismatchException("Invalid Creature config!");
            }
            Player pc = (Player) c;
            if (br.readLine().trim().split(" ")[1].equals("basic"))
                pc.addRope(new BasicRope());
            if (br.readLine().trim().split(" ")[1].equals("basicdiving"))
                pc.addDivingSuit(new BasicDivingSuit());
            int usableitems = Integer.parseInt(br.readLine().trim().split(" ")[1]);
            for (int i = 0; i < usableitems; i++) {
                String[] item = br.readLine().trim().split(" ");
                switch (item[0]) {
                    case "shovel": (new Shovel()).giveToPlayer(pc); break;
                    case "tent": (new TentEquipment()).giveToPlayer(pc); break;
                    case "brokenshovel": (new BrokenShovel(Integer.parseInt(item[1]))).giveToPlayer(pc); break;
                    default: throw new InputMismatchException("Invalid Creature config!");
                }
            }
            return pc;
        } catch (IOException e) {
            throw new InputMismatchException("Invalid Creature config!");
        }
    }
}