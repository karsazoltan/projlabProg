package sumatra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    // TODO COMMENT, VAJON JÓ-E EZ ÍGY
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

    public static void generateTilesFrom(ArrayList<String> info, int tileCount, int linkCount) {
        // INPUT: stable 3 none / unstable 5 shovel 3 / type snow item [cap]
        World.getInstance().generateTilesFrom(info, tileCount, linkCount);
    }

    public static void generateCreaturesFrom(ArrayList<String> info) {
        // Itt bőven elég, ha soronként a következő: 0 polarbear / 1 researcher / tileidx creature
        World.getInstance().generateCreaturesFrom(info);
    }

    public static List<Command> validCommands() {
        if (World.getInstance().isRunning()) {
            String activePlayer = World.getInstance().getActivePlayer();
            if (!activePlayer.equals("none"))
                return Arrays.asList(
                        new Command("Move", "move", 1),
                        new Command("Dig", "dig", 0),
                        new Command("Use manual tool", "use", 1),
                        new Command("Use character ability", "ability", 0),
                        new Command("Pick up item from current tile", "pickup", 0),
                        new Command("Build flare", "buildflare", 0),
                        new Command("Finish round", "finish", 0)
                );
            else if (World.getInstance().isManaged())
                return Arrays.asList(
                        new Command("Generate snowstorm", "snowstorm", 0), // TODO EZT HOGY A FENÉBE
                        new Command("Step creature", "step", 1),
                        new Command("Stop game", "stop", 0)
                );
            else
                return Arrays.asList(
                        new Command("Stop game", "stop", 0)
                );
        } else {
            return Arrays.asList(
                    // TODO Vajon initek ide jöjjenek?
                    new Command("Start managed game", "start managed", 0),
                    new Command("Start automated game", "start automated", 0),
                    // TODO Vajon save / load ide jöjjön?
                    new Command("Quit application", "exit", 0)
            );
        }
    }
}
