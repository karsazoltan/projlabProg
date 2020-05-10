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

    public void attachViews(){
        panel.removeAll();
        ArrayList<Creature> creatures = World.getInstance().getCreatures();

        for( Creature c : creatures ){
            OneCreaturePanel ocp = new OneCreaturePanel(c);
            panel.add( ocp );
            c.addView(ocp);
        }
    }
}
