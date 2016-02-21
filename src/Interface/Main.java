package Interface;


import javax.swing.*;

/**
 * Created by Adrian on 19.02.2016.
 */
public class Main {
    public static void main2(String[] args) {
        StartingDialog dialog = new StartingDialog();
        dialog.setVisible(true);
    }
    public static void main(String[] args){
        MainWindow frame = new MainWindow();
        frame.setVisible(true);
    }
}
