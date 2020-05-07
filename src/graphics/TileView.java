package graphics;

import sumatra.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TileView extends JPanel implements IView {
    private int x, y;
    private GridLayout layout;
    private List<IUpdate> views;
    private Tile tile;
    public TileView(Tile t, int x, int y) {
        layout = new GridLayout(4, 2);
        this.x = x;
        this.y = y;
        tile = t;
        views.add(new BuldingView());
        views.add(new ItemView());
        for(int i = 0; i < 6; i++)
            views.add(new CreatureView(i));
    }

    @Override
    public void subjectChanged() {
        for(IUpdate up : views) {
            up.Update(tile);
        }
    }

    public Point getPosition() {
        return new Point(x, y);
    }
}
