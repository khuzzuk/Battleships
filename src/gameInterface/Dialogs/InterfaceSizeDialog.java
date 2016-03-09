package gameInterface.Dialogs;

import gameInterface.ShipPlacementWindow;
import gameInterface.buttons.VisibleItem;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;

public class InterfaceSizeDialog extends JFrame implements GridLayoutSettings {
    public InterfaceSizeDialog() {
        super("Choose interface size");
        GridBagConstraints constr = setupConstraints();
        JPanel panel = PanelModification.PreparePanel(new JPanel());
        add(panel);
        panel.add(new JLabel("Choose new interface size in pixels:"), constr);
        constr.gridy++;
        try {
            JFormattedTextField textField = PanelModification.addTextField(this,panel,constr);
            textField.addActionListener((e)->{
                VisibleItem.setItemSize(Integer.parseInt(textField.getValue().toString()));
                ShipPlacementWindow.shipPlacementWindow.remake();
            });
        } catch (ParseException e) {
            e.printStackTrace();
        }
        setLocationRelativeTo(null);
        pack();
    }
}
