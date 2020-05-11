package graphics;

import sumatra.Tile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * A tile-ok és az ott tartalmazottak (creature, item, building) megjelenítésére használható grafikus interfész
 */
public class TileView extends JPanel implements IView {
    /**
     * A vásznon a tile pozíciója
     */
    private int x, y;
    /**
     * A tartalmazott nézetek
     */
    private List<UpdateJPanel> views;
    /**
     * Modell-re való referencia
     */
    private Tile tile;
    /**
     * a kapacitás megjelenítése
     */
    private JLabel capLabel;
    /**
     * a hó ha van, különben item megjelenítése
     */
    private JLabel snow_itemLabel;

    /**
     * Alap konstruktor
     * @param t hivatkozás a modellbeli mezőre
     * @param x 1. koordináta
     * @param y 2. koordináta
     */
    public TileView(Tile t, int x, int y) {
        t.addView(this);
        setLayout(new BorderLayout());
        JLabel idLabel = new JLabel(t.getID().toString());
        add(idLabel, BorderLayout.PAGE_START);

        JPanel creatures = new JPanel();
        creatures.setPreferredSize(new Dimension(140, 120));
        creatures.setLayout(new GridLayout(3, 3));

        views = new ArrayList<>();
        this.x = x;
        this.y = y;
        tile = t;

        JPanel info = new JPanel();
        info.setPreferredSize(new Dimension(140, 40));
        info.setLayout(new FlowLayout());

        capLabel = new JLabel("");
        JPanel capPanel = new JPanel();
        capPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        capPanel.setPreferredSize(new Dimension(30, 30));
        capPanel.add(capLabel);
        info.add(capPanel);

        snow_itemLabel = new JLabel("");
        ItemView iv = new ItemView(snow_itemLabel);
        views.add(iv);
        info.add(iv);

        BuildingView bv = new BuildingView(new JLabel(""));
        info.add(bv);
        views.add(bv);

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
    }

    /**
     * A nézet és tartalmazott nézetek frissítése
     */
    @Override
    public void subjectChanged() {
        for(UpdateJPanel up : views) {
            up.Update(tile);
        }
        capLabel.setText(tile.getInfo());
        if(tile.getSnow() != 0)
            snow_itemLabel.setText(tile.getSnow().toString());
    }

    /**
     * Visszadja a jelenlegi pozícióját
     * @return Point
     */
    public Point getPosition() {
        return new Point(x, y);
    }

    /**
     * Beállítja a grafikus nézet helyzetét a vásznon
     * @param x első
     * @param y második koordináta
     */
    public void setPosition(int x, int y) {
        this.x = x; this.y = y;
    }

    /**
     * Visszaadja a referált játékmodell mezőjét
     * @return
     */
    public Tile getTile(){
        return tile;
    }
}
