package graphics;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import sumatra.World;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.ArrayList;


public class InitWorldWindow extends JFrame {
    /**
     * ID for serialization (not used)
     */
    private static final long serialVersionUID = 1L;

    ArrayList<Integer> tileID = new ArrayList<>();
    ArrayList<String> item = new ArrayList<>();
    ArrayList<String> capacity = new ArrayList<>();
    ArrayList<String> snow = new ArrayList<>();
    ArrayList<String> type = new ArrayList<>();
    ArrayList<String> link = new ArrayList<>();

    String[] types = { "Stable", "Unstable", "Hole"};
    String[] items = { "Beacon", "Cartridge", "Gun", "Rope", "Divingsuit", "Shovel", "Brokenshovel", "Tent", "Food"};
    String tile_list = "";
    String item_list = "";
    String link_list = "";


    /**
     * Constuctor for World Creator Window
     * Creates a new frame and then calls the Interpreter's world creator function 
     */
    public InitWorldWindow() {
        super("World Creator");

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(1, 2, 1, 2);
        gbc.fill = GridBagConstraints.BOTH;

        // Left Panel
        NumberFormat nformat = NumberFormat.getIntegerInstance();
        nformat.setGroupingUsed(false);
        NumberFormatter nf = new NumberFormatter(nformat);
        nf.setAllowsInvalid(false);
        nf.setMinimum(0);

        JFormattedTextField snowField = new JFormattedTextField(nf);
        JFormattedTextField capacityField = new JFormattedTextField(nf);
        JTextArea tilesList = new JTextArea();       
        JComboBox<String> typeList = new JComboBox<>(types);
        JScrollPane tilesListScroll = new JScrollPane(tilesList);
        JButton addTile = new JButton("Add Tile");
        typeList.setSelectedIndex(0);
        tilesList.setEditable(false);
        capacityField.setValue(0);
        snowField.setValue(0);
        typeList.setPreferredSize(new Dimension(166, 30));
        capacityField.setPreferredSize(new Dimension(83, 30));
        snowField.setPreferredSize(new Dimension(83, 30));
        addTile.setPreferredSize(new Dimension(166, 30));
        tilesListScroll.setPreferredSize(new Dimension(166, 210));

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        mainPanel.add(typeList, gbc);
        gbc.gridwidth = 1; gbc.gridy++;
        mainPanel.add(snowField, gbc);
        gbc.gridx = 1;
        mainPanel.add(capacityField, gbc);
        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        mainPanel.add(addTile, gbc);
        gbc.gridy++;
        mainPanel.add(tilesListScroll, gbc);
        gbc.gridwidth = 1;


        // Center Panel
        gbc.gridx = 2; gbc.gridy = GridBagConstraints.RELATIVE;

        JComboBox<Integer> t1 = new JComboBox<>(tileID.toArray(new Integer[0]));
        JComboBox<Integer> t2 = new JComboBox<>(tileID.toArray(new Integer[0]));
        JButton addLink = new JButton("Add Link");
        JTextArea linksList = new JTextArea();
        linksList.setEditable(false);
        JScrollPane linksListScroll = new JScrollPane(linksList);
        t1.setPreferredSize(new Dimension(166, 30));
        t2.setPreferredSize(new Dimension(166, 30));
        addLink.setPreferredSize(new Dimension(166, 30));
        linksListScroll.setPreferredSize(new Dimension(166, 210));
        mainPanel.add(t1, gbc);
        mainPanel.add(t2, gbc);
        mainPanel.add(addLink, gbc);
        mainPanel.add(linksListScroll, gbc);


        // Right Panel
        gbc.gridx = 3;

        JComboBox<Integer> tileSelector = new JComboBox<>(tileID.toArray(new Integer[0]));
        JComboBox<String> itemSelector = new JComboBox<>(items);
        JButton addItem = new JButton("Add Item");
        JTextArea itemsList = new JTextArea();
        itemsList.setEditable(false);
        JScrollPane itemsListScroll = new JScrollPane(itemsList);
        addItem.setPreferredSize(new Dimension(166, 30));
        tileSelector.setPreferredSize(new Dimension(166, 30));
        itemSelector.setPreferredSize(new Dimension(166, 30));
        itemsListScroll.setPreferredSize(new Dimension(166, 210));
        mainPanel.add(tileSelector, gbc);
        mainPanel.add(itemSelector, gbc);
        mainPanel.add(addItem, gbc);
        mainPanel.add(itemsListScroll, gbc);


        // OK Button
        JButton OK = new JButton("OK");
        OK.setPreferredSize(new Dimension(166, 30));
        gbc.gridx = 2; gbc.gridy = 4;
        mainPanel.add(OK, gbc);

        // Action Listeners
        addTile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
                tileID.add(tileID.size());
                item.add("none");
                type.add(types[typeList.getSelectedIndex()]);
                if (types[typeList.getSelectedIndex()].equals("Unstable"))
                    capacity.add(capacityField.getText());
                else if (types[typeList.getSelectedIndex()].equals("Hole"))
                    capacity.add("0");
                else
                    capacity.add("inf");
                snow.add(snowField.getText());

                tile_list += Integer.toString(tileID.size()-1) + " " + types[typeList.getSelectedIndex()] + " ";
                tile_list += snowField.getText() + " ";
                if (type.get(type.size()-1).equals("Unstable")) {
                    tile_list += capacityField.getText();
                }
                tile_list += "\n";
                tilesList.setText(tile_list);
                t1.setModel(new DefaultComboBoxModel<>(tileID.toArray(new Integer[0])));
                t2.setModel(new DefaultComboBoxModel<>(tileID.toArray(new Integer[0])));
                tileSelector.setModel(new DefaultComboBoxModel<>(tileID.toArray(new Integer[0])));
			}
        });
        

        addLink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
                if (t1.getSelectedIndex() != t2.getSelectedIndex()) {
                    String new_link = Integer.toString(t1.getSelectedIndex()) + " " + Integer.toString(t2.getSelectedIndex());
                    if (link.contains(new_link) || link.contains(new_link.charAt(2) + " " + new_link.charAt(0))) 
                        return;
                    link_list += new_link + "\n";
                    link.add(new_link);
                    linksList.setText(link_list);
                }
			}
        });


        addItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
                String new_item = Integer.toString(tileSelector.getSelectedIndex()) + " " + items[itemSelector.getSelectedIndex()];
                if (item.contains(new_item))
                    return;
                item_list += new_item + "\n";
                item.set(tileSelector.getSelectedIndex(), items[itemSelector.getSelectedIndex()]);
                itemsList.setText(item_list);
			}
        });


        OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
                ArrayList<String> setup = new ArrayList<>();

                for (int i = 0; i < tileID.size(); ++i) {
                    String new_tile = type.get(i).charAt(0) + " ";
                    new_tile += snow.get(i) + " ";
                    new_tile += item.get(i).toLowerCase();
                    if (type.get(i).equals("Unstable")) {
                        new_tile += " " + capacity.get(i);
                    }

                    setup.add(new_tile);    
                }

                for (String l : link) {
                    setup.add(l);
                }

                World.getInstance().generateTilesFrom(setup, tileID.size(), link.size());
                CommandPanel.getInstance().updateButtons();
                GameAreaPanel.getInstance().attachTileViews();
                CreaturesPanel.getInstance().attachViews();
                dispose();
			}
        });

        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(mainPanel);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}