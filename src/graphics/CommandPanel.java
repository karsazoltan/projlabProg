package graphics;

import sumatra.Interpreter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Az akciópanelt reprezentáló esemény
 */
public class CommandPanel extends JPanel {
    private ArrayList<CommandButton> commandButtons;

    private static CommandPanel instance = new CommandPanel();
    public static CommandPanel getInstance() { return instance; }

    /**
     * Default konstruktor
     */
    private CommandPanel() {
        setLayout(new GridLayout(0, 1));
        commandButtons = new ArrayList<>();
        updateButtons();
    }

    /**
     * Az akció gombokat létrehozó és beállító függvény
     * @param c Parancslista ami alapján a gombokat létrehozzuk
     */
    public void setButtons(List<Command> c) {
        if (!commandButtons.isEmpty())
            commandButtons.clear();

        this.removeAll();

        for (Command comm : c) {
            commandButtons.add(new CommandButton(comm));
        }

        for (CommandButton cb : commandButtons) {
            cb.addActionListener(cb);
            cb.setText(cb.getCommand().getMessage());
            this.add(cb);
        }

        setPreferredSize(new Dimension(250, c.size() * 40));
        revalidate();
    }

    /**
     * A gombok frissítését elindító függvény
     */
    public void updateButtons() {
        setButtons(Interpreter.validCommands());
    }
}
