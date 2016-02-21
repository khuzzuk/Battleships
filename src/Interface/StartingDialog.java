package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;

/**
 * Created by Adrian on 19.02.2016.
 */
public class StartingDialog extends JDialog {

    private JFormattedTextField textField;

    public StartingDialog() {
        super();
        JPanel panel = setupJPanel();
        GridBagConstraints constr = setupConstraints();
        addLabel(panel, constr);
        addTextField(panel, constr);
        pack();
    }

    private void addTextField(JPanel panel, GridBagConstraints constr) {
        textField = new JFormattedTextField(NumberFormat.getNumberInstance());
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(textField.getValue().toString());
                dispose();
                //Main.startGame();
            }
        });
        constr.gridy=2;
        panel.add(textField,constr);
    }

    private void addLabel(JPanel panel, GridBagConstraints constr) {
        JLabel label = new JLabel("Welcome in the Battleships game.");
        constr.gridy=0;
        constr.gridx=0;
        panel.add(label,constr);
        JLabel secondLabel = new JLabel("Please, decide about board size.");
        constr.gridy = 1;
        panel.add(secondLabel,constr);
    }

    private GridBagConstraints setupConstraints() {
        GridBagConstraints constr = new GridBagConstraints();
        constr.weightx=0;
        constr.weighty=50;
        constr.fill = GridBagConstraints.HORIZONTAL;
        constr.insets = new Insets(5,5,5,5);
        return constr;
    }

    private JPanel setupJPanel() {
        JPanel panel = (JPanel) getContentPane();
        GridBagLayout mainLayout = new GridBagLayout();
        panel.setLayout(mainLayout);
        return panel;
    }
}
