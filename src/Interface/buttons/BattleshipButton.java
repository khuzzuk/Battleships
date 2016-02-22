package Interface.buttons;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by adrabik on 22.02.16.
 */
public class BattleshipButton extends ShipButton {
    private BufferedImage image;
    public BattleshipButton(Point point) {
        super(point);
        try {
            image = ImageIO.read(new File(getClass().getClassLoader().getResource("BB.png").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        shipSize = new Dimension(rectangleSize*4,rectangleSize);
        relocate(position);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = setRendering(g);
        g2.drawImage(image,0,0,shipSize.width-1,shipSize.height-1,null);
        g2.setColor(Color.black);
        g2.draw(rectangle);
    }
}
