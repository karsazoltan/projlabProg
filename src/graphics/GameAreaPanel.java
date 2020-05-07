package graphics;

import sumatra.World;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class GameAreaPanel extends JPanel {
    private static GameAreaPanel instance = new GameAreaPanel();
    public static GameAreaPanel getInstance() { return instance; }

    private static ArrayList<TileView> tiles;

    private GameAreaPanel() {
        super();
        tiles = new ArrayList<>();
        setBackground(Color.WHITE);
        setVisible(true);
    }

    public void saveLayout(String filename) throws IOException {
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

        World.getInstance().saveConfig(filename + ".data.txt");
    }

    public void attachTileViews() {
        // TODO
    }

    public void loadTileViewsFromFile(String filename) throws IOException {
        FileReader fis = new FileReader(filename);
        BufferedReader br = new BufferedReader(fis);

        String[] dataline = br.readLine().trim().split(" ");

        if (dataline[0].equals("worlddata")) {
            World.getInstance().loadConfig(dataline[0]);
            attachTileViews();
            return;
        } else if (!dataline[0].equals("datafile"))
            throw new IOException("Hibás első sor!");

        World.getInstance().loadConfig(dataline[1]);

        int count = Integer.parseInt(br.readLine().trim().split(" ")[1]);

        for (int i = 0; i < count; i++) {
            // TODO
        }

        br.close();
    }
}
