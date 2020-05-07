package graphics;

import sumatra.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TileView extends JPanel implements IView {
    private int x, y;
    private GridLayout layout;
    private List<CreatureView> creaturesV;
    private ItemView itemV;
    private BuldingView buildingV;
    public TileView(Tile t, int x, int y) {
        layout = new GridLayout(4, 2);
        this.x = x;
        this.y = y;
    }

    @Override
    public void subjectChanged() {
        //TODO
    }

    public Point getPosition() {
        return new Point(x, y);
    }
}
