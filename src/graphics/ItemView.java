package graphics;

import sumatra.Tile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ItemView extends JPanel implements IUpdate{
    @Override
    public void Update(Tile t) {
        setBorder(new LineBorder(new Color(20, 40, 140)));
        //hogyha ...
        t.getItem();
        //TODO
    }
}
