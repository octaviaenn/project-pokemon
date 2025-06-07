import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage {
    private JPanel mainPanel;
    private JButton storeButton;
    private JButton characterButton;
    private JButton battleButton;
    private JButton homeButton;
    private JFrame frame;

    private Store storePage;

    public Homepage(JFrame frame) {
        this.frame = frame;
        Character.define();
        mainPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("assets\\blue-cloud.jpeg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BorderLayout());
        JPanel topButtonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        ImageIcon store = new ImageIcon("assets\\store.png");
        Image sg = store.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        ImageIcon stx = new ImageIcon(sg);
        storeButton = new JButton(stx);
        storeButton.setBorderPainted(false);
        storeButton.setFocusPainted(false);
        storeButton.setContentAreaFilled(false);

        ImageIcon character = new ImageIcon("assets\\chara.png");
        Image ig = character.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        ImageIcon chx = new ImageIcon(ig);
        characterButton = new JButton(chx);
        characterButton.setBorderPainted(false);
        characterButton.setFocusPainted(false);
        characterButton.setContentAreaFilled(false);

        ImageIcon battle = new ImageIcon("assets\\battle.png");
        Image img = battle.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        ImageIcon btx = new ImageIcon(img);
        battleButton = new JButton(btx);
        battleButton.setBorderPainted(false);
        battleButton.setFocusPainted(false);
        battleButton.setContentAreaFilled(false);

        ImageIcon home = new ImageIcon("assets\\onbrd.png");
        Image imd = home.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
        ImageIcon hx = new ImageIcon(imd);
        homeButton = new JButton(hx);
        homeButton.setBorderPainted(false);
        homeButton.setFocusPainted(false);
        homeButton.setContentAreaFilled(false);


//        ImageIcon coin = new ImageIcon("assets\\coin.png");
//        Image cc = coin.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
//        ImageIcon stx = new ImageIcon(sg);
//        storeButton = new JButton(stx);
//        storeButton.setBorderPainted(false);
//        storeButton.setFocusPainted(false);
//        storeButton.setContentAreaFilled(false);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(homeButton);
        topButtonPanel.setOpaque(false);
        storeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (storePage == null) {
                    storePage = new Store(frame);
                }
                frame.setContentPane(storePage.getPanel());
                frame.revalidate();
                frame.repaint();
            }
        });

        characterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCharacterSelection();
            }
        });

        battleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openBattle();
            }
        });

        topButtonPanel.add(storeButton);
        topButtonPanel.add(characterButton);
        topButtonPanel.add(battleButton);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goHome();
            }
        });
        mainPanel.add(topButtonPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }
    private void openCharacterSelection() {

    }
    private void openBattle() {
        JOptionPane.showMessageDialog(mainPanel, "Battle started");
    }
    private void goHome() {
        Onboard.getCard().show(Onboard.getHome(), "Onboard");
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Homepage");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Homepage Hp = new Homepage(frame);
                frame.setContentPane(Hp.getMainPanel());
                frame.setSize(1400, 750);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}