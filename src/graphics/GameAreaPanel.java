package graphics;

import sumatra.Tile;
import sumatra.World;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/** A játéktér komponense */
public class GameAreaPanel extends JPanel {
    /** Singleton megvalósítás: példány */
    private static GameAreaPanel instance = new GameAreaPanel();
    /** Singleton megvalósítás: függvény */
    public static GameAreaPanel getInstance() { return instance; }

    /** Hivatkozás a megjelenítendő TileViewekre */
    private static ArrayList<TileView> tiles;

    /** Singleton megvalósítás: privát konstruktor */
    private GameAreaPanel() {
        super();
        setLayout(null);
        tiles = new ArrayList<>();
        setBackground(Color.WHITE);
        setVisible(true);
    }

    /**
     * A grafikus felület pályakiíró függvénye. Kiírja a TileViewek layoutját,
     * és szól a Worldnek, hogy írja ki a modellt
     * @param filename A cél fájl neve
     * @throws IOException Ha nem tudunk írni az adott területre
     */
    public void saveLayout(String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        PrintWriter pw = new PrintWriter(fos);
        pw.write("datafile " + filename + ".data.txt\n");
        pw.write("tiles " + tiles.size() + "\n");
        for (int i = 0; i < tiles.size(); i++) {
            Point p = tiles.get(i).getPosition();
            pw.write("    tile " + i + " " + p.x + " " + p.y + "\n");
        }
        pw.flush();
        pw.close();

        World.getInstance().saveConfig(filename + ".data.txt");
    }

    /** Létrehozza, és elrendezi a TileVieweket a már létező Tile-ok alapján */
    public void attachTileViews() {
        tiles.clear();

        int x = 10, y = 10;
        for (int i = 0; i < World.getInstance().getTileCount(); i++) {
            tiles.add(new TileView(World.getInstance().getTileAt(i), x, y));
            x += 170;
            if (x >= this.getWidth()) {
                y += 165;
                x = 10;
            }
        }
        displayTileViews();
    }

    /**
     * A grafikus felület pályabeolvasó függvénye. Beolvassa a modellt, illetve a
     * layoutot is. Ha modellfájlt kap, akkor automatikusan generál hozzá layoutot
     * @param filename A forrásfájl neve
     * @throws IOException Ha nem tudunk olvasni az adott területről, vagy hibás a fájl
     */
    public void loadTileViewsFromFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));

        String[] dataline = br.readLine().trim().split(" ");

        if (dataline[0].equals("worlddata")) {
            World.getInstance().loadConfig(filename);
            attachTileViews();
            br.close();
            return;
        } else if (!dataline[0].equals("datafile")) {
            throw new IOException("Hibás első sor!");
        }

        World.getInstance().loadConfig(dataline[1]);

        int count = Integer.parseInt(br.readLine().trim().split(" ")[1]);

        tiles.clear();
        for (int i = 0; i < count; i++) {
            String[] info = br.readLine().trim().split(" ");
            tiles.add(new TileView(World.getInstance().getTileAt(Integer.parseInt(info[1])),
                    Integer.parseInt(info[2]), Integer.parseInt(info[3])));
        }
        br.close();

        displayTileViews();
    }

    /** Megjeleníti a már elkészített TileView-eket */
    public void displayTileViews() {
        this.removeAll();

        int largestX = 0, largestY = 0;

        for (TileView tv : tiles) {
            tv.setBounds(tv.getPosition().x, tv.getPosition().y, 140, 160);
            this.add(tv);
            tv.setVisible(true);
            if (tv.getPosition().x > largestX) largestX = tv.getPosition().x;
            if (tv.getPosition().y > largestY) largestY = tv.getPosition().y;
        }

        setPreferredSize(new Dimension(largestX + 150, largestY + 170));

        revalidate();
        repaint();
    }

    /**
     * Megkeresi egy tile-hoz a hozzátartozó tileView-ot.
     * @param tile tile, aminek a tileViewját meg akarjuk kapni.
     * @return a keresett tileView.
     */
    public TileView findTileView(Tile tile){
        for( TileView tileView : tiles ){
            if( tile == tileView.getTile() )
                return tileView;
        }
        return null;
    }

    /**
     * A mezők közti összekötések (élek) kirajzolása.
     * Ez a függvény, akkor hívódik meg, ha meghívjuk a panel repaint() függvényét.
     */
    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;

        g.setStroke(new BasicStroke(10));

        for( TileView tileView : tiles ){
            Point p1 = tileView.getPosition();
            p1.translate(140/2, 160/2);            

            ArrayList<Tile> neighbors = tileView.getTile().getNeighbors();
            for( Tile tile : neighbors ){
                
                TileView neighTileView = findTileView(tile);
                Point p2 = neighTileView.getPosition();
                p2.translate(140/2, 160/2);
                            
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }
}
