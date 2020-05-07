package graphics;

import sumatra.Creature;
import sumatra.Tile;

import javax.swing.*;
import java.util.ArrayList;

public class CreatureView extends JPanel implements IUpdate {
    private int index;
    JLabel label;
    public CreatureView(int index) {
        this.index = index;
        label = new JLabel("");
    }
    @Override
    public void Update(Tile t) {
        setBorder(BorderFactory.createTitledBorder("title"));
        //hogyha ...
        try {
            Creature c = t.getCreature(index);
            //TODO
        } catch (IndexOutOfBoundsException ea) {
            label.setText("");
        }
    }
}
