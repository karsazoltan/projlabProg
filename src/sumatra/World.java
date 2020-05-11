package sumatra;
import graphics.IView;
import graphics.IViewable;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A játékot menedzselő singleton osztály. Kezeli a világot alkotó Tile mezőket, generálja a játékteret,
 * illetve néha hóviharokat generál.
 */
public class World implements Printable, IViewable {
    /** A játékban résztvevő lények tömbje */
    private ArrayList<Creature> creatures;
    /** A játék során összegyűjtött jelzőrakéta-alkatrészek */
    private ArrayList<FlarePart> flareParts;
    /** Menedzselt mód-flag, csak akkor fontos, ha van futó játék */
    private boolean managedMode;
    /** Játék futása flag, igaz, ha éppen fut egy játék */
    private boolean running;
    /** Lépésszámláló, csak akkor fontos, ha van futó játék */
    private int stepCounter;
    /** Lerakott sátorok, azt tartja számon, hogy az adott sátor melyik körben lett lerakva */
    private HashMap<Tent, Integer> tentplacements;
    /** A játék tábláját alkotó jégtáblák, és linkek tömbje */
    private ArrayList<Tile> tiles;
    /** Az inputfeldolgozó függvények által közösen használt scanner objektum. */
    private Scanner input;
    /** Az aktív játékos jelzője */
    private String activeplayer;

    /**
     * Privát konstruktor - Mivel a World egy singleton, nem akarunk külsőleg példányt létrehozni
     * belőle. Viszont a tagváltozók inicializálására megfelelő.
     */
    private World() {
        creatures = new ArrayList<>();
        flareParts = new ArrayList<>();
        tentplacements = new HashMap<>();
        tiles = new ArrayList<>();
        input = new Scanner(System.in);
        running = false;
        activeplayer = "none";
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

        boolean sameTile = true;
        Tile t = null;
        for (Creature c : creatures) {
            if (t == null)
                t = c.getTile();
            else
                if (c.getTile() != null && c.getTile() != t)
                    sameTile = false;
        }

        if (!sameTile) {
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
     * Ellenőrzi, hogy az adott lépésben kell-e megsemmisíteni sátrat
     */
    private void checkTentDecays() {
        Iterator<Tent> iter = tentplacements.keySet().iterator();
        while (iter.hasNext()) {
            Tent t = iter.next();
            if (tentplacements.get(t) + creatures.size() == stepCounter) {
                tentplacements.remove(t);
                t.destroy();
            }
        }
    }

    /** Tárolja az utolsó hóvihar bekövetkeztét */
    private int nextstormstep = -1;

    /**
     * A World a grafikus felület hozzáadásával végtelen ciklusrendszer helyett egy állapotgéppé vált -
     * Ez a függvény ezt az állapotgépet lépteti
     */
    public void advanceGame() {
        // Ha load történt, akkor történhet ilyen
        if (!activeplayer.equals("none")) {
            try {
                int idx = Integer.parseInt(activeplayer);
                if (idx < creatures.size()) {
                    step(idx);
                    return;
                }
            } catch (Exception ignored) {} // Ha invalid a creature id, ne bajlódjunk vele, induljon rendesen a játék
        }

        if (!running) return;
        if (!managedMode) {
            if (nextstormstep == stepCounter) {
                generateSnowstorm();
            }
            if (nextstormstep <= stepCounter) {
                nextstormstep += ThreadLocalRandom.current().nextInt(1, 3 * creatures.size());
            }
            step(stepCounter % creatures.size());
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

        creatures.clear();
        for (Tile t : tiles) {
            t.clearCreatures();
        }

        System.out.println("    > Enter creatures (<tile> <type> where tiles: 0-" + (tiles.size() - 1) + ",");
        System.out.println("    > types are researcher, eskimo, polarbear), or F to finish:");
        int loop = 0;
        System.out.print("        0: ");
        String line = input.nextLine().trim();
        while (!line.equals("F")) {
            String[] words = line.split(" ");
            Creature c = null;
            Tile t;
            try {
                boolean skipinit = false;
                t = tiles.get(Integer.parseInt(words[0]));
                switch (words[1]) {
                    case "researcher": c = new Researcher(t, creatures.size()); break;
                    case "eskimo": c = new Eskimo(t, creatures.size()); break;
                    case "polarbear": c = new Bear(t, creatures.size()); break;
                    default:
                        System.out.println("    > Error: Invalid type, skipping line!");
                        skipinit = true;
                }
                if (!skipinit) {
                    t.accept(c);
                    creatures.add(c);
                    loop++;
                }
            } catch (Exception e) {
                System.out.println("    > Error: Invalid tile ID, skipping line!");
            }

            System.out.print("        " + loop + ": ");
            line = input.nextLine().trim();
        }
        System.out.println("    > Creature Initialization finished!");
    }

    /**
     * Létrehoz lényeket a kapott input tömbből
     * @param list Tömb, soronként egy-egy élőlény információival
     */
    public void generateCreaturesFrom(ArrayList<String> list) {
        creatures.clear();

        for (Tile t : tiles) {
            t.clearCreatures();
        }

        for (int i = 0; i < list.size(); i++) {
            String[] words = list.get(i).trim().split(" ");
            Tile t = tiles.get(Integer.parseInt(words[0]));
            Creature c = null;
            switch (words[1]) {
                case "researcher": c = new Researcher(t, creatures.size()); break;
                case "eskimo": c = new Eskimo(t, creatures.size()); break;
                case "polarbear": c = new Bear(t, creatures.size()); break;
            }
            t.accept(c);
            creatures.add(c);
        }
    }

    /**
     * Jégtábla-generáló függvény, közvetlenül az inputtal dolgozik.
     */
    public void generateTiles() {
        System.out.println("    > Enter tiles (<t> <s> [c] where t is S/U/H, s is # of");
        System.out.println("    > snow layers, c is capacity for U), or F to finish:");
        int loop = 0;
        System.out.print("        0: ");
        String line = input.nextLine().trim();
        while (!line.equals("F")) {
            String[] words = line.split(" ");
            Tile t = null;
            int snow, cap = 0, id;
            try {
                boolean skipinit = false;
                snow = Integer.parseInt(words[1]);
                id = Integer.parseInt(words[0]);
                if (words[0].equals("U"))
                    cap = Integer.parseInt(words[2]);
                switch(words[0]) {
                    case "S": t = new Tile(id, snow); break;
                    case "U": t = new UnstableTile(id, snow, cap); break;
                    case "H": t = new HoleTile(id, snow); break;
                    default:
                        System.out.println("> Error: Invalid tile type");
                        skipinit = true;
                }
                if (!skipinit) {
                    tiles.add(t);
                    loop++;
                }
            } catch (Exception e) {
                System.out.println("    > Error: Invalid syntax!");
            }
            System.out.print("        " + loop + ": ");
            line = input.nextLine().trim();
        }

        System.out.print("    > Enter links (<a> <b>), or F to finish:\n        ");
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
            System.out.print("        ");
            line = (input.nextLine().trim());
        }

        System.out.print("    > Enter items to add (<tile> <item>), or F to finish:\n        ");
        line = input.nextLine().trim();
        while (!line.equals("F")) {
            String[] words = line.split(" ");
            try {
                Item i = null;
                boolean skipinit = false;
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
                        skipinit = true;
                }
                if (!skipinit)
                    if (!tiles.get(Integer.parseInt(words[0])).placeItem(i)) {
                        System.out.println("    > Error: Couldn't place item!");
                    }
            } catch (Exception e) {
                System.out.println("    > Error: Invalid syntax!");
            }
            System.out.print("        ");
            line = input.nextLine().trim();
        }
        System.out.println("    > World Initialization finished!");
    }

    /**
     * Létrehoz jégtáblákat a kapott input tömbből.
     * @param list Tömb, elemenként egy jégtábla információival, vagy egy linkkel.
     * @param tileCount Az első hány elem jégtáblaleíró
     * @param linkCount Az utolsó hány elem linkleíró
     */
    public void generateTilesFrom(ArrayList<String> list, int tileCount, int linkCount) {
        tiles.clear();
        flareParts.clear();
        creatures.clear();
        updateViews();

        for (int i = 0; i < tileCount; i++) {
            String[] words = list.get(i).trim().split(" ");
            int snow = 0, cap = 0;
            Tile t = null;
            snow = Integer.parseInt(words[1]);
            if (words[0].equals("U"))
                cap = Integer.parseInt(words[3]);
            switch(words[0]) {
                case "S": t = new Tile(i, snow); break;
                case "U": t = new UnstableTile(i, snow, cap); break;
                case "H": t = new HoleTile(i, snow); break;
            }
            Item item = null;
            switch (words[2]) {
                case "divingsuit": item = new BasicDivingSuit(); break;
                case "rope": item = new BasicRope(); break;
                case "beacon": item = new Beacon(); break;
                case "brokenshovel": item = new BrokenShovel(); break;
                case "cartridge": item = new Cartridge(); break;
                case "food": item = new Food(); break;
                case "gun": item = new Gun(); break;
                case "shovel": item = new Shovel(); break;
                case "tent": item = new TentEquipment(); break;
            }
            if (!words[2].equals("none")) {
                assert t != null;
                t.placeItem(item);
            }
            tiles.add(t);
        }

        for (int i = tileCount; i < tileCount + linkCount; i++) {
            String[] words = list.get(i).trim().split(" ");
            int idxa = Integer.parseInt(words[0]);
            int idxb = Integer.parseInt(words[1]);
            tiles.get(idxa).addNeighbor(tiles.get(idxb));
            tiles.get(idxb).addNeighbor(tiles.get(idxa));
        }
    }

    /**
     * Hóvihargeneráló függvény, közvetlenül az inputtal dolgozik.
     */
    public void generateSnowstorm() {
        HashMap<Integer, Integer> affectedTiles = new HashMap<>();
        if (!managedMode) {
            int amount = ThreadLocalRandom.current().nextInt(1, tiles.size() + 1);
            for (int i = 0; i < amount; i++) {
                int tile = ThreadLocalRandom.current().nextInt(0, tiles.size());
                int snow = ThreadLocalRandom.current().nextInt(1, 5);
                affectedTiles.put(tile, snow);
            }
        } else {
            affectedTiles = Interpreter.requestSnowstormGeneration(tiles.size());
        }

        System.out.print("> Snowstorm! Tiles affected: ");
        for (Integer key : affectedTiles.keySet()) {
            System.out.print(key + " ");
        }
        
        System.out.println();

        for (Integer key : affectedTiles.keySet()) {
            try {
                Tile t = tiles.get(key);
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
        }
    }

    /**
     * Visszatér azzal, hogy hányadik lépésben lett lerakva a sátor (a kiíratáshoz kell).
     * @param t A kérdéses sátor
     * @return Az, hogy hányadik lépésben lett lerakva.
     */
    public int getTentPlacementStep(Tent t) {
        return tentplacements.get(t);
    }

    /**
     * Visszatér a globálisan index-edik indexű jégtáblával
     * @param index A jégtábla indexe
     * @return A jégtábla
     */
    public Tile getTileAt(int index) {
        return tiles.get(index);
    }

    /**
     * Visszatér a jégtábla globális indexével.
     * @param t A kérdéses jégtábla, mint Tile
     * @return A kérdéses jégtábla indexe.
     */
    public int getTileIndex(Tile t) {
        return tiles.indexOf(t);
    }

    /**
     * A tiles parancsra adott válaszért felelős függvény. Kiíratja az összes tile-lal, és creature-rel
     * a hozzájuk tartozó, róluk ismert adatokat.
     */
    public void listTiles() {
        System.out.println("> Tile list (? if cap unknown, S/U (cap)/H if known):");
        for (Tile t : tiles) {
            t.listGameInfo();
        }
        for (Creature c : creatures) {
            c.listGameInfo();
        }
    }

    /** Betöltéskor használjuk, hogy ne veszítsük el a körszámlálót */
    private int loadedStepCounter = 0;

    /**
     * Betölt egy konfigurációt a kapott fájlból
     * @param filename A konfigurációt tartalmazó fájl neve.
     */
    public void loadConfig(String filename) {
        running = false;

        tiles.clear();
        creatures.clear();
        flareParts.clear();
        updateViews();

        FileReader fis;
        BufferedReader br = new BufferedReader(Reader.nullReader()); // Java 11 sajátosság, de cserébe szép lesz a finally
        try {
            fis = new FileReader(filename);
            br = new BufferedReader(fis);
            if (!br.readLine().trim().equals("worlddata"))
                throw new InputMismatchException("File does not start with \"worlddata\"");
            loadedStepCounter = Integer.parseInt(br.readLine().trim().split(" ")[1]);
            activeplayer = br.readLine().trim().split(" ")[1];
            int fps = Integer.parseInt(br.readLine().trim().split(" ")[1]);
            for (int i = 0; i < fps; i++) {
                String name = br.readLine().trim();
                switch (name) {
                    case "beacon": flareParts.add(new Beacon()); break;
                    case "cartridge": flareParts.add(new Cartridge()); break;
                    case "gun": flareParts.add(new Gun()); break;
                    default: throw new InputMismatchException("Invalid Flare Part name: \"" + name + "\"");
                }
            }
            int tilenum = Integer.parseInt(br.readLine().trim().split(" ")[1]);
            for (int i = 0; i < tilenum; i++) {
                Tile t = Tile.fromConfig(br);
                tiles.add(t);
            }
            int tilelinks = Integer.parseInt(br.readLine().trim().split(" ")[1]);
            for (int i = 0; i < tilelinks; i++) {
                String[] words = br.readLine().trim().split(" ");
                int tilea = Integer.parseInt(words[0]);
                int tileb = Integer.parseInt(words[1]);
                tiles.get(tilea).addNeighbor(tiles.get(tileb));
                tiles.get(tileb).addNeighbor(tiles.get(tilea));
            }
            int creatnum = Integer.parseInt(br.readLine().trim().split(" ")[1]);
            for (int i = 0; i < creatnum; i++) {
                Creature c = Creature.fromConfig(br);
                creatures.add(c);
            }
            System.out.println("    > World loaded successfully!");
        } catch (Exception e) {
            System.out.println("> Error: Could not load savefile. The file either doesn't exist," +
                    "or it's not a proper save file.");
            System.out.println("> Cause: " + e.getMessage());
        } finally {
            try {
                br.close();
            } catch (Exception ignored) {}
        }
    }

    /**
     * Játék elvesztése függvény, kívülről hívható
     */
    public void loseGame() {
        System.out.println("> You lost!");
        stop();
    }

    /**
     * Kiírja a standard outputra a pillanatnyi konfigurációt
     */
    public void printConfig() {
        System.out.println("    > Printing world to standard output...");
        printData(System.out, "");
    }

    /**
     * Regisztrálja a játékosok által megtalált flarepartot
     * @param fp A megtalált jelzőrakéta-alkatrész
     */
    public void registerFlarePart(FlarePart fp) {
        flareParts.add(fp);
        updateViews();
    }

    /**
     * Regisztrálja a kapott sátrat, hogy egy kör letelte után lehessen neki szólni, hogy semmisítse meg magát
     * @param t A sátor
     */
    public void registerTent(Tent t) {
        tentplacements.put(t, stepCounter);
    }
    /**
     * Regisztrálja a kapott sátrat, hogy egy kör letelte után lehessen neki szólni, hogy semmisítse meg magát,
     * csak most adott lépésszámmal.
     * @param t A sátor
     */
    public void registerTent(Tent t, int step) {
        tentplacements.put(t, step);
    }

    /**
     * Elmenti a játék pillanatnyi konfigurációját a kapott fájlnévhez tartozó fájlba
     * @param filename A fájl neve
     */
    public void saveConfig(String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            printData(fos, "");
            fos.close();
            System.out.println("    > World saved successfully!");
        } catch (Exception e) {
            System.out.println("    > Error: Couldn't save config! Please try again!");
        }
    }

    /**
     * A játék indítására szolgáló függvény, körönként lehetőséget ad arra, hogy a játékosok lépjenek
     */
    public void startGame(boolean managed) {
        managedMode = managed;
        stepCounter = loadedStepCounter;
        loadedStepCounter = 0;
        running = true;
        System.out.println("> Game Started");
        advanceGame();
    }

    /**
     * Lépésindító függvény, lejátszik egy lépést az idx-edik lény
     * @param idx A léptetendő creature indexe
     */
    public void step(int idx) {
        stepCounter++;
        checkTentDecays();
        if (idx >= creatures.size()) {
            System.out.println("> Error: Invalid creature index!");
            return;
        }
        activeplayer = Integer.toString(idx);
        if( managedMode )
            creatures.get(idx).playManagedRound();
        else
            creatures.get(idx).playRound();
    }

    /**
     * Játék-leállító függvény.
     */
    public void stop() {
        if (running)
            System.out.println("> Game Stopped.");
        running = false;
        activeplayer = "none";

    }

    /**
     * Kiírja a megvalósító osztály adatait az átadott streamre, delegálja azokat a tartalmazott elemekre
     * @param stream ahova kiírjuk az adatokat
     */
    @Override
    public void printData(OutputStream stream, String prefix) {
        PrintWriter pw = new PrintWriter(stream);

        pw.write("worlddata\n");
        pw.write("    step " + stepCounter + "\n");
        pw.write("    activeplayer " + activeplayer + "\n");
        pw.write("    flareparts " + flareParts.size() + "\n");
        pw.flush();
        for (FlarePart fp : flareParts) {
            fp.printData(stream, "        ");
        }

        pw.write("tiles " + tiles.size() + "\n");
        pw.flush();
        for (Tile t : tiles) {
            t.printData(stream, "    ");
        }

        ArrayList<Integer> links = new ArrayList<>();
        for (int i = 0; i < tiles.size(); i++) {
            ArrayList<Tile> nbs = tiles.get(i).getNeighbors();
            for (Tile t : nbs) {
                int j = getTileIndex(t);
                if (j > i) {
                    links.add(i); links.add(j);
                }
            }
        }
        pw.write("tilelinks " + links.size() / 2 + "\n");
        for (int i = 0; i < links.size(); i += 2) {
            pw.write("    " + links.get(i) + " " + links.get(i + 1) + "\n");
        }

        pw.write("creatures " + creatures.size() + "\n");
        pw.flush();
        for (Creature c : creatures) {
            c.printData(stream, "    ");
        }

        pw.flush();
    }

    /**
     * Visszatér a World input szkennerével - nem szerencsés több szkennert tenni az STDIN-re
     * @return Az input szkennere.
     */
    Scanner getInputScanner(){
        return input;
    }

    /**
     * Visszatér azzal, hogy fut-e egy játék.
     * @return Fut-e éppen játék
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Visszatér az aktív játékossal, "none", ha nincs ilyen
     * @return Az aktív játékos
     */
    public String getActivePlayer() {
        return activeplayer;
    }

    /**
     * Visszatér az index-edik élőlénnyel.
     * @param index Az élőlény sorszáma
     * @return Az élőlény
     */
    public Creature getCreatureAt(int index) {
        return creatures.get(index);
    }

    /**
     * Visszatér azzal, hogy menedzselt-e az aktív játék. Irreleváns, ha nem fut épp játék
     * @return Menedzselt-e a játék
     */
    public boolean isManaged() { return managedMode; }

    /**
     * Visszaadja a jelenleg megszerzet jelzőrakéta darabok neveit.
     * @return a begyűjtött darabok nevei egy String listában.
     */
    public ArrayList<String> getFlarepartNames(){
        ArrayList<String> names = new ArrayList<>();
        for( FlarePart fp : flareParts ){
            names.add(fp.toString());
        }
        return names;
    }

    /**
     * Visszaadja a lényeket a játékban.
     * @return a lények egy listában.
     */
    public ArrayList<Creature> getCreatures(){
        return creatures;
    }

    /**
     * Visszaadja a játékban levő mezők számát.
     * @return a játékban levő mezők számát.
     */
    public int getTileCount() {
        return tiles.size();
    }

    /**
     * Befejezi egy játékos a körét.
     */
    public void playerFinished() {
        activeplayer = "none";
        advanceGame();
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