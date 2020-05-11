package graphics;

import sumatra.Creature;
import sumatra.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * A creaturok meglenítéséhez szükséges grafikus elem
 */
public class CreatureView extends UpdateJPanel {
    /**
     * Az egyes típusok és megjelnítés összerendeltje
     */
    static Dictionary<String, Color> viewMap = new Hashtable<>();
    static {
        viewMap.put("Polar bear", new Color(20, 100, 200));
        viewMap.put("Eskimo", new Color(200, 134, 20));
        viewMap.put("Researcher", new Color(200, 20, 20));
    }

    /**
     * A tile hányadik "rekeszét" jelöli
     */
    private int index;

    /**
     * Alap ctor
     * @param index a mező creature rekeszének sorszáma
     */
    public CreatureView(int index) {
        super(new JLabel());
        this.index = index;
    }

    /**
     * A nézet frissítése az átadott Tile referencia alapján
     * @param t a creature-hoz tartozó Tile referencia
     */
    @Override
    public void Update(Tile t) {
        try {
            Creature c = t.getCreature(index);
            setBackground(viewMap.get(c.getType()));
            label.setText(((Integer) c.getIndex()).toString());
        } catch (IndexOutOfBoundsException ea) {
            setBackground(new Color(255, 255, 255));
            label.setText("");
        }
    }
}
