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
    private User user;
    private CardLayout mainCard;
    private JPanel home;
    private static boolean isReady = false;
    private LoadingAnimation loading = new LoadingAnimation("assets\\disc.png");
    private static LoadingAnimation loadingBattle = new LoadingAnimation("assets\\disc.png");
    private static BattleScreen battleScreen;

    private Store storePage;

    public Homepage(JFrame frame, User user) {
        mainCard = Onboard.getCard();
        home = Onboard.getHome();
        this.frame = frame;
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
        Image sg = store.getImage().getScaledInstance(150, 75, Image.SCALE_SMOOTH);
        ImageIcon stx = new ImageIcon(sg);
        storeButton = new JButton(stx);
        storeButton.setBorderPainted(false);
        storeButton.setFocusPainted(false);
        storeButton.setContentAreaFilled(false);

        // ImageIcon character = new ImageIcon("assets\\chara.png");
        // Image ig = character.getImage().getScaledInstance(150, 75,
        // Image.SCALE_SMOOTH);
        // ImageIcon chx = new ImageIcon(ig);
        // characterButton = new JButton(chx);
        // characterButton.setBorderPainted(false);
        // characterButton.setFocusPainted(false);
        // characterButton.setContentAreaFilled(false);

        ImageIcon battle = new ImageIcon("assets\\battle.png");
        Image img = battle.getImage().getScaledInstance(150, 75, Image.SCALE_SMOOTH);
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

        // ImageIcon coin = new ImageIcon("assets\\coin.png");
        // Image cc = coin.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
        // ImageIcon stx = new ImageIcon(sg);
        // storeButton = new JButton(stx);
        // storeButton.setBorderPainted(false);
        // storeButton.setFocusPainted(false);
        // storeButton.setContentAreaFilled(false);

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

        // characterButton.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // openCharacterSelection();
        // }
        // });

        battleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openBattle();
            }
        });

        topButtonPanel.add(storeButton);
        // topButtonPanel.add(characterButton);
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
    // private void openCharacterSelection() {

    // }
    private void openBattle() {

        addBattleScreen();
        addKarakterPokemon();

        // JOptionPane.showMessageDialog(mainPanel, "Battle started");
    }

    private void goHome() {
        Onboard.getCard().show(Onboard.getHome(), "Onboard");
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void addKarakterPokemon() {
        new SwingWorker<JPanel, Void>() {
            @Override
            protected JPanel doInBackground() throws Exception {
                loading.setBounds(650, 200, 102, 153);
                mainPanel.add(loading);
                loading.startAnimation();
                KarakterPokemon choose = new KarakterPokemon(user);
                return choose; // Get the actual JPanel
            }

            @Override
            protected void done() {
                try {
                    JPanel choose = get();
                    System.out.println("sukses nambahin KarakterPokemon");
                    loading.stopAnimation();
                    home.add(choose, "KarakterPokemon");
                    mainCard.show(home, "KarakterPokemon");
                    // parentFrame.pack();
                    // parentFrame.revalidate(); // Essential to ensure layout updates
                    // parentFrame.repaint(); // Essential to ensure visual updates

                } catch (InterruptedException | java.util.concurrent.ExecutionException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(Main.frame,
                            "Error loading Battle Screen: " + e.getMessage(),
                            "Loading Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute(); // Start the SwingWorker
    }

    private void addBattleScreen() {
        new SwingWorker<BattleScreen, Void>() {
            @Override
            protected BattleScreen doInBackground() throws Exception {
                BattleScreen battleScreen = new BattleScreen(user);
                return battleScreen; // Get the actual JPanel
            }

            @Override
            protected void done() {
                try {
                    isReady = true;
                    battleScreen = get();
                    System.out.println("sukses nambahin battlescreen");
                    loadingBattle.stopAnimation();
                    // home.add(battleScreen, "BattleScreen");
                    // mainCard.show(home, "BattleScreen");
                    // parentFrame.pack();
                    // parentFrame.revalidate(); // Essential to ensure layout updates
                    // parentFrame.repaint(); // Essential to ensure visual updates

                } catch (InterruptedException | java.util.concurrent.ExecutionException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(Main.frame,
                            "Error loading Battle Screen: " + e.getMessage(),
                            "Loading Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute(); // Start the SwingWorker
    }

    public static boolean getIsReady() {
        return isReady;
    }

    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(new Runnable() {
    // @Override
    // public void run() {
    // JFrame frame = new JFrame("Homepage");
    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Homepage Hp = new Homepage(frame);
    // frame.setContentPane(Hp.getMainPanel());
    // frame.setSize(1400, 750);
    // frame.setLocationRelativeTo(null);
    // frame.setVisible(true);
    // }
    // });
    // }
}