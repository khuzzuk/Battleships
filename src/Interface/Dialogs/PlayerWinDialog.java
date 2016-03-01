package Interface.Dialogs;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Adrian on 19.02.2016.
 */
public class PlayerWinDialog extends JDialog implements GridLayoutSettings {

    private JButton okButton;

    public PlayerWinDialog(int playerNumber) {
        super();
        setModal(true);
        JPanel panel = setupJPanel();
        GridBagConstraints constr = setupConstraints();
        addLabel(panel, constr, playerNumber);
        okButton(panel, constr);
        setLocationRelativeTo(null);
        pack();
    }

    private void okButton(JPanel panel, GridBagConstraints constr) {
        okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            dispose();
            System.exit(0);
        });
        constr.gridy=2;
        panel.add(okButton,constr);
    }

    private void addLabel(JPanel panel, GridBagConstraints constr, int playerNumber) {
        JLabel label = new JLabel("Gracz " + playerNumber + " wygrał.");
        constr.gridy=0;
        constr.gridx=0;
        panel.add(label,constr);
        JLabel secondLabel = new JLabel("Gratulacje!");
        constr.gridy = 1;
        panel.add(secondLabel,constr);
    }

    private JPanel setupJPanel() {
        JPanel panel = (JPanel) getContentPane();
        GridBagLayout mainLayout = new GridBagLayout();
        panel.setLayout(mainLayout);
        return panel;
    }
}
