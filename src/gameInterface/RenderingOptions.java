package gameInterface;

import java.awt.*;

/**
 * Created by Adrian_Drabik on 2/25/2016.
 */
public interface RenderingOptions{
    default Graphics2D setRendering(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        return g2;
    }

}
