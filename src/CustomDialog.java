import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

public class CustomDialog extends JDialog {
    private BufferedImage backgroundImage;
    private Point offset;
    private JFrame frame;
    private JPanel backgroundPanel;

    public CustomDialog(String path, int a, int b) {
        super(Main.getFrame(), true);
        setUndecorated(true); // No window border
        frame = Main.getFrame();

        try {
            // Load your custom PNG with transparent background
            backgroundImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setSize(backgroundImage.getWidth(), backgroundImage.getHeight());

        // Optional: Apply rounded or custom shape based on image alpha
        setShape(getImageShape(backgroundImage));

        // Make background transparent
        setBackground(new Color(0, 0, 0, 0));

        // // Allow dragging the dialog
        addMouseListener(new MouseAdapter() {
            // Point offset;

            public void mousePressed(MouseEvent e) {
                offset = e.getPoint();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point p = e.getLocationOnScreen();
                setLocation(p.x - offset.x, p.y - offset.y);
            }
        });

        // Add custom painted panel
        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, null);
            }

            @Override
            public boolean isOpaque() {
                return false;
            }
        };

        backgroundPanel.setLayout(null);
        JButton x = new JButton(new ImageIcon("assets\\x-btn.png"));
        x.setBounds(a, b, 50, 50);
        x.setBorderPainted(false);
        x.setContentAreaFilled(false);
        // child.setOpaque(false);

        // backgroundPanel.add(child);
        backgroundPanel.add(x);
        setContentPane(backgroundPanel);

        setLocationRelativeTo(Main.frame);

        x.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    private Shape getImageShape(BufferedImage image) {
        Area area = new Area();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int alpha = (image.getRGB(x, y) >> 24) & 0xff;
                if (alpha > 0) {
                    area.add(new Area(new Rectangle(x, y, 1, 1)));
                }
            }
        }
        return area;
    }

    public void addButton(JButton button) {
        backgroundPanel.add(button);
    }

    public void addLabel(JLabel label) {
        backgroundPanel.add(label);
    }

    // public static void main(String[] args) {
    // JFrame frame = new JFrame();
    // frame.setUndecorated(true);
    // frame.setSize(0, 0);
    // frame.setLocationRelativeTo(null);
    // frame.setVisible(true);
    //
    // CustomDialog dialog = new CustomDialog(frame);
    // dialog.setVisible(true);
    // }
}
