package graphics;

import sumatra.Interpreter;
import sumatra.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BearTileChooserWindow extends JFrame {
    int where;

    public BearTileChooserWindow(Tile t) {
        setSize(200, 90);
        setTitle("Hova mozogjon a medve?");
        setLayout(new GridLayout(0, 1));

        JButton okButton = new JButton("Ok");
        Component generic;
        ActionListener lambda;

        ArrayList<Integer> optionsList = new ArrayList<>();
        for (Tile tile : t.getNeighbors()) {
            optionsList.add(tile.getID());
        }
        final Integer[] options = (Integer[])(optionsList.toArray());

        JComboBox<Integer> jcb = new JComboBox<Integer>(options);
        generic = jcb;
        lambda = (arg) -> {
            int index = jcb.getSelectedIndex();
            if (index < 0) return;
            where = options[index];
            dispose();
        };
    }

    public int showDialog() {
        return where; // MELYIK TILE-RA MOZOGJON
    }
}
