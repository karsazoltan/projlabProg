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
            d.setSize(50, 200);
            ArrayList<JTextField> tfs = new ArrayList<>();
            d.setTitle("Enter parameters");
            d.setLayout(new GridLayout(command.getParameters()+1, 1));

            for (int i = 0; i < command.getParameters(); i++) {
                JTextField tf = new JTextField();
                tf.setSize(new Dimension( 50,20));
                tfs.add(tf);
                d.add(tf);
            }

            JButton okButton = new JButton("Ok");
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cmd = command.getCommand();
                    for (JTextField tf : tfs) {
                        cmd += " "+tf.getText();
                    }
                    d.setVisible(false);
                    Interpreter.interpretCommand(cmd);
                }
            });

            d.add(okButton);
            d.pack();
            d.setVisible(true);
        }
        else
        Interpreter.interpretCommand(command.getCommand());

        cp.updateButtons();
    }
}
