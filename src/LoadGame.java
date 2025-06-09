import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LoadGame {
    private JFrame main;
    private JPanel loadPanel;
    public LoadGame(JFrame frame) {
        this.main = frame;
        this.loadPanel = new JPanel();
        this.loadPanel.setLayout(null);
        ImagePanel bg = new ImagePanel("assets\\blue-cloud.jpeg");
        bg.setBounds(0,0,main.getWidth(),main.getHeight());
        bg.setLayout(null);
        this.loadPanel.add(bg);

        JLabel title = new JLabel("Choose your account");
        title.setFont(new Font("Impact", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        title.setBounds(450,100,500,50);
        bg.add(title);

        JPanel listUser = new JPanel();
        listUser.setLayout(new BoxLayout(listUser, BoxLayout.Y_AXIS));
        listUser.setBounds(400,200,500,300);
        listUser.setOpaque(false);
        JScrollPane scroll = new JScrollPane(listUser);
        scroll.setBounds(400,200,500,300);
        scroll.getViewport().setOpaque(false);
        bg.add(scroll);
        ArrayList<User> users = new ArrayList<>();

        if(users.isEmpty()){
            JLabel noGame = new JLabel("No Game Has Been Saved Yet !");
            noGame.setFont(new Font("Impact", Font.BOLD, 25));
            noGame.setForeground(Color.WHITE);
            noGame.setAlignmentX(Component.CENTER_ALIGNMENT);
            listUser.add(noGame);
            //-balek main
        }else {
            for (User user : users) {
                JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                userPanel.setOpaque(false);
                userPanel.setMaximumSize(new Dimension(scroll.getWidth(), 50));

                JLabel userlabel = new JLabel(user.getName() + " (" + user.getLevel() + ")");
                userlabel.setFont(new Font("Impact", Font.BOLD, 20));
                userlabel.setForeground(Color.WHITE);
                userPanel.add(userlabel);
                JButton loadBtn = new JButton("Load " + user.getName());
                loadBtn.setFont(new Font("Impact", Font.BOLD, 20));
                loadBtn.setBackground(new Color(0x7FDCD2));
                loadBtn.setForeground(Color.WHITE);
                loadBtn.setPreferredSize(new Dimension(150, 50));
                loadBtn.addActionListener(e -> {
                    BattleScreen bs = new BattleScreen(user);
                    JPanel battlePanel = bs.page();
                    JPanel home = Onboard.getHome();
                    CardLayout card = Onboard.getCard();
                    home.add(battlePanel, "BattleScreen");
                    card.show(home, "BattleScreen");
                });
                userPanel.add(loadBtn);
                listUser.add(userPanel);
                listUser.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
        ImageIcon backTomain = new ImageIcon("assets\\onbrd.png");
        JButton back = new JButton(backTomain);
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.setBounds(575,550,150,75);
        back.addActionListener(e -> {
//                Onboard.getCard().show(Onboard.getHome()),"Onboard");
    });
        bg.add(back);
    }
    public JPanel page() {
        return loadPanel;
    }

    public static void main(String[] args) {
        JFrame load = new JFrame("Load Game");
        load.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        load.setSize(500, 500);
        load.setLocationRelativeTo(null);
        JPanel home = Onboard.getHome();
        load.add(home);
        LoadGame loadGameScreen = new LoadGame(load);
        home.add(loadGameScreen.page(), "LoadGame");
        load.setVisible(true);
        Onboard.getCard().show(home, "LoadGame");
    }
    }