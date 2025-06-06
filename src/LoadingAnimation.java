import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

public class LoadingAnimation extends JPanel {

    private BufferedImage discImage; // This will now hold the SCALED image
    private double angle = 0; // Current rotation angle
    private Timer animationTimer;
    private String imagePath; // Store the original path

    // --- New Constructor: accepts imagePath, targetWidth, 153 ---
    public LoadingAnimation(String imagePath) {
        this.imagePath = imagePath;
        // Set the preferred size of the panel to the desired display size of the image
        setPreferredSize(new Dimension(102, 153));
        setOpaque(false); // Keeps background transparent

        BufferedImage originalDiscImage = null; // Temporary variable for the original loaded image
        try {
            originalDiscImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading original disc image: " + imagePath);
            // Fallback: originalDiscImage remains null, drawing fallback circle
        }

        // --- Scale the loaded image to fit the panel's preferred size ---
        if (originalDiscImage != null) {
            this.discImage = scaleBufferedImage(originalDiscImage);
        } else {
            this.discImage = null; // No scaled image if original failed to load
        }

        // Initialize the timer for animation
        animationTimer = new Timer(50, e -> { // Update every 50 milliseconds (20 frames per second)
            angle += 5; // Rotate by 5 degrees per frame
            if (angle >= 360) {
                angle = 0; // Reset angle
            }
            repaint(); // Request a repaint to show the new rotation
        });
    }

    // --- Helper method to scale a BufferedImage (as discussed before) ---
    private BufferedImage scaleBufferedImage(BufferedImage originalImg) {
        if (originalImg == null || 102 <= 0 || 153 <= 0) {
            return null;
        }

        BufferedImage scaledImg = new BufferedImage(102, 153, originalImg.getType());
        Graphics2D g2d = scaledImg.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawImage(originalImg, 0, 0, 102, 153, null);
        g2d.dispose();
        return scaledImg;
    }

    public void startAnimation() {
        if (animationTimer != null && !animationTimer.isRunning()) {
            animationTimer.start();
        }
    }

    public void stopAnimation() {
        if (animationTimer != null && animationTimer.isRunning()) {
            animationTimer.stop();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create(); // Create a copy of Graphics context

        // Get center of the panel (which is now the 102/Height)
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        if (discImage != null) {
            // Since discImage is already scaled to getWidth()/getHeight(),
            // we just need to calculate its top-left position to center it.
            // (getWidth() and getHeight() here are the panel's actual current dimensions,
            // which should match 102/Height if layout manager is honoring preferredSize)
            int imgX = centerX - discImage.getWidth() / 2;
            int imgY = centerY - discImage.getHeight() / 2;

            // Apply rotation transform around the image's center
            AffineTransform transform = new AffineTransform();
            transform.translate(centerX, centerY); // Move origin to center of rotation
            transform.rotate(Math.toRadians(angle)); // Apply rotation
            transform.translate(-discImage.getWidth() / 2, -discImage.getHeight() / 2); // Move back to draw image at
                                                                                        // top-left relative to new
                                                                                        // origin

            g2d.drawImage(discImage, transform, this); // Draw the transformed (scaled and rotated) image
        } else {
            // Fallback if image failed to load or is null
            g2d.setColor(Color.RED); // Draw a red circle
            g2d.fillOval(centerX - 50, centerY - 50, 100, 100);
            g2d.setColor(Color.WHITE);
            g2d.drawString("Loading...", centerX - 30, centerY);
        }

        g2d.dispose(); // Release resources used by the Graphics2D copy
    }

    @Override
    public void addNotify() {
        super.addNotify();
        startAnimation();
    }

    @Override
    public void removeNotify() {
        super.removeNotify();
        stopAnimation();
    }
}