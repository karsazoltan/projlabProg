package graphics;

import sumatra.Interpreter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommandButton extends JButton implements ActionListener {
    private CommandPanel cp;
    private String command;

    public CommandButton(String comm, CommandPanel cp) {
        command = comm;
        this.cp = cp;
    }

    public String getCommand() {
        return command;
    }

    public void actionPerformed(ActionEvent e) {
        Interpreter.interpretCommand(command);
        cp.updateButtons();
    }
}
