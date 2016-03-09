package gameInterface.Dialogs;

import gameInterface.TerminationWindow;
import messagingHandler.MessageSender;
import messagingHandler.Messages.BoardSizeDecided;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class StartingDialog extends JDialog implements GridLayoutSettings, TerminationWindow {

    private JFormattedTextField textField;

    public StartingDialog() {
        super();
        closingDefinition(this);
        try {
            JPanel panel = PanelModification.PreparePanel((JPanel) getContentPane());
            GridBagConstraints constr = setupConstraints();
            addLabel(panel, constr);
            JFormattedTextField textField = PanelModification.addTextField(this, panel, constr);
            textField.addActionListener(e -> {
                int boardSize = Integer.parseInt(textField.getValue().toString());
                MessageSender.send(new BoardSizeDecided(boardSize));
                dispose();
            });
            pack();
            setLocationRelativeTo(null);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void addLabel(JPanel panel, GridBagConstraints constr) {
        JLabel label = new JLabel("Welcome in the Battleships game.");
        panel.add(label,constr);
        JLabel secondLabel = new JLabel("Please, decide about board size.");
        constr.gridy++;
        panel.add(secondLabel,constr);
        JLabel thirdLabel = new JLabel("Type a number and press enter.");
        constr.gridy++;
        panel.add(thirdLabel,constr);
    }
}
