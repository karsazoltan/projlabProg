package sumatra;

import graphics.BearTileChooserWindow;
import graphics.Command;
import graphics.SnowstormGeneratorWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Parancsértelmező osztály. A függvényei statikusak, kapnak egy-egy parancs sort, és értelmezik azt.
 */
public class Interpreter {

    /**
     * Játékos parancsértelmező
     * @param p Az éppen soron lévő játékos
     * @param cmd A felhasználó által kiadott parancs
     * @return Igaz, ha a kiadott parancs a finish volt
     */
    public static boolean interpretPlayerCommand(Player p, String cmd) {
        String[] words = cmd.trim().split(" ");
        if (words[0].length() == 0) return false;

        switch (words[0]) {
            case "move":
                try {
                    int idx = Integer.parseInt(words[1]);
                    System.out.println("> You moved to tile " + idx);
                    p.move(World.getInstance().getTileAt(idx));
                } catch (Exception e) {
                    System.out.println("> Error: Invalid syntax!");
                }
                break;
            case "dig":
                p.dig();
                break;
            case "inventory":
                ArrayList<UsableItem> items = p.getItems();
                int loop = 0;
                System.out.println("> Manually usable items:");
                for (Item i : items) {
                    i.printData(System.out, "    " + loop + ": ");
                }
                System.out.println("> Automatically used items:");
                    p.getDivingSuit().printData(System.out, "    ");
                    p.getRope().printData(System.out, "    ");
                break;
            case "use":
                try {
                    p.useItem(Integer.parseInt(words[1]), p.getTile());
                } catch (Exception e) {
                    System.out.println("> Error: Invalid item index!");
                }
                break;
            case "ability":
                p.useAbility(p.getTile());
                break;
            case "pickup":
                p.pickUpItem();
                break;
            case "buildflare":
                p.buildFlare();
                break;
            case "finish":
                World.getInstance().playerFinished();
                return true;
            default:
                return interpretGameplayCommand(cmd);
        }
        return false;
    }

    /**
     * Játék közbeni parancsértelmező.
     * @param cmd A felhasználó által kiadott parancs.
     * @return Igaz, ha a kiadott parancs a stop volt.
     */
    public static boolean interpretGameplayCommand(String cmd) {
        String[] words = cmd.trim().split(" ");
        if (words[0].length() == 0) return false;

        switch (words[0]) {
            case "tiles":
                World.getInstance().listTiles();
                break;
            case "snowstorm":
                World.getInstance().generateSnowstorm();
                break;
            case "step":
                try {
                    World.getInstance().step(Integer.parseInt(words[1]));
                } catch (Exception e) {
                    System.out.println("> Error: Invalid syntax!");
                }
                break;
            case "stop":
                World.getInstance().stop();
                return true;
            case "init":
            case "start":
                System.out.println("> Error: Invalid context for this command!");
                return false;
            default:
                return interpretBasicCommand(cmd);
        }
        return false;
    }

    /**
     * Általános parancsértelmező.
     * @param cmd A felhasználó által kiadott parancs.
     * @return Igaz, ha a kiadott parancs az exit volt.
     */
    public static boolean interpretBasicCommand(String cmd) {
        String[] words = cmd.trim().split(" ");
        if (words[0].length() == 0) return false;

        switch (words[0]) {
            case "init":
                switch (words[1]) {
                    case "world": World.getInstance().generateTiles(); break;
                    case "creatures": World.getInstance().generateCreatures(); break;
                    default:
                        System.out.println("> Error: Invalid argument!");
                }
                break;
            case "start":
                switch (words[1]) {
                    case "automated": World.getInstance().startGame(false); break;
                    case "managed": World.getInstance().startGame(true); break;
                    default:
                        System.out.println("> Error: Invalid argument!");
                }
                break;
            case "save": World.getInstance().saveConfig(cmd.trim().substring(5)); break;
            case "print": World.getInstance().printConfig(); break;
            case "load": World.getInstance().loadConfig(cmd.trim().substring(5)); break;
            case "exit": System.exit(0);
            default:
                System.out.println("> Error: Invalid command!");
        }
        return false;
    }

    /**
     * Globális parancsértelmező - a szövegalapú verzióban a World kezelte, hogy mikor melyik interpreter
     * függvény volt meghívva, viszont a grafikus verziónál a World állapotgépként funkcionál - itt az
     * állapot függvényében hívjuk a megfelelő parancsértelmezőt.
     * @param cmd Az értelmezendő parancs.
     */
    public static void interpretCommand(String cmd) {
        if (World.getInstance().isRunning()) {
            String activePlayer = World.getInstance().getActivePlayer();
            if (!activePlayer.equals("none"))
                interpretPlayerCommand((Player) World.getInstance().getCreatureAt(Integer.parseInt(activePlayer)), cmd);
            else
                interpretGameplayCommand(cmd);
        } else {
            interpretBasicCommand(cmd);
        }
    }

    /**
     * Visszatér a valid parancsok listájával.
     * @return A valid parancsok listája.
     */
    public static List<Command> validCommands() {
        if (World.getInstance().isRunning()) {
            String activePlayer = World.getInstance().getActivePlayer();
            if (!activePlayer.equals("none")) {
                Player active = (Player) World.getInstance().getCreatures().get(Integer.parseInt(activePlayer));
                ArrayList<Integer> tiles = new ArrayList<>();
                for (Tile t : active.getTile().getNeighbors()) {
                    tiles.add(World.getInstance().getTileIndex(t));
                }

                ArrayList<Integer> items = (ArrayList<Integer>) IntStream
                        .range(0, active.getItems().size()).boxed().collect(Collectors.toList());

                return Arrays.asList(
                        new Command("Move", "move", "Enter tile to move to: ", tiles),
                        new Command("Dig", "dig"),
                        new Command("Use manual tool", "use", "Enter tool to use: ", items),
                        new Command("Use character ability", "ability"),
                        new Command("Pick up item from current tile", "pickup"),
                        new Command("Build flare", "buildflare"),
                        new Command("Finish round", "finish"),
                        new Command("Stop game", "stop")
                );
            } else if (World.getInstance().isManaged()) {
                ArrayList<Integer> creatures = (ArrayList<Integer>) IntStream
                        .range(0, World.getInstance().getCreatures().size()).boxed().collect(Collectors.toList());


                return Arrays.asList(
                        new Command("Generate snowstorm", "snowstorm"),
                        new Command("Step creature", "step", "Enter creature index: ", creatures),
                        new Command("Stop game", "stop")
                );
            } else
                return Arrays.asList(
                        new Command("Stop game", "stop")
                );
        } else {
            if (World.getInstance().getCreatures().size() == 0)
                return Arrays.asList(
                        new Command("Quit application", "exit")
                );
            else
                return Arrays.asList(
                        new Command("Start managed game", "start managed"),
                        new Command("Start automated game", "start automated"),
                        new Command("Quit application", "exit")
                );
        }
    }

    /**
     * Medve-léptető delegáló függvény - A modell nem közvetlenül a grafikus felületre hivatkozik, hanem erre,
     * és cserébe ez az, ami meghívja a grafikus felület kezelőjét.
     * @param b A léptetendő medve
     * @param t A medve táblája
     */
    public static void requestBearMovement(Bear b, Tile t) {
        BearTileChooserWindow btcw = new BearTileChooserWindow(t);
        b.move(World.getInstance().getTileAt(btcw.showDialog()));
    }

    /**
     * Hóvihar-generáló delegáló függvény - A modell nem közvetlenül a grafikus felületre hivatkozik, hanem erre,
     * és cserébe ez az, ami meghívja a grafikus felület kezelőjét.
     * @param tileCount A jégtáblák száma a mezőn
     * @return Egy int-int összerendelés - melyik jégtáblán mennyi havat generáljunk
     */
    public static HashMap<Integer, Integer> requestSnowstormGeneration(int tileCount) {
        SnowstormGeneratorWindow sgw = new SnowstormGeneratorWindow(tileCount);
        return sgw.showDialog();
    }
}
