import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LoadGame {
    private JPanel mainPanel;
    private JButton homeButton;
    private JFrame frame;
    private User user;
    private CardLayout mainCard;
    private JPanel home;
    private JButton loadButton;
    private JButton selectedAcc=null;
    private String selectedUsn = "";

    public LoadGame(JFrame frame, User user) {
        mainCard = Onboard.getCard();
        home = Onboard.getHome();
        this.frame = frame;
        this.user = user;
        mainPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("assets\\blue-cloud.jpeg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        topPanel.setOpaque(false);
        bottomPanel.setOpaque(false);
//        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));

        JLabel selectUser = new JLabel();
        selectUser.setOpaque(false);
        selectUser.setFont(new Font("Arial", Font.BOLD, 40));
        selectUser.setForeground(Color.WHITE);
        selectUser.setHorizontalAlignment(JLabel.CENTER);
        selectUser.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        ImageIcon home = new ImageIcon("assets\\onbrd.png");
        Image imd = home.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
        ImageIcon hx = new ImageIcon(imd);
        homeButton = new JButton(hx);
        homeButton.setBorderPainted(false);
        homeButton.setFocusPainted(false);
        homeButton.setContentAreaFilled(false);

        ImageIcon loadAcc = new ImageIcon("assets\\load-acc.png");
        Image ld = loadAcc.getImage().getScaledInstance(200,75,Image.SCALE_SMOOTH);
        ImageIcon lc = new ImageIcon(ld);
        loadButton = new JButton(lc);
        loadButton.setBorderPainted(false);
        loadButton.setFocusPainted(false);
        loadButton.setContentAreaFilled(false);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!selectedUsn.isEmpty()){
                    JOptionPane.showMessageDialog(frame,"Load Account :  "+selectedUsn);
                }else{
                    JOptionPane.showMessageDialog(frame, "Choose account first!");
                }
            }
        });

//        topButtonPanel.add(coinPanel);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goHome();
            }
        });
        topPanel.add(selectUser);
        bottomPanel.add(homeButton);
        bottomPanel.add(loadButton);
        JPanel listUser = new JPanel();
        listUser.setOpaque(false);
        listUser.setLayout(new BoxLayout(listUser, BoxLayout.Y_AXIS));
        List<String> username = new ArrayList<>();
        for(int i=1; i<=15; i++){//dummy user
            username.add("User "+i+" - Level "+(i*5));
        }
        for(String usn : username){
            //msh button, hrusnya list ajh
            JButton labelUser = new JButton(usn);
            labelUser.setFont(new Font("Arial", Font.BOLD, 20));
            labelUser.setForeground(Color.BLACK);
            labelUser.setBackground(new Color(148, 229, 243));
            labelUser.setBorderPainted(false);
            labelUser.setFocusPainted(false);
            labelUser.setContentAreaFilled(true);
            labelUser.setAlignmentX(Component.CENTER_ALIGNMENT);
            labelUser.setMaximumSize(new Dimension(350,100));
            labelUser.setPreferredSize(new Dimension(100, 40));
            labelUser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(selectedAcc!=null){
                        selectedAcc.setBackground(new Color(148, 229, 243));
                        selectedAcc.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                    }
                    selectedAcc=(JButton) e.getSource();
                    selectedUsn=selectedAcc.getText();
                    selectedAcc.setBackground(new Color(70,130,180,200));
                    selectedAcc.setBorder(BorderFactory.createLineBorder(Color.CYAN,3,true));
                }
            });
            listUser.add(labelUser);
            listUser.add(Box.createRigidArea(new Dimension(0, 5)));

            listUser.add(labelUser);
            listUser.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        JScrollPane scroll = new JScrollPane((listUser));
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(BorderFactory.createEmptyBorder());//1 argument tapi found 2
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scroll, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
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
     User dummyUser = new User("TestUser");//1 argument tapi found 2
     LoadGame lg = new LoadGame(frame,dummyUser);
     frame.setContentPane(lg.getMainPanel());
     frame.setSize(1400, 750);
     frame.setLocationRelativeTo(null);
     frame.setVisible(true);
     }
     });
     }
     }