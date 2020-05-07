package graphics;

import sumatra.Interpreter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CommandButton extends JButton implements ActionListener {
    private CommandPanel cp;
    private Command command;

    public CommandButton(Command comm, CommandPanel cp) {
        command = comm;
        this.cp = cp;
    }

    public Command getCommand() {
        return command;
    }

    public void actionPerformed(ActionEvent e) {
        if (command.getParameters() != 0) {
            JDialog d = new JDialog();
            d.setSize(200, 30 * (command.getParameters() + 2));
            d.setTitle("Enter parameters");
            d.setLayout(new GridLayout(0, 1));

            ArrayList<JTextField> tfs = new ArrayList<>();
            for (int i = 0; i < command.getParameters(); i++) {
                JTextField tf = new JTextField();
                tf.setSize(new Dimension(200,30));
                tfs.add(tf);
                d.add(tf);
            }

            JButton okButton = new JButton("Ok");
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    StringBuilder cmd = new StringBuilder(command.getCommand());
                    for (JTextField tf : tfs) {
                        cmd.append(" ").append(tf.getText());
                    }
                    Interpreter.interpretCommand(cmd.toString());
                    cp.updateButtons();
                    d.dispose();
                }
            });

            d.add(okButton);
            d.setLocationRelativeTo(null);
            d.setVisible(true);
        }
        else {
            Interpreter.interpretCommand(command.getCommand());
            cp.updateButtons();
        }
    }
}
