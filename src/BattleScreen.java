import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BattleScreen {

    private static CardLayout card;
    private int pageText = 1;
    private int runCount = 0;
    private String setText = "You had arrived in a battle arena.";

    public BattleScreen(){

    }

    public JPanel page(){

        JPanel main = new JPanel();

        card = new CardLayout();
        main.setSize(1400, 750);
        main.setLayout(card);

        ImagePanel battleBg = new ImagePanel("assets\\battle-bg.jpeg");
        main.add(battleBg, "Screen");

        battleBg.setPreferredSize(new Dimension(1400, 750));
        battleBg.setLayout(null);

//        JPanel enemyPanel = new JPanel();
//        enemyPanel.setBounds(775, 0, 500, 300);
//        enemyPanel.setOpaque(false);

        BufferedImage optImage = null;
        try {
            optImage = ImageIO.read(new File("assets\\opt-btn.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image opt = optImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JButton option = new JButton(new ImageIcon(opt));
        option.setBorderPainted(false);
        option.setContentAreaFilled(false);
        option.setBounds(1290, 25, 50, 50);
        battleBg.add(option);

        CardLayout cardEnemy = new CardLayout();
        JPanel enemyCard = new JPanel(cardEnemy);
        enemyCard.setBounds(775, 25, 500, 225);
        battleBg.add(enemyCard);
        //enemy.setOpaque(false);
        //CardLayout cardEstat = new CardLayout();

//        enemyPanel.add(enemyCard);
//        //enemyPanel.add(enemyStat);
//        battleBg.add(enemyPanel);

//        JPanel playerPanel = new JPanel();
//        playerPanel.setBounds(75, 300, 500, 300);
//        playerPanel.setOpaque(false);

        CardLayout cardPlayer = new CardLayout();
        JPanel playerCard = new JPanel(cardPlayer);
        playerCard.setBounds(75, 250, 500, 225);
        battleBg.add(playerCard);
        //player.setBackground(Color.white);
        //CardLayout cardPstat = new CardLayout();

//        playerPanel.add(playerCard);
//        //playerPanel.add(playerStat);
//        battleBg.add(playerPanel);

        CardLayout cardText = new CardLayout();
        JPanel text = new JPanel(cardText);
        text.setBounds(75, 500, 1200, 200);
        text.setBackground(new Color(235, 213, 200));
        battleBg.add(text);

        JPanel enemy = new JPanel(null);
        enemy.setBackground(Color.black);
        JPanel enemyStat = new JPanel();
        enemyStat.setBounds(0, 0, 450, 75);
        enemyStat.setBackground(new Color(235, 213, 200));
        enemy.add(enemyStat);
        enemyCard.add(enemy, "Enemy");

        JPanel player = new JPanel(null);
        player.setBackground(Color.black);
        JPanel playerStat = new JPanel();
        playerStat.setBounds(50, 150, 450, 75);
        playerStat.setBackground(new Color(235, 213, 200));
        player.add(playerStat);
        playerCard.add(player, "Player");

        //CardLayout cardNext = new CardLayout();
        BufferedImage nextImage = null;
        try {
            nextImage = ImageIO.read(new File("assets\\next-btn.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image next = nextImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JButton nextBtn = new JButton(new ImageIcon(next));
        nextBtn.setBorderPainted(false);
        nextBtn.setContentAreaFilled(false);
        nextBtn.setBounds(1290, 580, 50, 50);
        nextBtn.setBackground(Color.black);
        battleBg.add(nextBtn);

//        JPanel nextVis = new JPanel();
//        nextVis.setBackground(Color.black);
////        JButton next = new JButton("Next");
////        next.setBounds(1300, 600, 30, 30);
////        nextVis.add(next);
//        nextBtn.add(nextVis, "Visible");

//        JPanel notAvail = new JPanel();
//        notAvail.setOpaque(false);
//        nextBtn.add(notAvail, "Not");

        JPanel text1 = new JPanel();
        text1.setPreferredSize(new Dimension(1200, 200));
        JLabel turn = new JLabel();
        turn.setText(setText);
        turn.setFont(new Font("Courier New", Font.BOLD, 20));
        turn.setBorder(new EmptyBorder(20, 15, 20, 15));
        turn.setPreferredSize(new Dimension(1100, 200));
        text1.setBackground(new Color(235, 213, 200));
        text1.add(turn, BorderLayout.CENTER);
        text.add(text1, "Text1");

        JPanel text2 = new JPanel();
        text2.setPreferredSize(new Dimension(1200, 200));
        JLabel monsterAppear = new JLabel("Wild (monster) appeared!");
        monsterAppear.setFont(new Font("Courier New", Font.BOLD, 20));
        monsterAppear.setBorder(new EmptyBorder(20, 15, 20, 15));
        monsterAppear.setPreferredSize(new Dimension(1100, 200));
        text2.setBackground(new Color(235, 213, 200));
        text2.add(monsterAppear, BorderLayout.CENTER);
        text.add(text2, "Text2");

        JPanel text3 = new JPanel(null);
        text3.setPreferredSize(new Dimension(1200, 200));
        JLabel playerDo = new JLabel("What will (chara) do?");
        playerDo.setFont(new Font("Courier New", Font.BOLD, 20));
        //playerDo.setBorder(new EmptyBorder(20, 15, 20, 15));
        playerDo.setBounds(65, 95, 700, 20);
        JButton fight = new JButton("FIGHT");
        JButton run = new JButton("RUN");
        JButton bag = new JButton("BAG");
        fight.setBounds(750, 70, 100, 60);
        run.setBounds(900, 70, 100, 60);
        bag.setBounds(1050, 70, 100, 60);
        text3.setBackground(new Color(235, 213, 200));
        text3.add(playerDo);
        text3.add(fight);
        text3.add(run);
        text3.add(bag);
        text.add(text3, "Text3");

//        text.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                pageText++;
//                cardText.show(text, String.format("Text%d", pageText));
//            }
//        });

        nextBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                pageText++;
                cardText.show(text, String.format("Text%d", pageText));
                if(pageText == 3) nextBtn.setVisible(false);
                else nextBtn.setVisible(true);
            }
        });

        JPanel optionPanel = new JPanel();
        optionPanel.setPreferredSize(new Dimension(1000, 400));
        JButton resume = new JButton("Resume");
        JButton quit = new JButton("Quit");
        optionPanel.add(resume);
        optionPanel.add(quit);

        JOptionPane optionSetting = new JOptionPane(optionPanel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{});
        JDialog dialogSetting = optionSetting.createDialog("What do you want to do?");

        option.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                dialogSetting.setVisible(true);
            }
        });

        resume.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dialogSetting.setVisible(false);
            }
        });

        quit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //nanti nyimpen state sama manggil home
            }
        });

        JPanel movePanel = new JPanel(new BorderLayout());
        movePanel.setPreferredSize(new Dimension(1000, 400));
        JButton west = new JButton("West");
        JButton north = new JButton("North");
        JButton east = new JButton("East");
        JButton south = new JButton("South");
        JLabel move = new JLabel("Move");
        movePanel.add(west, BorderLayout.WEST);
        movePanel.add(north, BorderLayout.NORTH);
        movePanel.add(east, BorderLayout.EAST);
        movePanel.add(south, BorderLayout.SOUTH);
        movePanel.add(move, BorderLayout.CENTER);

        JOptionPane optionMove = new JOptionPane(movePanel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{});
        JDialog dialogMove = optionMove.createDialog("Choose where u want to go");

        west.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dialogMove.setVisible(false);
                runCount = 0;
                pageText = 1;
                setText = "You had turned to left";
                cardText.show(text, "Text1");
                nextBtn.setVisible(true);
            }
        });

        north.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dialogMove.setVisible(false);
                runCount = 0;
                pageText = 1;
                setText = "You had moved forward";
                cardText.show(text, "Text1");
                nextBtn.setVisible(true);
            }
        });

        east.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dialogMove.setVisible(false);
                runCount = 0;
                pageText = 1;
                setText = "You had turned to right";
                cardText.show(text, "Text1");
                nextBtn.setVisible(true);
            }
        });

        south.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dialogMove.setVisible(false);
                runCount = 0;
                pageText = 1;
                setText = "You had moved backward";
                cardText.show(text, "Text1");
                nextBtn.setVisible(true);
            }
        });

        JPanel attackPanel = new JPanel(new GridLayout(3, 1));
        attackPanel.setPreferredSize(new Dimension(300, 300));
        JButton basic = new JButton("basic");
        JButton special = new JButton("special");
        JButton elemental = new JButton("elemental");
        attackPanel.add(basic);
        attackPanel.add(special);
        attackPanel.add(elemental);

        JOptionPane optionAttack = new JOptionPane(attackPanel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{});
        JDialog dialogAttack = optionAttack.createDialog("Choose your attack!");

        fight.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                dialogAttack.setVisible(true);

                // animation dulu, jalanin battlenya, nanti tanya mau lanjut apa exit
                // janlup page balikin 1
                //dialogMove.setVisible(true);
            }
        });

        run.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                runCount++;
                if(runCount > 3){
                    JOptionPane.showMessageDialog(Main.frame, "You can't run more than 3 times!");
                } else{
                    dialogMove.setVisible(true);
                }
            }
        });

        bag.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

            }
        });




        return main;
    }

    public static CardLayout getCard(){ return card;}

}
