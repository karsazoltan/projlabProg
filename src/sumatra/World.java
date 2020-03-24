package sumatra;
import java.util.*;

/**
 * A játékot menedzselő singleton osztály. Kezeli a világot alkotó Tile mezőket, generálja a játékteret, illetve néha hóviharokat generál.
 */
public class World {
    /**
     * Mezók - Jaki még csinálja :)
     */
    private List<Tile> tiles;

    /**
     * A jelzőrakéta egy alkatrészét reprezentáló típus
     */
    private List<FlarePart> flareParts;

    /**
     * A játék indítására szolgáló függvény, körönként lehetőséget ad arra, hogy a játékosok lépjenek
     */
    public void startGame() {
        // TODO implement here
    }

    /**
     * Ellenőrzi a játék végét, azaz vagy nyertek a játékosok, vagy veszítettek
     */
    public void checkEndGame() {
        // TODO implement here
    }

    /**
     * Legenrálja a pályát alkotó táblákat
     */
    public void generateTiles() {
        // TODO implement here
    }

    /**
     * Hóvihar generálására szolgáló függvény
     */
    public void generateSnowstorm() {
        // TODO implement here
    }

    /**
     * A megtalált rakétaegység beregisztrálására használatos függvény
     * @param fp a megtalált rész
     */
    public void registerFlarePart(FlarePart fp ) {
        // TODO implement here
    }

    /**
     * A játékosok vesztettek
     */
    public void loseGame() {
        // TODO implement here
    }

}