package graphics;

import javax.swing.*;

import sumatra.Interpreter;
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

    ArrayList<String> commands;

    ArrayList<Integer> tileID = new ArrayList<Integer>();
    ArrayList<String> item = new ArrayList<String>();
    ArrayList<String> capacity = new ArrayList<String>();
    ArrayList<String> snow = new ArrayList<String>();
    ArrayList<String> type = new ArrayList<String>();
    ArrayList<String> link = new ArrayList<String>();

    String[] types = { "Stable", "Unstable", "Hole"};
    String[] items = { "Beacon", "Cartridge", "Gun", "Rope", "Divingsuit", "Shovel", "Brokenshovel", "Tent", "Food"};
    String tile_list = "";
    String item_list = "";
    String link_list = "";



    public InitWorldWindow() {
        super("World Creator");

        setSize(498, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        JPanel setupPanel = new JPanel();
        JPanel tilesPanel = new JPanel();
        JPanel linksPanel = new JPanel();
        JPanel itemsPanel = new JPanel();


        // Left Panel
        tilesPanel.setLayout(new BoxLayout(tilesPanel, BoxLayout.Y_AXIS));
        JFormattedTextField snowField = new JFormattedTextField(NumberFormat.getNumberInstance());
        JFormattedTextField capacityField = new JFormattedTextField(NumberFormat.getNumberInstance());
        JTextArea tilesList = new JTextArea();       
        JComboBox<String> typeList = new JComboBox<String>(types);
        JScrollPane tilesListScroll = new JScrollPane(tilesList);
        tilesListScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JButton addTile = new JButton("Add Tile");
        typeList.setSelectedIndex(0);
        tilesList.setEditable(false);
        capacityField.setValue(0);
        snowField.setValue(0);
        capacityField.setPreferredSize(new Dimension(166, 19));
        snowField.setPreferredSize(new Dimension(166, 19));
        tilesListScroll.setPreferredSize(new Dimension(166, 200));
        addTile.setPreferredSize(new Dimension(166, 19));
        tilesPanel.add(typeList, BorderLayout.NORTH);
        tilesPanel.add(snowField, BorderLayout.NORTH);
        tilesPanel.add(capacityField, BorderLayout.NORTH);
        tilesPanel.add(addTile, BorderLayout.NORTH);
        tilesPanel.add(tilesListScroll, BorderLayout.SOUTH);


        // Center Panel
        linksPanel.setLayout(new BoxLayout(linksPanel, BoxLayout.Y_AXIS));
        JComboBox<Integer> t1 = new JComboBox<Integer>(tileID.toArray(new Integer[0]));
        JComboBox<Integer> t2 = new JComboBox<Integer>(tileID.toArray(new Integer[0]));
        JButton addLink = new JButton("Add Link");
        JTextArea linksList = new JTextArea();
        linksList.setEditable(false);
        JScrollPane linksListScroll = new JScrollPane(linksList);
        addLink.setPreferredSize(new Dimension(166, 19));
        t1.setPreferredSize(new Dimension(166, 19));
        t2.setPreferredSize(new Dimension(166, 19));
        linksListScroll.setPreferredSize(new Dimension(166, 200));
        linksPanel.add(t1, BorderLayout.NORTH);
        linksPanel.add(t2, BorderLayout.NORTH);
        linksPanel.add(addLink, BorderLayout.NORTH);
        linksPanel.add(linksListScroll);


        // Right Panel 
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        JComboBox<Integer> tileSelector = new JComboBox<Integer>(tileID.toArray(new Integer[0]));
        JComboBox<String> itemSelector = new JComboBox<String>(items);
        JButton addItem = new JButton("Add Item");
        JTextArea itemsList = new JTextArea();
        itemsList.setEditable(false);
        JScrollPane itemsListScroll = new JScrollPane(itemsList);
        addItem.setPreferredSize(new Dimension(166, 19));
        tileSelector.setPreferredSize(new Dimension(166, 19));
        itemSelector.setPreferredSize(new Dimension(166, 19));
        itemsListScroll.setPreferredSize(new Dimension(166, 200));
        itemsPanel.add(tileSelector, BorderLayout.NORTH);
        itemsPanel.add(itemSelector, BorderLayout.NORTH);
        itemsPanel.add(addItem, BorderLayout.NORTH);
        itemsPanel.add(itemsListScroll);


        // OK Button
        JButton OK = new JButton("OK");

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
                t1.setModel(new DefaultComboBoxModel<Integer>(tileID.toArray(new Integer[0])));
                t2.setModel(new DefaultComboBoxModel<Integer>(tileID.toArray(new Integer[0])));
                tileSelector.setModel(new DefaultComboBoxModel<Integer>(tileID.toArray(new Integer[0])));
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
                ArrayList<String> setup = new ArrayList<String>();

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

                Interpreter.generateTilesFrom(setup, tileID.size(), link.size());
                dispose();
			}
        });

        
        tilesPanel.setPreferredSize(new Dimension(166, 300));
        linksPanel.setPreferredSize(new Dimension(166, 300));
        itemsPanel.setPreferredSize(new Dimension(166, 300));
        setupPanel.add(tilesPanel, BorderLayout.CENTER);
        setupPanel.add(linksPanel, BorderLayout.CENTER);
        setupPanel.add(itemsPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(setupPanel, BorderLayout.NORTH);
        mainPanel.add(OK, BorderLayout.SOUTH);
        add(mainPanel);
        pack();
        setVisible(true);
    }
}