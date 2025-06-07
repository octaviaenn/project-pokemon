import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Store {
    private JPanel mainPanel;
    static User user;
    private Character character;
    private static final int potionPrice=500;
    private static final int hpPrice=500;
    private static final int skillUp=500;
    private static CardLayout card;
    private JPanel frem;
    private JFrame frame;

    public Store(JFrame frame){
        this.frame = frame;
        frem = new JPanel();
        frame.add(frem);
        frem.setSize(1400, 750);
        card = new CardLayout();
        frem.setLayout(card);
        ImagePanel bg = new ImagePanel("assets\\blue-cloud.jpeg");
        bg.setLayout(new BoxLayout(bg, BoxLayout.Y_AXIS));
        frem.add(bg, "Onboard");
        ImageIcon title = new ImageIcon("assets\\charmon-store.png");
        JLabel titlee = new JLabel(title);
        titlee.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlee.setBounds(400, 50, title.getIconWidth(), title.getIconHeight());
        bg.add(titlee);

        ImageIcon posion = new ImageIcon("assets\\buyPotion.png");
        JButton buypot = new JButton(posion);
        buypot.setBorderPainted(false);
        buypot.setContentAreaFilled(false);
        buypot.setAlignmentX(Component.CENTER_ALIGNMENT);
        buypot.setBounds(300,300,50,50);
        bg.add(buypot);
        buypot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(frem, "NewGame");
            }
        });

        ImageIcon skillUp = new ImageIcon("assets\\upSkill.png");
        JButton upgred = new JButton(skillUp);
        upgred.setBorderPainted(false);
        upgred.setContentAreaFilled(false);
        bg.add(upgred);

        ImageIcon baack = new ImageIcon("assets\\back.png");
        JButton back = new JButton(baack);
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.setBounds(200,200,10,10);
        bg.add(back);

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
//
////    public Store(User user,Character character){
////        this.user=user;
////        this.character=character;
////    }
//
//    public Store(User user) {
//
//    }

    public Store() {
        mainPanel = new JPanel();{
            mainPanel.setOpaque(false);
        Image bg = new ImageIcon("assets\\blue-cloud.jpeg").getImage();
            mainPanel.setLayout(new CardLayout());
            JPanel topPanel = new JPanel(new CardLayout(0,0));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
    }
        mainPanel.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new CardLayout(0,0));
        topPanel.setOpaque(false);
        topPanel.setBackground(Color.BLUE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(topPanel, BorderLayout.NORTH);

    }

    public static void buyPotion(){
        if(user.expend(potionPrice)){
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
    public JPanel getPanel() { return frem; }
}
