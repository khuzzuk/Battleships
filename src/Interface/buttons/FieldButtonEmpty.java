package Interface.buttons;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Adrian on 19.02.2016.
 */
public class FieldButtonEmpty extends JButton implements PlaceableItem {
    protected Point position;
    protected static Color backgroundColor = Color.WHITE;
    protected static Color borderColor = Color.BLACK;
    protected static Color activatedBorderColor = Color.GREEN;
    public static final Dimension fieldSize = new Dimension(20,20);
    private Rectangle rectangle;

    private FieldButtonEmpty() {}
    public FieldButtonEmpty(Point position) {
        setOpaque(true);
        this.position=position;
        setBounds(new Rectangle(position, fieldSize));
        rectangle = new Rectangle(0, 0, fieldSize.width, fieldSize.height);
        setPreferredSize(fieldSize);
    }

    @Override
    public boolean contains(int x, int y) {
        return rectangle.contains(x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(backgroundColor);
        g2.fill(rectangle);
        if (getMousePosition()!=null) g2.setColor(activatedBorderColor);
        else g2.setColor(borderColor);
        g2.draw(rectangle);
    }

    @Override
    protected void paintBorder(Graphics g) {
    }

}
