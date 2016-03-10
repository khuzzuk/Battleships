package gameInterface.buttons;

import board.fields.Field;
import board.fields.Sign;
import gameInterface.RenderingOptions;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

public class FieldButton extends JButton implements RenderingOptions {
    private Sign sign;
    private static Rectangle rectangle = new Rectangle(0,0, VisibleItem.itemSize, VisibleItem.itemSize);
    private static GeneralPath xSign = getxSign();

    private static GeneralPath getxSign() {
        GeneralPath g = new GeneralPath();
        g.moveTo(0,0);
        g.lineTo(VisibleItem.itemSize, VisibleItem.itemSize);
        g.moveTo(VisibleItem.itemSize,0);
        g.lineTo(0, VisibleItem.itemSize);
        return g;
    }

    public FieldButton(Field field, int x, int y) {
        super();
        xSign = getxSign();
        rectangle = new Rectangle(0,0, VisibleItem.itemSize, VisibleItem.itemSize);
        sign = field.getStatus();
        int menuSize = VisibleItem.itemSize +2;
        setBounds(x*menuSize, y*menuSize, menuSize,menuSize);
    }
    public boolean isFieldUsable(){
        return sign==Sign.EMPTY || sign==Sign.SHIP;
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
            int size = VisibleItem.itemSize;
            g2.fill(new Ellipse2D.Float(size/3,size/3,size/2,size/2));
        }
    }
    protected void paintBorder(Graphics g) {
    }
}
