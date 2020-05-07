package graphics;

import sumatra.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TileView extends JPanel implements IView {
    private int x, y;
    private List<UpdateJPanel> views;
    private Tile tile;
    public TileView(Tile t, int x, int y) {
        setLayout(new GridLayout(0, 2));
        views = new ArrayList<UpdateJPanel>();
        this.x = x;
        this.y = y;
        tile = t;
        views.add(new BuildingView());
        views.add(new ItemView());
        for(int i = 0; i < 6; i++)
            views.add(new CreatureView(i));

        for(UpdateJPanel up : views) {
            add(up);
        }
        subjectChanged();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(160, 500);
    };

    @Override
    public void subjectChanged() {
        for(UpdateJPanel up : views) {
            up.Update(tile);
        }
    }

    public Point getPosition() {
        return new Point(x, y);
    }
}
