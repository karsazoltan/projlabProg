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
            Point p = tiles.get(i).getPosition();
            pw.write("tile " + i + " " + p.x + " " + p.y + "\n");
        }
        pw.flush();
        pw.close();

        World.getInstance().saveConfig(filename + ".data.txt");
    }

    public void attachTileViews() {
        int x = 10, y = 10;
        for (int i = 0; i < World.getInstance().getTileCount(); i++) {
            tiles.add(new TileView(World.getInstance().getTileAt(i), x, y));
            x += 60;
            if (x >= this.getWidth()) {
                y += 60;
                x = 10;
            }
        }
        displayTileViews();
    }

    public void loadTileViewsFromFile(String filename) throws IOException {
        FileReader fis = new FileReader(filename);
        BufferedReader br = new BufferedReader(fis);

        String[] dataline = br.readLine().trim().split(" ");

        if (dataline[0].equals("worlddata")) {
            World.getInstance().loadConfig(filename);
            attachTileViews();
            return;
        } else if (!dataline[0].equals("datafile"))
            throw new IOException("Hibás első sor!");

        World.getInstance().loadConfig(dataline[1]);

        int count = Integer.parseInt(br.readLine().trim().split(" ")[1]);

        for (int i = 0; i < count; i++) {
            String[] info = br.readLine().trim().split(" ");
            tiles.add(new TileView(World.getInstance().getTileAt(Integer.parseInt(info[1])),
                    Integer.parseInt(info[2]), Integer.parseInt(info[3])));
        }
        displayTileViews();

        br.close();
    }

    private void displayTileViews() {
        for (TileView tv : tiles) {
            tv.setBounds(tv.getPosition().x, tv.getPosition().y, 50, 50);
            this.add(tv);
        }
    }
}
