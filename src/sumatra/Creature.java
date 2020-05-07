package sumatra;

import graphics.IView;
import graphics.IViewable;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

//public class Creature implements Printable{
public abstract class Creature implements Printable, IViewable {
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

    /**
     * A lény lejátsza a körét, feltételezve, hogy a játék automata módban van.
     */
    abstract void playRound();
    /**
     * A lény lejátsza a körét, feltételezve, hogy a játék managed módban van.
     */
    void playManagedRound(){
        playRound();
    }

    /**
     * A lényt egy új mezőre lépteti.
     * @param newTile a mező, ahova a lény lép.
     */
    void move(Tile newTile){
        if( tile.isNeighbor(newTile) ){
            tile.remove(this);
            tile = newTile;
            newTile.accept(this);
            updateViews();
        }
    }

    /**
     * Visszaadja azt a mezőt, amin jelenleg van a lény.
     * @return mező melyen jelenleg van a lény.
     */ 
    public Tile getTile(){
        return tile;
    }

    /**
     * A lény ütközik egy másik lénnyel.
     * @param c a másik lény.
     */ 
    abstract void collideWith(Creature c);
    /**
     * Egy medve nekimegy a lénynek.
     * @param b a medve.
     */
    abstract void hitBy(Bear b);
    /**
     * A lény beleesik a vízbe.
     */ 
    abstract void fallInWater();
    /**
     * Megsebzi a lényt.
     * @param amount ennyi életerőt veszít el a lény.
     */
    abstract void damage(int amount);
    /**
     * Egy játékos mentést kér a lénytől.
     * @param p megmentendő játékos.
     * @param t megmententő játékos mezője.
     * @return a kimentés sikeressége.
     */ 
    abstract boolean saveMe(Player p, Tile t);

    /** Sztringként a creature neve. A tiles parancs kimenetéhez kell */
    protected String type;
    public void listGameInfo() {
        System.out.println("> " + type + " " + index + " is on tile " + World.getInstance().getTileIndex(tile));
    }

    /**  
     * Visszaadja Sztringként a lény típusát.
     * @return a lény típusa (Eskimo, Researcher vagy Polar bear)
     */
    public String getType(){
        return type;
    }

    /**
     * Visszaadja a lény globális indexét.
     * @return a lénty globális indexe.
     */
    public int getIndex(){
        return index;
    }

    /**
     * Visszaad egy lista sztringet a lény állapotáról, a megjelenítéshez kell.
     * @return egy lista sztring.
     */
    abstract public ArrayList<String> getDisplayData();

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
                case "polarbear": c = new Bear(t, idx); t.accept(c); return c;
                case "researcher": c = new Researcher(t, idx); t.accept(c); break;
                case "eskimo": c = new Eskimo(t, idx); t.accept(c); break;
                default: throw new InputMismatchException("Invalid Creature config!");
            }
            Player pc = (Player) c;
            pc.health = Integer.parseInt(firstline[3]);
            pc.mana = Integer.parseInt(firstline[4]);

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


    /**
     * Nézeti lista
     */
    private ArrayList<IView> views = new ArrayList<>();

    /**
     * Hozzáad a tárolt nézetek közé még egyet.
     *
     * @param v - a hozzáadott View
     */
    @Override
    public void addView(IView v) {
        views.add(v);
    }

    /**
     * Frissíti a nézeteket.
     */
    @Override
    public void updateViews() {
        for (IView v : views) {
            v.subjectChanged();
        }
    }
}