package graphics;

import javax.swing.*;

import java.awt.*;

import java.util.ArrayList;

import sumatra.*;

public class CreaturesPanel extends JPanel {
    private static CreaturesPanel instance = new CreaturesPanel();
    public static CreaturesPanel getInstance() { return instance; }

    JPanel panel = new JPanel();
    private CreaturesPanel(){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        JLabel cLabel = new JLabel("Creatures");
        Font labelFont = cLabel.getFont();
        cLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
        add( cLabel);
        
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        add( scrollPane );
    }

    public void attachViews(){
        
        ArrayList<Creature> creatures = World.getInstance().getCreatures();

        /*/
        // Test adatok:
        Tile tt = new Tile(9);
        Eskimo tc = new Eskimo(tt, 3);
        tc.addRope(new BasicRope());
        
        Creature tc2 = new Researcher(tt, 2);
        Creature tc3 = new Bear(tt, 1);
        Creature tc4 = new Bear(tt, 5);
        Creature tc5 = new Researcher(tt, 6);

        creatures.add( tc );
        creatures.add( tc2 );
        creatures.add( tc3 );
        creatures.add( tc4 );
        creatures.add( tc5 );
        for( int i = 0; i < 10; ++i ){
            creatures.add( new Researcher(tt, i) );
        }
        //*/

        for( Creature c : creatures ){
            OneCreaturePanel ocp = new OneCreaturePanel(c);
            panel.add( ocp );
            c.addView(ocp);
        }
    }
}
