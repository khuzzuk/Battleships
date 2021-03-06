package gameInterface.buttons;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Adrian on 19.02.2016.
 */
public class EmptyFieldButton extends JButton {
    protected Point position;
    protected static Color backgroundColor = Color.WHITE;
    protected static Color borderColor = Color.BLACK;
    protected static Color activatedBorderColor = Color.GREEN;
    public static Dimension fieldSize;
    private Rectangle rectangle;

    private EmptyFieldButton() {}
    public EmptyFieldButton(Point position) {
        setOpaque(true);
        relocate(position);
    }
    public void relocate(Point position){
        fieldSize = new Dimension(VisibleItem.itemSize, VisibleItem.itemSize);
        this.position = position;
        setBounds(new Rectangle(position,fieldSize));
        rectangle = new Rectangle(0, 0, VisibleItem.itemSize -2, VisibleItem.itemSize -2);
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
