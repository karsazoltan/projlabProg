package graphics;

import javax.swing.*;
import sumatra.Interpreter;
import sumatra.World;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class InitCreatureWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    ArrayList<String> addedCreatures = new ArrayList<String>();

    String[] creatures = { "Eskimo", "Researcher", "Polarbear"};
    String creature_list = "";
    ArrayList<Integer> tiles = new ArrayList<Integer>();

    public InitCreatureWindow(int nr) {
        super("Creature Creator"); 
        for (int i = 0; i < nr; ++i) {
            tiles.add(i);
        }

        setSize(166, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        JPanel setupPanel = new JPanel();
        JPanel creaturesPanel = new JPanel();

        creaturesPanel.setLayout(new BoxLayout(creaturesPanel, BoxLayout.Y_AXIS));
        JTextArea creaturesList = new JTextArea();
        creaturesList.setEditable(false);
        JScrollPane creaturesListScroll = new JScrollPane(creaturesList);
        creaturesListScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JButton addCreature = new JButton("Add Creature");
        JComboBox<String> creatureSelector = new JComboBox<String>(creatures);
        JComboBox<Integer> tileSelector = new JComboBox<Integer>(tiles.toArray(new Integer[0]));
        creatureSelector.setSelectedIndex(0);
        tileSelector.setSelectedIndex(0);
        creatureSelector.setPreferredSize(new Dimension(166, 19));
        tileSelector.setPreferredSize(new Dimension(166, 19));
        addCreature.setPreferredSize(new Dimension(166, 19));
        creaturesListScroll.setPreferredSize(new Dimension(166, 200));
        creaturesPanel.add(creatureSelector);
        creaturesPanel.add(tileSelector);
        creaturesPanel.add(addCreature);
        creaturesPanel.add(creaturesListScroll);
        creaturesPanel.setPreferredSize(new Dimension(166, 300));
        JButton OK = new JButton("OK");


        addCreature.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
                
                String new_creature = Integer.toString(tileSelector.getSelectedIndex()) + " " + creatures[creatureSelector.getSelectedIndex()];
                if (addedCreatures.contains(new_creature)) 
                    return;
                creature_list += new_creature + "\n";
                addedCreatures.add(new_creature.toLowerCase());
                creaturesList.setText(creature_list);
			}
        });


        OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
                Interpreter.generateCreaturesFrom(addedCreatures);
                dispose();
			}
        });



        setupPanel.add(creaturesPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(setupPanel, BorderLayout.NORTH);
        mainPanel.add(OK, BorderLayout.SOUTH);
        add(mainPanel);
        pack();
        setVisible(true);
    }
}