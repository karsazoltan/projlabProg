package graphics;

import javax.swing.*;

import java.awt.*;

import java.util.ArrayList;

import sumatra.*;

/**
 * A játék bal oldalán látható lény listát kezelő panel.
 */
public class CreaturesPanel extends JPanel {
    /** Singleton megvalósítás: példány */
    private static CreaturesPanel instance = new CreaturesPanel();
    /** Singleton megvalósítás: függvény */
    public static CreaturesPanel getInstance() { return instance; }

    /**
    * Ebben a panelben vannak a lények.
    */
    JPanel panel = new JPanel();

    /** Singleton megvalósítás: privát konstruktor */
    private CreaturesPanel(){
        setLayout(new BorderLayout());
        
        JLabel cLabel = new JLabel("Creatures");
        Font labelFont = cLabel.getFont();
        cLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
        add(cLabel, BorderLayout.PAGE_START);
        
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Hozzárendeli a CreaturesPanelhez a modellben levő lényeket.
     */
    public void attachViews(){
        panel.removeAll();
        repaint();
        ArrayList<Creature> creatures = World.getInstance().getCreatures();

        for( Creature c : creatures ){
            OneCreaturePanel ocp = new OneCreaturePanel(c);
            panel.add( ocp );
            c.addView(ocp);
        }
    }
}
