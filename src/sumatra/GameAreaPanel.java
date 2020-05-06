package sumatra;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GameAreaPanel extends JPanel {
    private static GameAreaPanel instance = new GameAreaPanel();
    public static GameAreaPanel getInstance() { return instance; }

    private static ArrayList<TileView> tiles;

    private GameAreaPanel() {
        tiles = new ArrayList<>();
    }

    public static void saveLayout(String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        PrintWriter pw = new PrintWriter(fos);
        pw.write("datafile " + filename + ".data.txt\n");
        pw.write("tiles " + tiles.size() + "\n");
        for (int i = 0; i < tiles.size(); i++) {
            Point p = tiles.get(i).getLayout();
            pw.write("tile " + i + " " + p.x + " " + p.y + "\n");
        }
        pw.flush();
        pw.close();
    }

    public static void attachTileViews() {

    }

    public static void loadTileViewsFromFile(String path) {

    }
}
