package Interface.buttons;

import Interface.Orientation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by adrabik on 22.02.16.
 */
public class BattleshipButton extends ShipButton {
    public BattleshipButton(Point point) {
        super(point);
        shipSize = new Dimension(PlaceableItem.ITEM_SIZE *4+1, PlaceableItem.ITEM_SIZE+1);
        relocate(position);
    }

    @Override
    protected void loadImage() {
        try {
            image = ImageIO.read(new File(getClass().getClassLoader().getResource("BB.png").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
