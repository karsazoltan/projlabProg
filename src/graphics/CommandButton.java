package graphics;

import sumatra.Interpreter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommandButton extends JButton implements ActionListener {
    private String command;

    public CommandButton(String comm) {
        command = comm;
    }

    public String getCommand() {
        return command;
    }

    public void actionPerformed(ActionEvent e) {
        Interpreter.interpretCommand(command);

    }
}
