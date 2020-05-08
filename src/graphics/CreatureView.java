package graphics;

import sumatra.Creature;
import sumatra.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class CreatureView extends UpdateJPanel {
    static Dictionary<String, Color> viewMap = new Hashtable<String, Color>();
    static {
        viewMap.put("Polar bear", new Color(20, 100, 200));
        viewMap.put("Eskimo", new Color(200, 134, 20));
        viewMap.put("Researcher", new Color(200, 20, 20));
    }
    private int index;
    public CreatureView(int index) {
        this.index = index;
        setVisible(true);
    }
    @Override
    public void Update(Tile t) {
        try {
            Creature c = t.getCreature(index);
            setBackground(viewMap.get(c.getType()));
        } catch (IndexOutOfBoundsException ea) {
            setBackground(new Color(255, 255, 255));
        }
    }
}
