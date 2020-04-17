package sumatra;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A játékot menedzselő singleton osztály. Kezeli a világot alkotó Tile mezőket, generálja a játékteret,
 * illetve néha hóviharokat generál.
 */
public class World implements Printable {
    /** A játékban résztvevő lények tömbje */
    private ArrayList<Creature> creatures;
    /** A játék során összegyűjtött jelzőrakéta-alkatrészek */
    private ArrayList<FlarePart> flareParts;
    /** Menedzselt mód-flag, csak akkor fontos, ha van futó játék */
    private boolean managedMode;
    /** Lépésszámláló, csak akkor fontos, ha van futó játék */
    private int stepCounter;
    /** Lerakott sátorok, azt tartja számon, hogy az adott sátor melyik körben lett lerakva */
    private HashMap<Tent, Integer> tentplacements;
    /** A játék tábláját alkotó jégtáblák tömbje */
    private ArrayList<Tile> tiles;
    /** Az inputfeldolgozó függvények által közösen használt scanner objektum. */
    private Scanner input;

    /**
     * Privát konstruktor - Mivel a World egy singleton, nem akarunk külsőleg példányt létrehozni
     * belőle. Viszont a tagváltozók inicializálására megfelelő.
     */
    private World() {
        creatures = new ArrayList<Creature>();
        flareParts = new ArrayList<>();
        tentplacements = new HashMap<>();
        tiles = new ArrayList<>();
        input = new Scanner(System.in);
    }

    /**
     * Singleton megvalósítás, a world példánya.
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
     * Ellenőrzi a játék végét, azaz vagy nyertek a játékosok, vagy veszítettek
     */
    public void checkEndGame() {
        if (flareParts.size() != 3) {
            System.out.println("> You don't have all 3 parts of the flare!");
            System.out.println("> Flare construction unsuccessful!");
            return;
        }
        System.out.println("> You have all 3 parts of the flare.");
        if (!true) {// TODO ELLENŐRIZNI, HOGY UGYANOTT ÁLL-E MINDENKI
            System.out.println("> Not all players are standing on the same tile!");
            System.out.println("> Flare construction unsuccessful!");
            return;
        }
        System.out.println("> All players are standing on the same tile.");
        System.out.println("> Flare construction successful!");
        System.out.println("> Congratulations, you won!");
        stop();
    }

    /**
     * Ciklusfüggvény, a játék menetét vezérli.
     */
    private void gameLoop() {
        boolean result = false;
        if (!managedMode) {
            while (!result) {
                /* TODO A World kezelhetné az exit conditionoket, meg lehetne azt, hogy csak a world
                    fér hozzá a System.in-hez, a creatureök nem :/ */
                creatures.get(stepCounter % creatures.size()).playRound();
                stepCounter++;
            }
        } else {
            while (!result && input.hasNextLine()) {
                String line = input.nextLine().trim();
                result = Interpreter.interpretGameplayCommand(line);
                // TODO A LÉPÉSKEZELÉS HOGY MŰKÖDIK ITT?
            }
        }
    }

    /**
     * Élőlény-generáló függvény, közvetlenül az inputtal dolgozik.
     */
    public void generateCreatures() {
        if (tiles.size() == 0) {
            System.out.println("    > Error: initialize a world before adding creatures!");
            return;
        }
        System.out.println("    > Enter creatures (<tile> <type> where tiles: 0-" + (tiles.size() - 1) + ",");
        System.out.println("    > types are researcher, eskimo, polarbear), or F to finish:");
        String line = "";
        int loop = 0;
        System.out.print("        0: ");
        line = (input.nextLine().trim().split(": "))[1];
        while (!line.equals("F")) {
            String[] words = line.split(" ");
            Creature c;
            Tile t;
            try {
                t = tiles.get(Integer.parseInt(words[0]));
                switch (words[1]) {
                    case "researcher": c = new Researcher(t); break;
                    case "eskimo": c = new Eskimo(t); break;
                    case "polarbear": c = new Bear(t); break;
                    default:
                        System.out.println("    > Error: Invalid type, skipping line!");
                        // TODO Ilyenkor nem jó a kövi sor beolvasása
                }
                t.accept(c);
                loop++;
            } catch (Exception e) {
                System.out.println("    > Error: Invalid tile ID, skipping line!");
            }

            System.out.print("        " + loop + ": ");
            /* TODO Vajon ide beszámít a sok sor eleji space, illetve a 0: rész?
                Elvben ez most jó, csak kicsit hack */
            line = (input.nextLine().trim().split(": "))[1];
        }
    }

    /**
     * Jégtábla-generáló függvény, közvetlenül az inputtal dolgozik.
     */
    public void generateTiles() {
        System.out.println("    > Enter tiles (<t> <s> [c] where t is S/U/H, s is # of");
        System.out.println("    > snow layers, c is capacity for U), or F to finish:");
        String line = "";
        int loop = 0;
        System.out.print("        0: ");
        line = (input.nextLine().trim().split(": "))[1];
        while (!line.equals("F")) {
            String[] words = line.split(" ");
            Tile t;
            int snow = 0, cap = 0;
            try {
                snow = Integer.parseInt(words[1]);
                if (words[0].equals("U"))
                    cap = Integer.parseInt(words[2]);
                switch(words[0]) {
                    case "S": t = new Tile(snow); break;
                    case "U": t = new UnstableTile(snow, cap); break;
                    case "H": t = new HoleTile(snow); break;
                }
                tiles.add(t);
                loop++;
            } catch (Exception e) {
                System.out.println("    > Error: Invalid syntax!");
            }
            System.out.print("        " + loop + ": ");
            line = (input.nextLine().trim().split(": "))[1];
        }

        System.out.println("    > Enter links (<a> <b>), or F to finish:");
        line = input.nextLine().trim();
        while (!line.equals("F")) {
            try {
                String[] words = line.split(" ");
                int idxa = Integer.parseInt(words[0]);
                int idxb = Integer.parseInt(words[1]);
                tiles.get(idxa).addNeighbor(tiles.get(idxb));
                tiles.get(idxb).addNeighbor(tiles.get(idxa));
            } catch (Exception e) {
                System.out.println("    > Error: Invalid tile IDs!");
            }
            line = (input.nextLine().trim());
        }

        System.out.println("    > Enter items to add (<tile> <item>), or F to finish:");
        line = input.nextLine().trim();
        while (!line.equals("F")) {
            String[] words = line.split(" ");
            try {
                Item i;
                switch (words[1]) {
                    case "basicdivingsuit": i = new BasicDivingSuit(); break;
                    case "basicrope": i = new BasicRope(); break;
                    case "beacon": i = new Beacon(); break;
                    case "brokenshovel": i = new BrokenShovel(); break;
                    case "cartridge": i = new Cartridge(); break;
                    case "food": i = new Food(); break;
                    case "gun": i = new Gun(); break;
                    case "shovel": i = new Shovel(); break;
                    case "tent": i = new TentEquipment(); break;
                    default:
                        System.out.println("    > Error: Invalid syntax!");
                        // TODO Ilyenkor nem kéne a placeItem lefusson. Exception dobása nagyon ronda
                }
                tiles.get(Integer.parseInt(words[0])).placeItem(i);
            } catch (Exception e) {
                System.out.println("    > Error: Invalid syntax!");
            }
            line = input.nextLine().trim();
        }
    }

    /**
     * Hóvihargeneráló függvény, közvetlenül az inputtal dolgozik.
     */
    public void generateSnowstorm() {
        HashMap<Integer, Integer> affectedTiles = new HashMap<>();
        if (!managedMode) {
            int amount = ThreadLocalRandom.current().nextInt(0, tiles.size());
            for (int i = 0; i < amount; i++) {
                int tile = ThreadLocalRandom.current().nextInt(0, tiles.size());
                int snow = ThreadLocalRandom.current().nextInt(1, 5);
                affectedTiles.put(tile, snow);
            }
        } else {
            System.out.println("    > Enter affected tiles and layers of snow to generate (<t> <#>),");
            System.out.println("    > or F to finish:");
            String line = input.nextLine().trim();
            while (!line.equals("F")) {
                try {
                    String[] words = line.split(" ");
                    int tile = Integer.parseInt(words[0]);
                    int snow = Integer.parseInt(words[1]);
                    affectedTiles.put(tile, snow);
                } catch (Exception e) {
                    System.out.println("    > Error: Invalid syntax!");
                }
                line = input.nextLine().trim();
            }
        }
        System.out.print("> Snowstorm! Tiles affected: ");

        for (Integer key : affectedTiles.keySet()) {
            try {
                Tile t = tiles.get(key);
                System.out.print(key + " ");
                t.addSnow(affectedTiles.get(key));
                t.storm();
            } catch (Exception ignored) {} // Csendes kivétel, elvben ilyen nem is történhet
        }
    }

    /**
     * Ciklusfüggvény, a játékon kívüli részt vezérli. A program belépőpontja a main után
     */
    public void initialLoop() {
        boolean result = false;
        while (!result && input.hasNextLine()) {
            String line = input.nextLine().trim();
            result = Interpreter.interpretBasicCommand(line);
            // TODO A LÉPÉSKEZELÉS HOGY MŰKÖDIK ITT?
        }
    }

    /**
     * Visszatér a globálisan index-edik indexű jégtáblával
     * @param index A jégtábla indexe
     * @return A jégtábla
     */
    public Tile getTileAt(int index) {
        return tiles.get(index);
    }

    public void listTiles() {
        // TODO
    }

    public void loadConfig(String filename) {
        // TODO
    }

    /**
     * Játék elvesztése függvény, kívülről hívható
     */
    public void loseGame() {
        System.out.println("You lost!");
        stop();
    }

    /**
     * Kiírja a standard outputra a pillanatnyi konfigurációt
     */
    public void printConfig() {
        printData(System.out);
    }

    /**
     * Regisztrálja a játékosok által megtalált flarepartot
     * @param fp A megtalált jelzőrakéta-alkatrész
     */
    public void registerFlarePart(FlarePart fp) {
        flareParts.add(fp);
    }

    /**
     * Regisztrálja a kapott sátrat, hogy egy kör letelte után lehessen neki szólni, hogy semmisítse meg magát
     * @param t A sátor
     */
    // TODO A megsemmisítés nincs implementálva
    public void registerTent(Tent t) {
        tentplacements.put(t, stepCounter);
    }

    /**
     * Elmenti a játék pillanatnyi konfigurációját a kapott fájlnévhez tartozó fájlba
     * @param filename A fájl neve
     */
    public void saveConfig(String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            printData(fos);
        } catch (Exception e) {
            System.out.println("> Error: Couldn't save config! Please try again!");
        }
    }

    /**
     * A játék indítására szolgáló függvény, körönként lehetőséget ad arra, hogy a játékosok lépjenek
     */
    public void startGame(boolean managed) {
        managedMode = managed;
        stepCounter = 0;
        gameLoop();
    }

    /**
     * Kiírja a megvalósító osztály adatait az átadott streamre, delegálja azokat a tartalmazott elemekre
     * @param stream ahova kiírjuk az adatokat
     */
    @Override
    public void printData(OutputStream stream) {
        // TODO
    }
}