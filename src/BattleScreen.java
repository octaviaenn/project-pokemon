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
import java.net.URL;

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
        TransparentPanel text = new TransparentPanel("assets\\text.png");
        text.setLayout(cardText);
        text.setBounds(75, 500, 1200, 200);
        //text.setOpaque(false);
        battleBg.add(text);

        JPanel enemy = new JPanel(null);
        //enemy.setBackground(Color.black);
        enemy.setOpaque(false);
        JPanel enemyStat = new JPanel();
        enemyStat.setBounds(0, 0, 450, 75);
        enemyStat.setBackground(new Color(235, 213, 200));
        enemy.add(enemyStat);
        enemyCard.add(enemy, "Enemy");

        ImageIcon enemyIcon = new ImageIcon("assets\\charmon\\pikachu-front.gif");
        JLabel enemyLabel = new JLabel(enemyIcon);
        enemy.add(enemyLabel);

        JPanel player = new JPanel(null);
        //player.setBackground(Color.black);
        player.setOpaque(false);
        JPanel playerStat = new JPanel();
        playerStat.setBounds(50, 150, 450, 75);
        playerStat.setBackground(new Color(235, 213, 200));
        player.add(playerStat);
        playerCard.add(player, "Player");

        ImageIcon playerIcon = new ImageIcon("assets\\charmon\\pikachu-back.gif");
        JLabel playerLabel = new JLabel(playerIcon);
        player.add(playerLabel);

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
        text1.setPreferredSize(new Dimension(1050, 200));
        JLabel turn = new JLabel();
        turn.setText(setText);
        turn.setFont(new Font("Courier New", Font.BOLD, 20));
        turn.setBorder(new EmptyBorder(0, 50, 30, 50));
        turn.setPreferredSize(new Dimension(1100, 200));
        text1.setOpaque(false);
        text1.add(turn, BorderLayout.CENTER);
        text.add(text1, "Text1");

        JPanel text2 = new JPanel();
        text2.setPreferredSize(new Dimension(1050, 200));
        JLabel monsterAppear = new JLabel("Wild (monster) appeared!");
        monsterAppear.setFont(new Font("Courier New", Font.BOLD, 20));
        monsterAppear.setBorder(new EmptyBorder(0, 50, 30, 50));
        monsterAppear.setPreferredSize(new Dimension(1100, 200));
        text2.setOpaque(false);
        text2.add(monsterAppear, BorderLayout.CENTER);
        text.add(text2, "Text2");

        JPanel text3 = new JPanel(null);
        text3.setPreferredSize(new Dimension(1050, 200));
        JLabel playerDo = new JLabel("What will (chara) do?");
        playerDo.setFont(new Font("Courier New", Font.BOLD, 20));
        playerDo.setBorder(new EmptyBorder(0, 50, 30, 50));
        playerDo.setBounds(65, 95, 700, 20);
        //ImageIcon fightImage = new ImageIcon("assets\\fight.png");
        //ImageIcon runImage = new ImageIcon("assets\\run.png");
        //ImageIcon healImage = new ImageIcon("assets\\heal.png");
        BufferedImage fightImage = null;
        try {
            fightImage = ImageIO.read(new File("assets\\fight.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image f = fightImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JButton fight = new JButton(new ImageIcon(f));
        //JButton fight = new JButton(fightImage);
        BufferedImage runImage = null;
        try {
            runImage = ImageIO.read(new File("assets\\run.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image r = runImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JButton run = new JButton(new ImageIcon(r));
        //JButton run = new JButton(runImage);
        BufferedImage healImage = null;
        try {
            healImage = ImageIO.read(new File("assets\\heal.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image h = healImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JButton heal = new JButton(new ImageIcon(h));
        //JButton heal = new JButton(healImage);
        fight.setBorderPainted(false);
        fight.setContentAreaFilled(false);
        run.setBorderPainted(false);
        run.setContentAreaFilled(false);
        heal.setBorderPainted(false);
        heal.setContentAreaFilled(false);
        fight.setBounds(700, 60, 100, 60);
        run.setBounds(850, 60, 100, 60);
        heal.setBounds(1000, 60, 100, 60);
        text3.setOpaque(false);
        text3.add(playerDo);
        text3.add(fight);
        text3.add(run);
        text3.add(heal);
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
        BufferedImage resumeImage = null;
        try {
            resumeImage = ImageIO.read(new File("assets\\resume.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image res = resumeImage.getScaledInstance(150, 75, Image.SCALE_SMOOTH);
        JButton resume = new JButton(new ImageIcon(res));

        BufferedImage quitImage = null;
        try {
            quitImage = ImageIO.read(new File("assets\\quit.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image qu = quitImage.getScaledInstance(150, 75, Image.SCALE_SMOOTH);
        JButton quit = new JButton(new ImageIcon(qu));
//        ImageIcon resumeImage = new ImageIcon("assets\\resume.png");
//        ImageIcon quitImage = new ImageIcon("assets\\quit.png");
//        JButton resume = new JButton(resumeImage);
//        JButton quit = new JButton(quitImage);
        resume.setBorderPainted(false);
        resume.setContentAreaFilled(false);
        quit.setBorderPainted(false);
        quit.setContentAreaFilled(false);
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
        BufferedImage westImage = null;
        try {
            westImage = ImageIO.read(new File("assets\\west-btn.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image w = westImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JButton west = new JButton(new ImageIcon(w));

        BufferedImage northImage = null;
        try {
            northImage = ImageIO.read(new File("assets\\north-btn.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image n = northImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JButton north = new JButton(new ImageIcon(n));

        BufferedImage eastImage = null;
        try {
            eastImage = ImageIO.read(new File("assets\\east-btn.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image ea = eastImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JButton east = new JButton(new ImageIcon(ea));

        BufferedImage southImage = null;
        try {
            southImage = ImageIO.read(new File("assets\\south-btn.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image s = southImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JButton south = new JButton(new ImageIcon(s));

//        ImageIcon w = new ImageIcon("assets\\west-btn.png");
//        ImageIcon n = new ImageIcon("assets\\north-btn.png");
//        ImageIcon e = new ImageIcon("assets\\east-btn.png");
//        ImageIcon s = new ImageIcon("assets\\south-btn.png");
//        JButton west = new JButton(w);
//        JButton north = new JButton(n);
//        JButton east = new JButton(e);
//        JButton south = new JButton(s);
        west.setBorderPainted(false);
        west.setContentAreaFilled(false);
        north.setBorderPainted(false);
        north.setContentAreaFilled(false);
        east.setBorderPainted(false);
        east.setContentAreaFilled(false);
        south.setBorderPainted(false);
        south.setContentAreaFilled(false);
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
        BufferedImage basicImage = null;
        try {
            basicImage = ImageIO.read(new File("assets\\basic.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image bas = basicImage.getScaledInstance(150, 75, Image.SCALE_SMOOTH);
        JButton basic = new JButton(new ImageIcon(bas));

        BufferedImage specialImage = null;
        try {
            specialImage = ImageIO.read(new File("assets\\special.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image spec = specialImage.getScaledInstance(150, 75, Image.SCALE_SMOOTH);
        JButton special = new JButton(new ImageIcon(spec));

        BufferedImage elementalImage = null;
        try {
            elementalImage = ImageIO.read(new File("assets\\elemental.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image el = elementalImage.getScaledInstance(150, 75, Image.SCALE_SMOOTH);
        JButton elemental = new JButton(new ImageIcon(el));
//        ImageIcon basicImage = new ImageIcon("assets\\basic.png");
//        ImageIcon specialImage = new ImageIcon("assets\\special.png");
//        ImageIcon elementalImage = new ImageIcon("assets\\elemental.png");
//        JButton basic = new JButton("basic");
//        JButton special = new JButton("special");
//        JButton elemental = new JButton("elemental");
        basic.setBorderPainted(false);
        basic.setContentAreaFilled(false);
        special.setBorderPainted(false);
        special.setContentAreaFilled(false);
        elemental.setBorderPainted(false);
        elemental.setContentAreaFilled(false);
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

        heal.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

            }
        });




        return main;
    }

    public static CardLayout getCard(){ return card;}

}
