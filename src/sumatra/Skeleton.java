package sumatra;

import java.util.*;

public class Skeleton {
    // TODO ez kell? Ne legyünk lusták :(
    private static void print(String s) {
        System.out.println(s);
    }

    public static void PlayerStepsOnTile() {
        print("loxd");
    }

    public static void PlayerStepsOnUnstableTileWithoutDivingSuit() {

    }

    public static void PlayerStepsOnUnstableTileWithDivingSuit() {

    }
    
    public static void PlayerStepsOnHoleWithoutDivingSuit() {

    }

    public static void PlayerStepsOnHoleWithDivingSuit() {

    }

    /**
     * Eszkimó használja a képességét nem lyukon szekvenciaindító függvény.
     */
    public static void EskimoAbilityNoHole() {
        Tile tile = new Tile();
        Eskimo e = new Eskimo(tile, "e" );

        tile.accept(e);
        e.useAbility(tile);
    }

    /**
     * Eszkimó használja a képességét lyukon szekvenciaindító függvény.
     */
    public static void EskimoAbilityOnHole() {
        Tile tile = new HoleTile();
        Eskimo e = new Eskimo(tile, "e" );

        tile.accept(e);
        e.useAbility(tile);
    }

    /**
     * Sarkkutató használja a képességét szekvenciaindító függvény.
     */
    public static void ResearcherAbility() {
        Tile tile = new Tile();
        Researcher r = new Researcher(tile, "r" );

        tile.accept(r);
        r.useAbility(tile);
    }

    public static void SnowstormNoBuilding() {

    }

    public static void SnowstormIgloo() {

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
        //Tile tile = new Tile("tile");
        Tile tile = new Tile(); // TODO Jaki FIX THIS
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
        //Tile tile = new Tile("tile");
        Tile tile = new Tile(); // TODO Jaki FIX THIS
        Eskimo eskimo = new Eskimo(tile, "eskimo");
        tile.accept(eskimo);

        eskimo.dig();
    }

    public static void SaveNoRope() {

    }

    public static void SaveBasicRope() {

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
}