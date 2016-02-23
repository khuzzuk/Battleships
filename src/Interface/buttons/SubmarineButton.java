package Interface.buttons;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by adrabik on 22.02.16.
 */
public class SubmarineButton extends ShipButton {
    public SubmarineButton(Point point) {
        super(point);
        try {
            image = ImageIO.read(new File(getClass().getClassLoader().getResource("SS.png").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        shipSize = new Dimension(PlaceableItem.ITEM_SIZE+1, PlaceableItem.ITEM_SIZE+1);
        relocate(position);
    }
}
