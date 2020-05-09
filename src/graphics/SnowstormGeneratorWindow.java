package graphics;

import javax.swing.*;
import java.util.HashMap;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.ArrayList;


public class SnowstormGeneratorWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    HashMap<Integer, Integer> addedSnowyTiles = new HashMap<Integer, Integer>();

    String snow_ammunt_list = "";
    ArrayList<Integer> tiles = new ArrayList<Integer>();

    public SnowstormGeneratorWindow(int tileCount) {
        super("Snowstorm Generator"); 
        for (int i = 0; i < tileCount; ++i) {
            tiles.add(i);
        }

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(1, 2, 1, 2);
        gbc.fill = GridBagConstraints.BOTH;

        JTextArea snowyTilesList = new JTextArea();
        snowyTilesList.setEditable(false);
        JScrollPane snowListScroll = new JScrollPane(snowyTilesList);
        JButton addSnow = new JButton("Add Snow");
        JFormattedTextField snowField = new JFormattedTextField(NumberFormat.getNumberInstance());

        JComboBox<Integer> tileSelector = new JComboBox<Integer>(tiles.toArray(new Integer[0]));
        tileSelector.setSelectedIndex(0);
        snowField.setPreferredSize(new Dimension(166, 30));
        tileSelector.setPreferredSize(new Dimension(166, 30));
        addSnow.setPreferredSize(new Dimension(166, 30));
        snowListScroll.setPreferredSize(new Dimension(166, 210));

        JButton OK = new JButton("OK");
        OK.setPreferredSize(new Dimension(166, 30));

        mainPanel.add(snowField, gbc);
        mainPanel.add(tileSelector, gbc);
        mainPanel.add(addSnow, gbc);
        mainPanel.add(snowListScroll, gbc);
        mainPanel.add(OK, gbc);


        addSnow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                Integer val = addedSnowyTiles.get(tileSelector.getSelectedIndex());
                if (val != null)
                    return;
                String new_snowy_tile = Integer.toString(tileSelector.getSelectedIndex()) + " " + snowField.getText();
                snow_ammunt_list += new_snowy_tile + "\n";
                addedSnowyTiles.put(tileSelector.getSelectedIndex(), Integer.parseInt(snowField.getText()));
                snowyTilesList.setText(snow_ammunt_list);
            }
        });


        OK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
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

    public HashMap<Integer,Integer> showDialog() {
        return addedSnowyTiles;
    }
}
