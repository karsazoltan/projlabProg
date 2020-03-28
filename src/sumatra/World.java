package sumatra;
import java.util.*;

/**
 * A játékot menedzselő singleton osztály. Kezeli a világot alkotó Tile mezőket, generálja a játékteret, illetve néha hóviharokat generál.
 */
public class World {
    /**
     * Mezók - Jaki még csinálja :)
     */
    private ArrayList<Tile> tiles;

    /**
     * A jelzőrakéta egy alkatrészét reprezentáló típus
     */
    private ArrayList<FlarePart> flareParts;

    /**
     * Singleton megvalósítás, a world példánya
     */
    private static World instance = new World();

    /**
     * Singleton megvalósítás, visszatér a singleton példánnyal.
     * @return A singleton példány.
     */
    public static World getInstance() {
        return instance;
    }

    /**
     * A játék indítására szolgáló függvény, körönként lehetőséget ad arra, hogy a játékosok lépjenek
     */
    public void startGame() {
        Skeleton.printLine("World", "startGame()");
        Skeleton.returned();
    }

    /**
     * Ellenőrzi a játék végét, azaz vagy nyertek a játékosok, vagy veszítettek
     */
    public void checkEndGame() {
        Skeleton.printLine("World", "checkEndGame()");
        if(Skeleton.askQuestion("Minden játékos egy mezőn áll? "))
            if(Skeleton.askQuestion("Minden alkatrész megvan?"))
                winGame();
        Skeleton.returned();
    }

    /**
     * a játék vége: nyertek a játékosok
     */
    public void winGame() {
        Skeleton.printLine("World", "winGame()");
        Skeleton.returned();
    }
    /**
     * Legenrálja a pályát alkotó táblákat
     */
    public void generateTiles() {
        Skeleton.printLine("World", "generateTiles()");
        Skeleton.returned();
    }

    /**
     * Hóvihar generálására szolgáló függvény
     */
    public void generateSnowstorm(Tile t) {
        Skeleton.printLine("World", "generateSnowstorm()");
        t.storm();
        Skeleton.returned();
    }

    /**
     * A megtalált rakétaegység beregisztrálására használatos függvény
     * @param fp a megtalált rész
     */
    public void registerFlarePart(FlarePart fp ) {
        Skeleton.printLine("World", "registerFlarePart()");
        flareParts.add(fp);
        Skeleton.returned();
    }

    /**
     * A játékosok vesztettek
     */
    public void loseGame() {
        Skeleton.printLine("World", "loseGame()");
        Skeleton.returned();
    }

}