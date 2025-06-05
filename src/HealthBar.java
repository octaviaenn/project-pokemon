import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HealthBar extends JPanel {

    private int maxHp;
    private int currentHp;
    private int targetHp; // The HP value we're animating towards
    private Timer animationTimer;
    private final int ANIMATION_STEPS = 30; // Number of frames for the animation
    private final int ANIMATION_DELAY_MS = 20; // Delay between animation frames (20ms = 50 FPS)

    private int animationCurrentStep;
    private int animationStartHp; // HP at the start of the current animation

    // Colors for the health bar
    private Color healthyColor = new Color(76, 175, 80); // Green
    private Color damagedColor = new Color(255, 193, 7); // Orange/Yellow
    private Color criticalColor = new Color(244, 67, 54); // Red
    private Color bgColor = Color.DARK_GRAY; // Background of the bar

    private JLabel hpTextLabel; // For displaying "HP: X/Y"

    public HealthBar(int initialHp, int maxHp) {
        if (initialHp < 0 || maxHp <= 0 || initialHp > maxHp) {
            throw new IllegalArgumentException("Invalid HP values: initialHp=" + initialHp + ", maxHp=" + maxHp);
        }
        this.maxHp = maxHp;
        this.currentHp = initialHp;
        this.targetHp = initialHp; // Start at current HP

        // Set up the panel properties
        setPreferredSize(new Dimension(200, 30)); // Default size for the bar
        setBackground(bgColor); // Set background of the entire panel

        // Use a layout manager to position the HP text label
        setLayout(new BorderLayout());
        hpTextLabel = new JLabel(getHpText(), SwingConstants.CENTER);
        hpTextLabel.setForeground(Color.WHITE); // Text color
        hpTextLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(hpTextLabel, BorderLayout.CENTER);

        // Initialize the animation timer
        animationTimer = new Timer(ANIMATION_DELAY_MS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animateHpChange();
            }
        });
        animationTimer.setRepeats(true); // Keep repeating until stopped
    }

    // Helper to get HP text for label
    private String getHpText() {
        return "HP: " + currentHp + "/" + maxHp;
    }

    /**
     * Updates the HP to a new value and starts the animation.
     * 
     * @param newHp The HP value to animate towards.
     */
    public void setHp(int newHp) {
        // Clamp newHp between 0 and maxHp
        newHp = Math.max(0, Math.min(newHp, maxHp));

        if (newHp == targetHp) {
            // No change, or already animating to this value
            return;
        }

        // Store the target HP and the starting HP for this animation
        this.targetHp = newHp;
        this.animationStartHp = currentHp; // Current visible HP is the start point
        this.animationCurrentStep = 0; // Reset animation step

        // Start or restart the timer
        if (!animationTimer.isRunning()) {
            animationTimer.start();
        }
    }

    /**
     * Internal method to perform one step of the animation.
     */
    private void animateHpChange() {
        if (animationCurrentStep < ANIMATION_STEPS) {
            animationCurrentStep++;
            // Calculate current HP based on linear interpolation
            currentHp = animationStartHp
                    + (int) ((double) (targetHp - animationStartHp) * animationCurrentStep / ANIMATION_STEPS);

            // Update text label and repaint the bar
            hpTextLabel.setText(getHpText());
            repaint();
        } else {
            // Animation finished, ensure currentHp is exactly targetHp
            currentHp = targetHp;
            hpTextLabel.setText(getHpText());
            repaint();
            animationTimer.stop(); // Stop the timer when animation is complete
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Paints the background (bgColor)

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Calculate the width of the health portion
        double healthPercentage = (double) currentHp / maxHp;
        int barWidth = getWidth();
        int barHeight = getHeight();
        int healthFillWidth = (int) (barWidth * healthPercentage);

        // Determine health bar color based on percentage
        Color healthColor;
        if (healthPercentage > 0.6) {
            healthColor = healthyColor;
        } else if (healthPercentage > 0.25) {
            healthColor = damagedColor;
        } else {
            healthColor = criticalColor;
        }

        // Draw the health bar background (the empty part)
        g2d.setColor(new Color(50, 50, 50)); // Darker gray for empty part
        g2d.fillRect(0, 0, barWidth, barHeight);

        // Draw the filled health portion
        g2d.setColor(healthColor);
        g2d.fillRect(0, 0, healthFillWidth, barHeight);

        // Draw a border around the bar (optional)
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawRect(0, 0, barWidth - 1, barHeight - 1);

        g2d.dispose();
    }

    // Example methods for testing
    public void heal(int amount) {
        setHp(currentHp + amount);
    }

    public void takeDamage(int amount) {
        setHp(currentHp - amount);
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getCurrentHp() {
        return currentHp;
    }
}