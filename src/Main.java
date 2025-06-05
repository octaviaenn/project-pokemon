import java.awt.*;
import javax.swing.*;

public class Main {
    public static JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame = new JFrame();
                Character.define();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(1400, 750);
                frame.setLocationRelativeTo(null);
                Onboard onboard = new Onboard(frame);
                // frame.add(homepage);
                frame.setVisible(true);
            }
        });
    }

    public static JFrame getFrame() {
        return frame;
    }
}