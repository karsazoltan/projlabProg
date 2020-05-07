package graphics;

import sumatra.Creature;
import sumatra.Tile;

import javax.swing.*;
import java.awt.*;

public class CreatureView extends UpdateJPanel {
    private int index;
    JLabel label;
    public CreatureView(int index) {
        this.index = index;
        label = new JLabel("");
        setVisible(true);
    }
    @Override
    public void Update(Tile t) {
        setBorder(BorderFactory.createTitledBorder("Creature"));
        //hogyha ...
        try {
            Creature c = t.getCreature(index);
            label.setText(c.getType());
        } catch (IndexOutOfBoundsException ea) {

            label.setText("");
        }
    }
}
