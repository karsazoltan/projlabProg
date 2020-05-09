package graphics;

import sumatra.Interpreter;
import sumatra.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BearTileChooserWindow extends JDialog {
    int where;

    public BearTileChooserWindow(Tile t) {
        setSize(200, 90);
        setTitle("Hova mozogjon a medve?");
        setLayout(new GridLayout(0, 1));

        JButton okButton = new JButton("Ok");
        ActionListener lambda;

        ArrayList<Integer> il = new ArrayList<>();
        for (Tile tile : t.getNeighbors()) {
            il.add(tile.getID());
        }

        final Integer[] options = il.toArray(new Integer[0]);

        JComboBox<Integer> jcb = new JComboBox<Integer>(options);
        lambda = (arg) -> {
            int index = jcb.getSelectedIndex();
            if (index < 0) return;
            where = options[index];
            dispose();
        };
        add(jcb);
        okButton.addActionListener(lambda);
        add(okButton);
        setModal(true);
        setLocationRelativeTo(null);
    }

    public int showDialog() {
        setVisible(true);
        return where;
    }
}
