import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Store {
    private JPanel mainPanel;
    static User user;
    private Character character;
    private static final int potionPrice = 500;
    private static final int hpPrice = 500;
    private static final int skillUp = 500;
    private static CardLayout card;
    private JPanel frem;
    private JFrame frame;

    public Store(JFrame frame) {
        this.frame = frame;
        frem = new JPanel();
        frame.add(frem);
        frem.setSize(1400, 750);
        card = new CardLayout();
        frem.setLayout(card);
        
        ImagePanel bg = new ImagePanel("assets\\blue-cloud.jpeg");
        bg.setLayout(new BorderLayout()); 
        frem.add(bg, "Onboard");

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 25)); // Padding: atas=10, kiri=10, bawah=0, kanan=20
        
        JPanel coinPanel = new JPanel();
        coinPanel.setOpaque(false); 
        coinPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        coinPanel.setPreferredSize(new Dimension(100, 40)); 
        
        ImageIcon coinIcon = new ImageIcon("assets\\coin.png"); 
        Image scaledCoin = coinIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH); 
        JLabel coinLabel = new JLabel(new ImageIcon(scaledCoin));
        coinLabel.setText("1000");
        coinLabel.setForeground(Color.BLACK); 
        coinLabel.setFont(new Font("Arial", Font.BOLD, 20));
        coinLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        coinLabel.setVerticalTextPosition(SwingConstants.CENTER);
        coinLabel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        coinPanel.add(coinLabel);
        
        topPanel.add(coinPanel);
        bg.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        bg.add(centerPanel, BorderLayout.CENTER);

        ImageIcon title = new ImageIcon("assets\\charmon-store.png");
        Image scaledTitle = title.getImage().getScaledInstance(title.getIconWidth() / 2, title.getIconHeight() / 2, Image.SCALE_SMOOTH);
        JLabel titlee = new JLabel(new ImageIcon(scaledTitle));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 40, 0);
        centerPanel.add(titlee, gbc);

        ImageIcon posion = new ImageIcon("assets\\buyPotion.png");
        JButton buypot = new JButton(posion);
        buypot.setBorderPainted(false);
        buypot.setContentAreaFilled(false);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        centerPanel.add(buypot, gbc);
        buypot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(frem, "NewGame");
            }
        });

        ImageIcon skillUpIcon = new ImageIcon("assets\\upSkill.png");
        JButton upgred = new JButton(skillUpIcon);
        upgred.setBorderPainted(false);
        upgred.setContentAreaFilled(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 10, 0);
        centerPanel.add(upgred, gbc);

        ImageIcon baack = new ImageIcon("assets\\back.png");
        Image scaledBack = baack.getImage().getScaledInstance(baack.getIconWidth() / 2, baack.getIconHeight() / 2, Image.SCALE_SMOOTH);
        JButton back = new JButton(new ImageIcon(scaledBack));
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 0, 20, 0);
        centerPanel.add(back, gbc);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Homepage hp = new Homepage(frame);
                frame.setContentPane(hp.getMainPanel());
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    public Store() {
        mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        Image bg = new ImageIcon("assets\\blue-cloud.jpeg").getImage();
        mainPanel.setLayout(new CardLayout());
        JPanel topPanel = new JPanel(new CardLayout(0, 0));
        topPanel.setOpaque(false);
        topPanel.setBackground(Color.BLUE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(topPanel, BorderLayout.NORTH);
    }

    public static void buyPotion() {
        if (user != null && user.expend(potionPrice)) {
            JOptionPane.showMessageDialog(null, "Potion has been purchased!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Store");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                new Store(frame);
                frame.setSize(1400, 750);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public JPanel getPanel() {
        return frem;
    }
}