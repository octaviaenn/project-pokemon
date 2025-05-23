import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 800);
        Homepage homepage = new Homepage(frame);
        //frame.add(homepage);
        frame.setVisible(true);
    }
}
