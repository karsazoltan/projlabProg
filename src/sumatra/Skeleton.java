package sumatra;

import java.util.*;

public class Skeleton {
    /**
     * Játékos stabil jégtáblára lép szekvenciaindító függvény.
     */
    public static void PlayerStepsOnTile() {
        startInit();
        Tile tile1 = new Tile("tile1");
        Tile tile2 = new Tile("tile2");
        Eskimo e = new Eskimo(tile1, "eskimo");

        tile1.accept(e);
        tile1.addNeighbor(tile2);
        tile2.addNeighbor(tile1);

        startSequence();
        e.move(tile2);
    }

    /**
     * Játékos instabil jégtáblára lép búvárruha nélkül szekvenciaindító függvény.
     */
    public static void PlayerStepsOnUnstableTileWithoutDivingSuit() {
        startInit();
        Tile tile1 = new Tile("tile1");
        UnstableTile tile2 = new UnstableTile("unstabletile");
        Eskimo e = new Eskimo(tile1, "eskimo");
        NoDivingSuit d = new NoDivingSuit("nodivingsuit");

        tile1.accept(e);
        tile1.addNeighbor(tile2);
        tile2.addNeighbor(tile1);
        e.addDivingSuit(d);

        startSequence();
        e.move(tile2);
    }

    /**
     * Játékos instabil jégtáblára lép búvárruhával szekvenciaindító függvény.
     */
    public static void PlayerStepsOnUnstableTileWithDivingSuit() {
        startInit();
        Tile tile1 = new Tile("tile1");
        UnstableTile tile2 = new UnstableTile("unstabletile");
        Eskimo e = new Eskimo(tile1, "eskimo");
        BasicDivingSuit d = new BasicDivingSuit("basicdivingsuit");

        tile1.accept(e);
        tile1.addNeighbor(tile2);
        tile2.addNeighbor(tile1);
        e.addDivingSuit(d);

        startSequence();
        e.move(tile2);
    }

    /**
     * Játékos lyukas jégtáblára lép búvárruha nélkül szekvenciaindító függvény.
     */
    public static void PlayerStepsOnHoleWithoutDivingSuit() {
        startInit();
        Tile tile1 = new Tile("tile1");
        HoleTile tile2 = new HoleTile("holetile");
        Eskimo e = new Eskimo(tile1, "eskimo");
        NoDivingSuit d = new NoDivingSuit("nodivingsuit");

        tile1.accept(e);
        tile1.addNeighbor(tile2);
        tile2.addNeighbor(tile1);
        e.addDivingSuit(d);

        startSequence();
        e.move(tile2);
    }

    /**
     * Játékos lyukas jégtáblára lép búvárruhával szekvenciaindító függvény.
     */
    public static void PlayerStepsOnHoleWithDivingSuit() {
        startInit();
        Tile tile1 = new Tile("tile1");
        HoleTile tile2 = new HoleTile("holetile");
        Eskimo e = new Eskimo(tile1, "eskimo");
        BasicDivingSuit d = new BasicDivingSuit("basicdivingsuit");

        tile1.accept(e);
        tile1.addNeighbor(tile2);
        tile2.addNeighbor(tile1);
        e.addDivingSuit(d);

        startSequence();
        e.move(tile2);
    }

    /**
     * Eszkimó használja a képességét nem lyukon szekvenciaindító függvény.
     */
    public static void EskimoAbilityNoHole() {
        startInit();
        Tile tile = new Tile("tile");
        Eskimo e = new Eskimo(tile, "eskimo" );
        tile.accept(e);

        startSequence();
        e.useAbility(tile);
    }

    /**
     * Eszkimó használja a képességét lyukon szekvenciaindító függvény.
     */
    public static void EskimoAbilityOnHole() {
        startInit();
        Tile tile = new HoleTile("tile");
        Eskimo e = new Eskimo(tile, "eskimo" );

        startSequence();
        e.useAbility(tile);
    }

    /**
     * Sarkkutató használja a képességét szekvenciaindító függvény.
     */
    public static void ResearcherAbility() {
        startInit();
        Tile tile = new Tile("tile");
        Researcher r = new Researcher(tile, "researcher" );
        tile.accept(r);

        startSequence();
        r.useAbility(tile);
    }


    /**
     * Hóvihart generál a pályán.
     * Megjegyzés: prezentációs célokból a World generateSnowstorm függvényének
     * paraméterként adjuk át a tile-t.
     */
    public static void SnowstormNoBuilding() {
        Tile t = new Tile("tile");
        Eskimo e = new Eskimo(t, "eskimo");
        startInit();
        t.accept(e);
        startSequence();
        World.getInstance().generateSnowstorm(t);
    }

    /**
     * Hóvihart generál a pályán és érint egy igluval felszerelt Tile-t.
     * Megjegyzés: prezentációs célokból a World generateSnowstorm függvényének
     * paraméterként adjuk át a tile-t.
     */
    public static void SnowstormIgloo() {
        Tile t = new Tile("tile");
        startInit();
        t.buildIgloo();
        startSequence();
        World.getInstance().generateSnowstorm(t);
    }

    /**
     * Étel felvétele a mezőről
     */
    public static void PickUpFood() {
        startInit();
        Tile tile = new Tile("tile");
        Eskimo eskimo = new Eskimo(tile, "eskimo");
        Food food = new Food("food");
        tile.placeItem(food);
        tile.accept(eskimo);

        startSequence();
        eskimo.pickUpItem();
    }

    /**
     * Kötél felvétele a mezőről
     */
    public static void PickUpBasicRope() {
        startInit();
        Tile tile = new Tile("tile");
        Eskimo eskimo = new Eskimo(tile, "eskimo");
        BasicRope rope = new BasicRope("basicrope");
        tile.placeItem(rope);
        tile.accept(eskimo);

        startSequence();
        eskimo.pickUpItem();
    }

    /**
     * Búvárruha felvétele a mezőről
     */
    public static void PickUpBasicDivingSuit() {
        startInit();
        Tile tile = new Tile("tile");
        Eskimo eskimo = new Eskimo(tile, "eskimo");
        BasicDivingSuit bds = new BasicDivingSuit("bds");
        tile.placeItem(bds);
        tile.accept(eskimo);

        startSequence();
        eskimo.pickUpItem();
    }

    /**
     * Ásó felvétele a mezőről
     */
    public static void PickUpShovel() {
        startInit();
        Tile tile = new Tile("tile");
        Eskimo eskimo = new Eskimo(tile, "eskimo");
        Shovel shovel = new Shovel("shovel");
        tile.placeItem(shovel);
        tile.accept(eskimo);

        startSequence();
        eskimo.pickUpItem();
    }

    /**
     * Pisztolyrész felvétele a mezőről
     */
    public static void PickUpGun() {
        startInit();
        Tile tile = new Tile("tile");
        Eskimo eskimo = new Eskimo(tile, "eskimo");
        Gun gun = new Gun("gun");
        tile.placeItem(gun);
        tile.accept(eskimo);

        startSequence();
        eskimo.pickUpItem();
    }

    /**
     * Patronrész felvétele a mezőről
     */
    public static void PickUpCartridge() {
        startInit();
        Tile tile = new Tile("tile");
        Eskimo eskimo = new Eskimo(tile, "eskimo");
        Cartridge cartridge = new Cartridge("cartridge");
        tile.placeItem(cartridge);
        tile.accept(eskimo);

        startSequence();
        eskimo.pickUpItem();
    }
    /**
     * Jelzőfényrész felvétele a mezőről
     */
    public static void PickUpBeacon() {
        startInit();
        Tile tile = new Tile("tile");
        Eskimo eskimo = new Eskimo(tile, "eskimo");
        Beacon beacon = new Beacon("beacon");
        tile.placeItem(beacon);
        tile.accept(eskimo);

        startSequence();
        eskimo.pickUpItem();
    }

    /**
     * Jelzőrakéta összeszerelése szekvenciaindító függvény
     */
    public static void FlareAssembly() {
        startInit();
        Eskimo eskimo = new Eskimo(null, "eskimo");

        startSequence();
        eskimo.buildFlare();
    }

    /**
     * Player ásóval ás szekvenciaindító függvény
     */
    public static void PlayerDigsWithShovel() {
        startInit();
        Tile tile = new Tile("tile");
        Eskimo eskimo = new Eskimo(tile, "eskimo");
        Shovel shovel = new Shovel("shovel");
        eskimo.addUsableItem(shovel);
        tile.accept(eskimo);

        startSequence();
        eskimo.useItem(0, tile);

    }
    /**
     * Player kézzel ás szekvenciaindító függvény
     */
    public static void PlayerDigsWithHands() {
        startInit();
        Tile tile = new Tile("tile");
        Eskimo eskimo = new Eskimo(tile, "eskimo");
        tile.accept(eskimo);

        startSequence();
        eskimo.dig();
    }

    /**
     * Kimentési kísérlet kötél nélkül.
     */
    public static void SaveNoRope() {
        Tile t1 = new Tile("tile1");
        Tile t2 = new Tile("tile2");
        Eskimo p1 = new Eskimo(t1, "eskimo1");
        Eskimo p = new Eskimo(t2, "eskimo2");
       
        //setup
        startInit();
        t2.accept(p);
        t1.accept(p1);
        t1.addNeighbor(t2);
        t2.addNeighbor(t1);

        startSequence();
        p1.fallInWater();
    }

    /**
     * Kimetnés BasicRope-al.
     */
    public static void SaveBasicRope() {
        Tile t1 = new Tile("tile1");
        Tile t2 = new Tile("tile2");
        Eskimo p1 = new Eskimo(t1, "eskimo1");
        Eskimo p = new Eskimo(t2, "eskimo2");
        p.addRope(new BasicRope("basicrope"));
       
        //setup
        startInit();
        t2.accept(p);
        t1.accept(p1);
        t1.addNeighbor(t2);
        t2.addNeighbor(t1);

        startSequence();
        p1.fallInWater();
    }

    public static void ListFunctions() {
        System.out.println(" 1 - Player steps on stable tile.");
        System.out.println(" 2 - Player steps on unstable tile without divingsuit");
        System.out.println(" 3 - Player steps on unstable tile with divingsuit.");
        System.out.println(" 4 - Player steps on hole without divingsuit");
        System.out.println(" 5 - Player steps on hole with divingsuit");
        System.out.println(" 6 - Eskimo uses special ability.");
        System.out.println(" 7 - Eskimo uses special ability on a hole.");
        System.out.println(" 8 - Researcher uses special ability.");
        System.out.println(" 9 - Snowstorm affects a tile without building.");
        System.out.println("10 - Snowstorm affects a tile with an igloo on it.");
        System.out.println("11 - Player picks up food.");
        System.out.println("12 - Player picks up rope.");
        System.out.println("13 - Player picks up divingsuit.");
        System.out.println("14 - Plater picks up shovel.");
        System.out.println("15 - Player picks up gun.");
        System.out.println("16 - Player picks up cartridge.");
        System.out.println("17 - Player picks up beacon.");
        System.out.println("18 - Players assemble the flare.");
        System.out.println("19 - Player digs with a shovel.");
        System.out.println("20 - Player digs with hands.");
        System.out.println("21 - Player asks for help but doesn't get saved.");
        System.out.println("22 - Player gets saved by another player.");
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
                    commands.get(idx).run();
                }
            } catch (Exception e) {
                System.out.println("Must be an integer value between 0 and 22!");
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
        sc.close();
        return (result.equals("I") || result.equals("i") || result.equals(""));
    }

    public static void startInit() {
        System.out.println("Inicializálás:");
    }

    public static void startSequence() {
        System.out.println("\nSzekvencia:");
    }
}
