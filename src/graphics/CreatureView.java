package graphics;

import sumatra.Tile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CreatureView extends JPanel implements IUpdate {
    private int index;
    public CreatureView(int index) {
        this.index = index;
    }
    @Override
    public void Update(Tile t) {
        setBorder(new LineBorder(new Color(149, 19, 29)));
        //hogyha ...

        //TODO
    }
}
