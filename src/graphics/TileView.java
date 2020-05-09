package graphics;

import sumatra.Tile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
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
        t.addView(this);
        setLayout(new BorderLayout());
        idLabel = new JLabel(t.getID().toString());
        add(idLabel, BorderLayout.PAGE_START);

        JPanel creatures = new JPanel();
        creatures.setPreferredSize(new Dimension(140, 120));
        creatures.setLayout(new GridLayout(3, 3));

        views = new ArrayList<UpdateJPanel>();
        this.x = x;
        this.y = y;
        tile = t;

        JPanel info = new JPanel();
        info.setPreferredSize(new Dimension(140, 40));
        info.setLayout(new FlowLayout());

        capLabel = new JLabel();
        JPanel capPanel = new JPanel();
        capPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        capPanel.setPreferredSize(new Dimension(30, 30));
        capPanel.add(capLabel);
        snowLabel = new JLabel();
        JPanel snowPanel = new JPanel();
        snowPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        snowPanel.setPreferredSize(new Dimension(30, 30));
        snowPanel.add(snowLabel);

        info.add(capPanel);
        info.add(snowPanel);

        BuildingView bv = new BuildingView(new JLabel(""));
        info.add(bv);
        views.add(bv);

        ItemView iv = new ItemView(new JLabel(""));
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

        final TileView tvObject = this;
        MouseInputAdapter maa = new MouseInputAdapter() {
            int x, y;

            @Override
            public void mouseMoved(MouseEvent e) {
                x = e.getX(); y = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (e.getSource() == tvObject) {
                    tvObject.setPosition(tvObject.getX() + e.getX() - x, tvObject.getY() + e.getY() - y);
                    GameAreaPanel.getInstance().displayTileViews();
                }
            }
        };
        this.addMouseListener(maa);
        this.addMouseMotionListener(maa);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(140, 160);
    };

    @Override
    public void subjectChanged() {
        for(UpdateJPanel up : views) {
            up.Update(tile);
        }
        capLabel.setText(tile.getInfo());
        snowLabel.setText(tile.getSnow().toString());
    }

    public Point getPosition() {
        return new Point(x, y);
    }

    public void setPosition(int x, int y) {
        this.x = x; this.y = y;
    }

    public Tile getTile(){
        return tile;
    }
}
