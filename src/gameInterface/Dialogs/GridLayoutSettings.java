package gameInterface.Dialogs;

import java.awt.*;

/**
 * Created by adria on 25.02.2016.
 */
public interface GridLayoutSettings {
    default GridBagConstraints setupConstraints() {
        GridBagConstraints constr = new GridBagConstraints();
        constr.weightx=0;
        constr.weighty=50;
        constr.fill = GridBagConstraints.HORIZONTAL;
        constr.insets = new Insets(5,5,5,5);
        constr.gridx=0;
        constr.gridy=0;
        return constr;
    }
}
