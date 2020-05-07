package graphics;

import sumatra.Interpreter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CommandPanel extends JPanel {
    private ArrayList<CommandButton> commandButtons;

    public CommandPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        commandButtons = new ArrayList<>();
        updateButtons();
    }

    public void setButtons(List<Command> c) {
        if (!commandButtons.isEmpty())
            commandButtons.clear();

        for (Command comm : c) {
            commandButtons.add(new CommandButton(comm.getCommand(), this));
        }

        this.removeAll();

        for (CommandButton cb : commandButtons) {
            cb.addActionListener(cb);
            cb.setText(cb.getCommand());
            this.add(cb);
        }
        revalidate();
    }

    public void updateButtons() {
        List<Command> commands = new ArrayList<>();
        commands = Interpreter.validCommands();

        setButtons(commands);
    }
}
