package graphics;

import javax.swing.*;

import sumatra.World;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class InitCreatureWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    ArrayList<String> addedCreatures = new ArrayList<>();

    String[] creatures = { "Eskimo", "Researcher", "Polarbear"};
    String creature_list = "";
    ArrayList<Integer> tiles = new ArrayList<>();

    /**
     * Creates a creature initializer window then adds the specified creatures to the game.
     * @param nr the number of the tiles in the game. 
     */
    public InitCreatureWindow(int nr) {
        super("Creature Creator"); 
        for (int i = 0; i < nr; ++i) {
            tiles.add(i);
        }

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(1, 2, 1, 2);
        gbc.fill = GridBagConstraints.BOTH;

        JTextArea creaturesList = new JTextArea();
        creaturesList.setEditable(false);
        JScrollPane creaturesListScroll = new JScrollPane(creaturesList);
        JButton addCreature = new JButton("Add Creature");
        JComboBox<String> creatureSelector = new JComboBox<>(creatures);
        JComboBox<Integer> tileSelector = new JComboBox<>(tiles.toArray(new Integer[0]));
        creatureSelector.setSelectedIndex(0);
        tileSelector.setSelectedIndex(0);
        creatureSelector.setPreferredSize(new Dimension(166, 30));
        tileSelector.setPreferredSize(new Dimension(166, 30));
        addCreature.setPreferredSize(new Dimension(166, 30));
        creaturesListScroll.setPreferredSize(new Dimension(166, 210));

        JButton OK = new JButton("OK");
        OK.setPreferredSize(new Dimension(166, 30));

        mainPanel.add(creatureSelector, gbc);
        mainPanel.add(tileSelector, gbc);
        mainPanel.add(addCreature, gbc);
        mainPanel.add(creaturesListScroll, gbc);
        mainPanel.add(OK, gbc);


        addCreature.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
                
                String new_creature = Integer.toString(tileSelector.getSelectedIndex()) + " " + creatures[creatureSelector.getSelectedIndex()];
                creature_list += new_creature + "\n";
                addedCreatures.add(new_creature.toLowerCase());
                creaturesList.setText(creature_list);
			}
        });


        OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
                World.getInstance().generateCreaturesFrom(addedCreatures);
                CommandPanel.getInstance().updateButtons();
                CreaturesPanel.getInstance().attachViews();
                GameAreaPanel.getInstance().attachTileViews();
                dispose();
			}
        });

        add(mainPanel);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}