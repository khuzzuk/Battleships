package Interface;

import Interface.buttons.PlaceableItem;
import game.Field;
import game.Sign;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

public class FieldButton extends JButton implements RenderingOptions {
    private Sign sign;
    private static Rectangle rectangle = new Rectangle(0,0,PlaceableItem.ITEM_SIZE, PlaceableItem.ITEM_SIZE);
    private static GeneralPath xSign = getxSign();

    private static GeneralPath getxSign() {
        GeneralPath g = new GeneralPath();
        g.moveTo(0,0);
        g.lineTo(PlaceableItem.ITEM_SIZE,PlaceableItem.ITEM_SIZE);
        g.moveTo(PlaceableItem.ITEM_SIZE,0);
        g.lineTo(0,PlaceableItem.ITEM_SIZE);
        return g;
    }

    public FieldButton(Field field, int x, int y) {
        super();
        sign = field.getStatus();
        int menuSize = PlaceableItem.ITEM_SIZE+2;
        setBounds(x*menuSize, y*menuSize, menuSize,menuSize);
    }
    public boolean contains(int x, int y) {
        return rectangle.contains(x, y);
    }
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = setRendering(g);
        g2.draw(rectangle);
        if (sign==Sign.EMPTY || sign==Sign.SHIP){
            g2.setColor(Color.white);
            g2.fill(rectangle);
            g2.setColor(Color.black);
            if (getMousePosition()!=null) g2.setColor(Color.RED);
            g2.draw(rectangle);
        }
        else if (sign==Sign.HIT){
            g2.setColor(Color.RED);
            g2.fill(rectangle);
            g2.setColor(Color.black);
            g2.draw(rectangle);
            g2.draw(xSign);
        }
        else {
            g2.setColor(Color.GRAY);
            g2.fill(rectangle);
            g2.setColor(Color.BLACK);
            g2.draw(rectangle);
            int size = PlaceableItem.ITEM_SIZE/4;
            g2.fill(new Ellipse2D.Float(size,size,size,size));
        }
    }
    protected void paintBorder(Graphics g) {
    }
}
