package graphics;

import sumatra.Interpreter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CommandPanel extends JPanel {
    private ArrayList<CommandButton> commandButtons;

    private static CommandPanel instance = new CommandPanel();
    public static CommandPanel getInstance() { return instance; }

    private CommandPanel() {
        setLayout(new GridLayout(0, 1));
        commandButtons = new ArrayList<>();
        updateButtons();
    }

    public void setButtons(List<Command> c) {
        if (!commandButtons.isEmpty())
            commandButtons.clear();

        this.removeAll();

        for (Command comm : c) {
            commandButtons.add(new CommandButton(comm, this));
        }

        for (CommandButton cb : commandButtons) {
            cb.addActionListener(cb);
            cb.setText(cb.getCommand().getMessage());
            this.add(cb);
        }

        setPreferredSize(new Dimension(250, c.size() * 40));
        revalidate();
    }

    public void updateButtons() {
        setButtons(Interpreter.validCommands());
    }
}
