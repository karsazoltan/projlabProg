package sumatra;

import java.util.*;

public class Skeleton {
    // TODO ez kell? Ne legyünk lusták :(
    private static void print(String s) {
        System.out.println(s);
    }

    /**
     * Játékos stabil jégtáblára lép szekvenciaindító függvény.
     */
    public static void PlayerStepsOnTile() {
        Tile tile1 = new Tile("tile1");
        Tile tile2 = new Tile("tile2");
        Eskimo e = new Eskimo(tile1, "eskimo");

        tile1.accept(e);
        tile1.addNeighbor(tile2);
        tile2.addNeighbor(tile1);

        e.move(tile2);
    }

    /**
     * Játékos instabil jégtáblára lép búvárruha nélkül szekvenciaindító függvény.
     */
    public static void PlayerStepsOnUnstableTileWithoutDivingSuit() {
        Tile tile1 = new Tile("tile1");
        UnstableTile tile2 = new UnstableTile("unstabletile");
        Eskimo e = new Eskimo(tile1, "eskimo");
        NoDivingSuit d = new NoDivingSuit("nodivinsuit");

        tile1.accept(e);
        tile1.addNeighbor(tile2);
        tile2.addNeighbor(tile1);
        e.addDivingSuit(d);

        e.move(tile2);
    }

    /**
     * Játékos instabil jégtáblára lép búvárruhával szekvenciaindító függvény.
     */
    public static void PlayerStepsOnUnstableTileWithDivingSuit() {
        Tile tile1 = new Tile("tile1");
        UnstableTile tile2 = new UnstableTile("unstabletile");
        Eskimo e = new Eskimo(tile1, "eskimo");
        BasicDivingSuit d = new BasicDivingSuit("basicdivinsuit");

        tile1.accept(e);
        tile1.addNeighbor(tile2);
        tile2.addNeighbor(tile1);
        e.addDivingSuit(d);

        e.move(tile2);
    }

    /**
     * Játékos lyukas jégtáblára lép búvárruha nélkül szekvenciaindító függvény.
     */
    public static void PlayerStepsOnHoleWithoutDivingSuit() {
        Tile tile1 = new Tile("tile1");
        HoleTile tile2 = new HoleTile("holetile");
        Eskimo e = new Eskimo(tile1, "eskimo");
        NoDivingSuit d = new NoDivingSuit("nodivinsuit");

        tile1.accept(e);
        tile1.addNeighbor(tile2);
        tile2.addNeighbor(tile1);
        e.addDivingSuit(d);

        e.move(tile2);
    }

    /**
     * Játékos lyukas jégtáblára lép búvárruhával szekvenciaindító függvény.
     */
    public static void PlayerStepsOnHoleWithDivingSuit() {
        Tile tile1 = new Tile("tile1");
        HoleTile tile2 = new HoleTile("holetile");
        Eskimo e = new Eskimo(tile1, "eskimo");
        BasicDivingSuit d = new BasicDivingSuit("basicdivinsuit");

        tile1.accept(e);
        tile1.addNeighbor(tile2);
        tile2.addNeighbor(tile1);
        e.addDivingSuit(d);

        e.move(tile2);
    }

    /**
     * Eszkimó használja a képességét nem lyukon szekvenciaindító függvény.
     */
    public static void EskimoAbilityNoHole() {
        Tile tile = new Tile("t");
        Eskimo e = new Eskimo(tile, "e" );

        tile.accept(e);
        e.useAbility(tile);


    }

    /**
     * Eszkimó használja a képességét lyukon szekvenciaindító függvény.
     */
    public static void EskimoAbilityOnHole() {
        Tile tile = new HoleTile("tile");
        Eskimo e = new Eskimo(tile, "e" );

        tile.accept(e);
        e.useAbility(tile);
    }

    /**
     * Sarkkutató használja a képességét szekvenciaindító függvény.
     */
    public static void ResearcherAbility() {
        Tile tile = new Tile("tile");
        Researcher r = new Researcher(tile, "r" );
        tile.accept(r);
        r.useAbility(tile);
    }


    /**
     * Hóvihart generál a pályán.
     * Megjegyzés: prezentációs célokból a World generateSnowstorm függvényének
     * paraméterként adjuk át a tile-t.
     */
    public static void SnowstormNoBuilding() {
        Tile t = new Tile("t");
        Eskimo e = new Eskimo(t, "e");
        t.accept(e);
        World.getInstance().generateSnowstorm(t);
    }

    /**
     * Hóvihart generál a pályán és érint egy igluval felszerelt Tile-t.
     * Megjegyzés: prezentációs célokból a World generateSnowstorm függvényének
     * paraméterként adjuk át a tile-t.
     */
    public static void SnowstormIgloo() {
        Tile t = new Tile("t");
        t.buildIgloo();
        World.getInstance().generateSnowstorm(t);
    }

    public static void PickUpFood() {

    }

    public static void PickUpBasicRope() {

    }

    public static void PickUpBasicDivingSuit() {

    }

    public static void PickUpShovel() {

    }

    public static void PickUpGun() {

    }

    public static void PickUpCartridge() {

    }

    public static void PickUpBeacon() {

    }

    /**
     * Jelzőrakéta összeszerelése szekvenciaindító függvény
     */
    public static void FlareAssembly() {
        Eskimo eskimo = new Eskimo(null, "eskimo");

        eskimo.buildFlare();
    }

    /**
     * Player ásóval ás szekvenciaindító függvény
     */
    public static void PlayerDigsWithShovel() {
        Tile tile = new Tile("tile");
        Eskimo eskimo = new Eskimo(tile, "eskimo");
        Shovel shovel = new Shovel("shovel");
        eskimo.addUsableItem(shovel);
        tile.accept(eskimo);

        eskimo.useItem(0, tile); // TODO Ide kell a tile?

    }

    /**
     * Player kézzel ás szekvenciaindító függvény
     */
    public static void PlayerDigsWithHands() {
        Tile tile = new Tile("tile");
        Eskimo eskimo = new Eskimo(tile, "eskimo");
        tile.accept(eskimo);

        eskimo.dig();
    }

    /**
     * Kimentési kísérlet kötél nélkül.
     */
    public static void SaveNoRope() {
        Tile t1 = new Tile("t1");
        Tile t2 = new Tile("t2");
        Eskimo p1 = new Eskimo(t1, "p1");
        Eskimo p = new Eskimo(t2, "p");
       
        //setup
        t2.accept(p);
        t1.accept(p1);
        t1.addNeighbor(t2);
        t2.addNeighbor(t1);

        p1.fallInWater();
    }

    public static void SaveBasicRope() {
        Tile t1 = new Tile("t1");
        Tile t2 = new Tile("t2");
        Eskimo p1 = new Eskimo(t1, "p1");
        Eskimo p = new Eskimo(t2, "p");
        p.addRope(new BasicRope("br"));
       
        //setup
        t2.accept(p);
        t1.accept(p1);
        t1.addNeighbor(t2);
        t2.addNeighbor(t1);

        p1.fallInWater();
    }

    public static void ListFunctions() {
        print(" 1 - Player steps on stable tile");
        print(" 2 - Player steps on unstable tile without divingsuit");
        print(" 3 - Player steps on unstable tile with divingsuit.");
        print(" 4 - Player steps on hole without divingsuit");
        print(" 5 - Player steps on hole with divingsuit");
        print(" 6 - Eskimo uses special ability.");
        print(" 7 - Eskimo uses special ability on a hole.");
        print(" 8 - Researcher uses special ability.");
        print(" 9 - Snowstorm affects a tile without building.");
        print("10 - Snowstorm affects a tile with an igloo on it.");
        print("11 - Player picks up food.");
        print("12 - Player picks up rope.");
        print("13 - Player picks up divingsuit.");
        print("14 - Plater picks up shovel.");
        print("15 - Player picks up gun.");
        print("16 - Player picks up cartridge.");
        print("17 - Player picks up beacon.");
        print("18 - Players assemble the flare.");
        print("19 - Player digs with a shovel.");
        print("20 - Player digs with hands.");
        print("21 - Player asks for help but doesn't get saved.");
        print("22 - Player gets saved by another player.");
    }

    public static void main(String[] args) throws Exception {
        Map<Integer, Runnable> commands = new HashMap<>();

        commands.put(0, () -> System.exit(0));
        commands.put(1, () -> PlayerStepsOnTile());
        commands.put(2, () -> PlayerStepsOnUnstableTileWithoutDivingSuit());
        commands.put(3, () -> PlayerStepsOnUnstableTileWithDivingSuit());
        commands.put(4, () -> PlayerStepsOnHoleWithoutDivingSuit());
        commands.put(5, () -> PlayerStepsOnHoleWithDivingSuit());
        commands.put(6, () -> EskimoAbilityNoHole());
        commands.put(7, () -> EskimoAbilityOnHole());
        commands.put(8, () -> ResearcherAbility());
        commands.put(9, () -> SnowstormNoBuilding());
        commands.put(10, () -> SnowstormIgloo());
        commands.put(11, () -> PickUpFood());
        commands.put(12, () -> PickUpBasicRope());
        commands.put(13, () -> PickUpBasicDivingSuit());
        commands.put(14, () -> PickUpShovel());
        commands.put(15, () -> PickUpGun());
        commands.put(16, () -> PickUpCartridge());
        commands.put(17, () -> PickUpBeacon());
        commands.put(18, () -> FlareAssembly());
        commands.put(19, () -> PlayerDigsWithShovel());
        commands.put(20, () -> PlayerDigsWithHands());
        commands.put(21, () -> SaveNoRope());
        commands.put(22, () -> SaveBasicRope());
        commands.put(23, () -> ListFunctions()); // easter egg list...
       
        Scanner scanner = new Scanner(System.in);
        String input;

        ListFunctions();
        while (true) {
            System.out.print("Choose one of the options above (0 to exit): ");
            input = scanner.next();
            try {
                int idx = Integer.parseInt(input);
                if (idx <= 23 && idx >= 0) {
                    if (idx == 0) scanner.close();
                    commands.get(idx).run();
                }
            } catch(Exception e) {
                print("Must be an integer value between 0 and 22!");
            }
        }
    }

    static int depth = 0;
    public static void printLine(String objectName, String methodName) {
        for (int i = 0; i < depth; i++)
            System.out.print("\t");
        System.out.println(objectName + "." + methodName);
        depth++;
    }

    public static void returned() {
        if (depth <= 0) {
            System.out.println("Hiba: Elrontotta valaki a returned() használatát, negatív mélység van :(");
        }
        depth--;
    }

    public static boolean askQuestion(String question) {
        System.out.print(question + " (I/N) ");
        Scanner sc = new Scanner(System.in);
        String result = sc.next().trim();
        return (result.equals("I") || result.equals("i"));
    }

    public static void startInit() {
        System.out.println("Inicializálás:");
    }

    public static void startSequence() {
        System.out.println("\nSzekvencia:");
    }
}