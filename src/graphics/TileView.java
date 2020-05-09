package graphics;

import sumatra.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

public class TileView extends JPanel implements IView {
    private int x, y;
    private List<UpdateJPanel> views;
    private Tile tile;

    private JLabel idLabel;
    private JLabel capLabel;
    private JLabel snowLabel;

    public TileView(Tile t, int x, int y) {
        setLayout(new BorderLayout());
        idLabel = new JLabel(t.getID().toString());
        add(idLabel, BorderLayout.PAGE_START);

        JPanel creatures = new JPanel();
        creatures.setPreferredSize(new Dimension(160, 160));
        creatures.setLayout(new GridLayout(3, 3));

        views = new ArrayList<UpdateJPanel>();
        this.x = x;
        this.y = y;
        tile = t;

        JPanel info = new JPanel();
        info.setPreferredSize(new Dimension(160, 40));
        info.setLayout(new FlowLayout());
        capLabel = new JLabel();
        snowLabel = new JLabel();
        info.add(capLabel);
        info.add(snowLabel);

        BuildingView bv = new BuildingView(new JLabel());
        info.add(bv);
        views.add(bv);

        ItemView iv = new ItemView(new JLabel());
        views.add(iv);
        info.add(iv);

        add(info, BorderLayout.PAGE_END);

        for(int i = 0; i < 9; i++) {
            CreatureView cv = new CreatureView(i);
            views.add(cv);
            creatures.add(cv);
        }

        add(creatures, BorderLayout.CENTER);

        subjectChanged();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(80, 160);
    };

    @Override
    public void subjectChanged() {
        for(UpdateJPanel up : views) {
            up.Update(tile);
        }
        capLabel.setText(tile.getTypeChar().toString() + "(" + tile.getCapacity().toString() + ")");
        snowLabel.setText(tile.getSnow().toString());
    }

    public Point getPosition() {
        return new Point(x, y);
    }
}
