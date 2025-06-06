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
    private static CardLayout cardText;
    private int pageText = 1;
    private int runCount = 0;
    private User user;
    private static TransparentPanel text;
    private HealthBar playerHealthBar;
    private HealthBar enemyHealthBar;
    private Battle currentBattle; // To hold the battle logic instance
    private String messageLabel; // To display battle messages like "Pikachu used Thunderbolt!"
    private Charmon player, enemy;
    // private JPanel buttonPanel;
    public static CustomDialog dialogAttack;

    public BattleScreen(User user) {
        this.user = user;
        player = user.getCurrentChar();
        enemy = Character.findRandom(player);
        playerHealthBar = new HealthBar(player.getHP(), player.getHP());
        enemyHealthBar = new HealthBar(enemy.getHP(), enemy.getHP());
        // buttonPanel = new JPanel();
        // buttonPanel.setPreferredSize(new Dimension(400, 200));
        // buttonPanel.setBounds(600, 0, 400, 200);
        // buttonPanel.setVisible(true);
        // In BattleScreen constructor, after Charmon objects are ready
        currentBattle = new Battle(user, player,
                // onPlayerHpChange callback
                () -> {
                    playerHealthBar.setHp(player.getHealth());
                    // messageLabel.setText(player.getName() + " HP: " + player.getHealth() + "/" +
                    // player.getHealth());
                },
                // onEnemyHpChange callback
                () -> {
                    enemyHealthBar.setHp(currentBattle.getEnemy().getHealth() + user.getDiffHealth());
                    // messageLabel.setText(currentBattle.getEnemy().getName() + " took damage! HP:
                    // "
                    // + currentBattle.getEnemy().getHealth() + "/" +
                    // currentBattle.getEnemy().getHealth());
                },
                // onBattleTurnEnd callback (Crucial for delays between turns)
                () -> {
                    // Disable buttons during animation/delay
                    // setButtonsEnabled(false);
                    // buttonPanel.setVisible(false);
                    // Use a Swing Timer to create a delay before the next action (e.g., enemy's
                    // turn)
                    new Timer(1500, new ActionListener() { // 1.5 second delay
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ((Timer) e.getSource()).stop(); // Stop this one-shot timer
                            if (!player.isFainted() && !currentBattle.getEnemy().isFainted()) {
                                if (!currentBattle.getIsPlayer()) { // If it's now enemy's turn
                                    // messageLabel.setText("Enemy's turn...");
                                    JPanel enemyTurn = textOption(enemy.getName() + "'s turn..");
                                    text.add(enemyTurn, "Enemy");
                                    cardText.show(text, "Enemy");
                                    currentBattle.enemyTurn(); // Trigger enemy's turn

                                } else { // It's player's turn again
                                    // messageLabel.setText("Your turn!");
                                    JPanel playerTurn = textOption("It's your turn!");
                                    text.add(playerTurn, "Player");
                                    // buttonPanel.setVisible(true);
                                    cardText.show(text, "Player");
                                    // setButtonsEnabled(true); // Re-enable player buttons
                                }
                            }
                        }
                    }).start();
                },
                // onBattleEnded callback
                () -> {
                    // buttonPanel.setVisible(false);
                    // setButtonsEnabled(false); // Disable all buttons
                    // if (player.isFainted()) {
                    // messageLabel.setText("You lost the battle!");
                    // } else {
                    // messageLabel.setText("You won the battle!");
                    // }
                    // // Add logic here to transition back to main menu, restart, etc.
                });
        // Initial HP updates for the bars
        playerHealthBar.setHp(player.getHealth());
        enemyHealthBar.setHp(enemy.getHealth());
        // setButtonsEnabled(true); // Enable player buttons for the first turn
    }

    public JPanel page() {

        JPanel home = Onboard.getHome();
        CardLayout mainCard = Onboard.getCard();
        JPanel main = new JPanel();

        card = new CardLayout();
        main.setSize(1400, 750);
        main.setLayout(card);

        ImagePanel battleBg = new ImagePanel("assets\\battle-bg.jpeg");
        main.add(battleBg, "Screen");

        battleBg.setPreferredSize(new Dimension(1400, 750));
        battleBg.setLayout(null);

        // JPanel enemyPanel = new JPanel();
        // enemyPanel.setBounds(775, 0, 500, 300);
        // enemyPanel.setOpaque(false);

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
        JPanel enemyCard = new JPanel();
        enemyCard.setLayout(cardEnemy);
        enemyCard.setOpaque(false);
        enemyCard.setBounds(300, 25, 900, 300);
        battleBg.add(enemyCard);
        // enemy.setOpaque(false);
        // CardLayout cardEstat = new CardLayout();

        // enemyPanel.add(enemyCard);
        // //enemyPanel.add(enemyStat);
        // battleBg.add(enemyPanel);

        // JPanel playerPanel = new JPanel();
        // playerPanel.setBounds(75, 300, 500, 300);
        // playerPanel.setOpaque(false);

        // CardLayout cardPlayer = new CardLayout();
        // JPanel playerCard = new JPanel(cardPlayer);
        // playerCard.setBounds(75, 250, 500, 225);
        // battleBg.add(playerCard);
        // player.setBackground(Color.white);
        // CardLayout cardPstat = new CardLayout();

        // playerPanel.add(playerCard);
        // //playerPanel.add(playerStat);
        // battleBg.add(playerPanel);

        cardText = new CardLayout();
        text = new TransparentPanel("assets\\text.png");
        text.setLayout(cardText);
        text.setBounds(75, 500, 1200, 200);
        // text.setOpaque(false);
        battleBg.add(text);

        JPanel enemyP = enemyPanel();
        enemyCard.add(enemyP, "Enemy");

        // Charmon chara = user.getCurrentChar();

        JPanel playerPanel = new JPanel(null);
        playerPanel.setBounds(150, 200, 500, 300);
        // playerPanel.setBackground(Color.black);
        playerPanel.setOpaque(false);
        TransparentPanel playerStat = new TransparentPanel("assets\\player-box.png");
        playerStat.setLayout(null);
        playerHealthBar.setBounds(50, 35, 200, 30);
        playerStat.add(playerHealthBar);
        playerStat.setBounds(500, 300, 300, 100);
        JLabel playerName = new JLabel(player.getName());
        JLabel playerLvl = new JLabel(String.format("Level %d", user.getLevel()));
        playerName.setBounds(50, 10, 150, 30);
        playerLvl.setBounds(200, 10, 150, 30);
        playerStat.add(playerName);
        playerStat.add(playerLvl);
        // playerStat.setBackground(new Color(235, 213, 200));
        // playerPanel.add(playerStat);
        ImageIcon playerIcon = new ImageIcon(player.getBackImage());
        JLabel playerLabel = new JLabel(playerIcon);
        playerLabel.setOpaque(false);
        playerLabel.setBounds(50, 50, playerIcon.getIconWidth(),
                playerIcon.getIconHeight()); // Make sure it's transparent
        // GifPanel playerLabel = new GifPanel(
        // "/charmon/pikachu-back.gif");
        playerPanel.add(playerLabel);
        battleBg.add(playerPanel);
        battleBg.add(playerStat);

        // CardLayout cardNext = new CardLayout();
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

        // JPanel nextVis = new JPanel();
        // nextVis.setBackground(Color.black);
        //// JButton next = new JButton("Next");
        //// next.setBounds(1300, 600, 30, 30);
        //// nextVis.add(next);
        // nextBtn.add(nextVis, "Visible");

        // JPanel notAvail = new JPanel();
        // notAvail.setOpaque(false);
        // nextBtn.add(notAvail, "Not");

        JPanel text1 = new JPanel();
        text1.setPreferredSize(new Dimension(1050, 200));
        JLabel turn = new JLabel();
        turn.setText("You have arrived at the battle arena");
        turn.setFont(new Font("Courier New", Font.BOLD, 20));
        turn.setBorder(new EmptyBorder(0, 50, 30, 50));
        turn.setPreferredSize(new Dimension(1100, 200));
        text1.setOpaque(false);
        text1.add(turn, BorderLayout.CENTER);
        text.add(text1, "Text1");
        cardText.show(text, "Text1");

        // JPanel text2 = new JPanel();
        // text2.setPreferredSize(new Dimension(1050, 200));
        // JLabel monsterAppear = new JLabel("Wild (monster) appeared!");
        // monsterAppear.setFont(new Font("Courier New", Font.BOLD, 20));
        // monsterAppear.setBorder(new EmptyBorder(0, 50, 30, 50));
        // monsterAppear.setPreferredSize(new Dimension(1100, 200));
        // text2.setOpaque(false);
        // text2.add(monsterAppear, BorderLayout.CENTER);
        // text.add(text2, "Text2");
        textFill(String.format("Wild %s appeared!", currentBattle.getEnemy().getName()), "Text2");

        JPanel text3 = new JPanel(null);
        text3.setPreferredSize(new Dimension(1050, 200));
        JLabel playerDo = new JLabel("What will (chara) do?");
        playerDo.setFont(new Font("Courier New", Font.BOLD, 20));
        playerDo.setBorder(new EmptyBorder(0, 50, 30, 50));
        playerDo.setBounds(65, 95, 700, 50);
        // ImageIcon fightImage = new ImageIcon("assets\\fight.png");
        // ImageIcon runImage = new ImageIcon("assets\\run.png");
        // ImageIcon healImage = new ImageIcon("assets\\heal.png");
        // JPanel text3 = new JPanel(null);
        // text3.setPreferredSize(new Dimension(1050, 200));
        // JPanel textO = textOption("What will " + user.getCurrentChar().getName() + "
        // do?");
        // text3.setOpaque(false);
        // text3.add(textO);
        // text3.setPreferredSize(new Dimension(1050, 200));
        BufferedImage fightImage = null;
        try {
            fightImage = ImageIO.read(new File("assets\\fight.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image f = fightImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JButton fight = new JButton(new ImageIcon(f));
        // JButton fight = new JButton(fightImage);
        BufferedImage runImage = null;
        try {
            runImage = ImageIO.read(new File("assets\\run.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image r = runImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JButton run = new JButton(new ImageIcon(r));
        // JButton run = new JButton(runImage);

        // JButton heal = new JButton(healImage);
        fight.setBorderPainted(false);
        fight.setContentAreaFilled(false);
        run.setBorderPainted(false);
        run.setContentAreaFilled(false);

        fight.setBounds(700, 60, 100, 60);
        run.setBounds(850, 60, 100, 60);
        text3.setOpaque(false);
        text3.add(playerDo);
        // JButton p = new JButton("y");
        // text3.add(p);
        // buttonPanel.setLayout(null);
        text3.add(fight);
        text3.add(run);
        // buttonPanel.add(heal);
        // buttonPanel.setOpaque(false);
        // text3.add(buttonPanel);

        text.add(text3, "Text3");

        // text.addMouseListener(new MouseAdapter() {
        // public void mouseClicked(MouseEvent e) {
        // pageText++;
        // cardText.show(text, String.format("Text%d", pageText));
        // }
        // });

        nextBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                pageText++;
                cardText.show(text, String.format("Text%d", pageText));
                if (pageText == 3)
                    nextBtn.setVisible(false);
                else
                    nextBtn.setVisible(true);
            }
        });

        // JPanel optionPanel = new JPanel();
        // optionPanel.setOpaque(false);
        // optionPanel.setPreferredSize(new Dimension(300, 300));
        BufferedImage resumeImage = null;
        try {
            resumeImage = ImageIO.read(new File("assets\\resume.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image res = resumeImage.getScaledInstance(80, 40, Image.SCALE_SMOOTH);
        JButton resume = new JButton(new ImageIcon(res));

        BufferedImage quitImage = null;
        try {
            quitImage = ImageIO.read(new File("assets\\quit.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image qu = quitImage.getScaledInstance(80, 40, Image.SCALE_SMOOTH);
        JButton quit = new JButton(new ImageIcon(qu));
        // ImageIcon resumeImage = new ImageIcon("assets\\resume.png");
        // ImageIcon quitImage = new ImageIcon("assets\\quit.png");
        // JButton resume = new JButton(resumeImage);
        // JButton quit = new JButton(quitImage);
        resume.setBorderPainted(false);
        resume.setContentAreaFilled(false);
        quit.setBorderPainted(false);
        quit.setContentAreaFilled(false);

        resume.setBounds(110, 120, 80, 40);
        quit.setBounds(110, 200, 80, 40);

        // JOptionPane optionSetting = new JOptionPane(optionPanel,
        // JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{});
        // JDialog dialogSetting = optionSetting.createDialog("What do you want to
        // do?");
        CustomDialog dialogSetting = new CustomDialog("assets\\popup.png", 211, 17, 1);
        dialogSetting.addButton(resume);
        dialogSetting.addButton(quit);

        option.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialogSetting.setVisible(true);
            }
        });

        resume.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialogSetting.setVisible(false);
            }
        });

        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // nanti nyimpen state sama manggil home
                // simpen statenya belum
                home.add(new Homepage().getMainPanel(), "Homepage");
                dialogSetting.setVisible(false);
                mainCard.show(home, "Homepage");
            }
        });

        // JPanel movePanel = new JPanel(new BorderLayout());
        // // movePanel.setOpaque(false);
        // movePanel.setPreferredSize(new Dimension(300, 300));
        BufferedImage westImage = null;
        try {
            westImage = ImageIO.read(new File("assets\\west-btn.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image w = westImage.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        JButton west = new JButton(new ImageIcon(w));

        BufferedImage northImage = null;
        try {
            northImage = ImageIO.read(new File("assets\\north-btn.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image n = northImage.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        JButton north = new JButton(new ImageIcon(n));

        BufferedImage eastImage = null;
        try {
            eastImage = ImageIO.read(new File("assets\\east-btn.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image ea = eastImage.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        JButton east = new JButton(new ImageIcon(ea));

        BufferedImage southImage = null;
        try {
            southImage = ImageIO.read(new File("assets\\south-btn.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image s = southImage.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        JButton south = new JButton(new ImageIcon(s));

        // ImageIcon w = new ImageIcon("assets\\west-btn.png");
        // ImageIcon n = new ImageIcon("assets\\north-btn.png");
        // ImageIcon e = new ImageIcon("assets\\east-btn.png");
        // ImageIcon s = new ImageIcon("assets\\south-btn.png");
        // JButton west = new JButton(w);
        // JButton north = new JButton(n);
        // JButton east = new JButton(e);
        // JButton south = new JButton(s);
        west.setBorderPainted(false);
        west.setContentAreaFilled(false);
        north.setBorderPainted(false);
        north.setContentAreaFilled(false);
        east.setBorderPainted(false);
        east.setContentAreaFilled(false);
        south.setBorderPainted(false);
        south.setContentAreaFilled(false);
        // JLabel move = new JLabel("Move");
        west.setBounds(30, 140, 75, 75);
        north.setBounds(110, 90, 75, 75);
        // move.setBounds(120, 140, 60, 20);
        east.setBounds(195, 140, 75, 75);
        south.setBounds(110, 200, 75, 75);

        // JOptionPane optionMove = new JOptionPane(movePanel,
        // JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{});
        // JDialog dialogMove = optionMove.createDialog("Choose where u want to go");
        CustomDialog dialogMove = new CustomDialog("assets\\popup.png", 211, 17, 1);
        dialogMove.addButton(west);
        dialogMove.addButton(north);
        dialogMove.addButton(east);
        dialogMove.addButton(south);
        // dialogMove.addLabel(move);

        west.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Sound.play("assets\\sound\\run.wav");
                dialogMove.setVisible(false);
                runCount++;
                pageText = 1;
                textFill("You had turned to left", "West");
                cardText.show(text, "West");
                currentBattle.changeEnemy();

                textFill(String.format("Wild %s appeared!", currentBattle.getEnemy().getName()), "Text2");
                JPanel enemyP = enemyPanel();
                enemyCard.add(enemyP, "Enemy");
                cardEnemy.show(enemyCard, "Enemy");
                nextBtn.setVisible(true);
            }
        });

        north.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Sound.play("assets\\sound\\run.wav");
                dialogMove.setVisible(false);
                runCount++;
                pageText = 1;
                textFill("You had moved forward", "North");
                cardText.show(text, "North");
                currentBattle.changeEnemy();
                textFill(String.format("Wild %s appeared!", currentBattle.getEnemy().getName()), "Text2");
                JPanel enemyP = enemyPanel();
                enemyCard.add(enemyP, "Enemy");
                cardEnemy.show(enemyCard, "Enemy");
                nextBtn.setVisible(true);
            }
        });

        east.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Sound.play("assets\\sound\\run.wav");
                dialogMove.setVisible(false);
                runCount++;
                pageText = 1;
                textFill("You had turned to right", "East");
                cardText.show(text, "East");
                currentBattle.changeEnemy();
                textFill(String.format("Wild %s appeared!", currentBattle.getEnemy().getName()), "Text2");
                JPanel enemyP = enemyPanel();
                enemyCard.add(enemyP, "Enemy");
                cardEnemy.show(enemyCard, "Enemy");
                nextBtn.setVisible(true);
            }
        });

        south.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Sound.play("assets\\sound\\run.wav");
                dialogMove.setVisible(false);
                runCount++;
                pageText = 1;
                textFill("You had moved backward", "South");
                cardText.show(text, "South");
                currentBattle.changeEnemy();
                textFill(String.format("Wild %s appeared!", currentBattle.getEnemy().getName()), "Text2");
                JPanel enemyP = enemyPanel();
                enemyCard.add(enemyP, "Enemy");
                cardEnemy.show(enemyCard, "Enemy");
                nextBtn.setVisible(true);
            }
        });

        // JPanel attackPanel = new JPanel(new GridLayout(3, 1));
        // // attackPanel.setOpaque(false);
        // attackPanel.setPreferredSize(new Dimension(300, 300));
        BufferedImage basicImage = null;
        try {
            basicImage = ImageIO.read(new File("assets\\basic.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image bas = basicImage.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        JButton basic = new JButton(new ImageIcon(bas));

        BufferedImage specialImage = null;
        try {
            specialImage = ImageIO.read(new File("assets\\special.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image spec = specialImage.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        JButton special = new JButton(new ImageIcon(spec));

        BufferedImage elementalImage = null;
        try {
            elementalImage = ImageIO.read(new File("assets\\elemental.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image el = elementalImage.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        JButton elemental = new JButton(new ImageIcon(el));

        BufferedImage healImage = null;
        try {
            healImage = ImageIO.read(new File("assets\\heal.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image h = healImage.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
        JButton heal = new JButton(new ImageIcon(h));

        // ImageIcon basicImage = new ImageIcon("assets\\basic.png");
        // ImageIcon specialImage = new ImageIcon("assets\\special.png");
        // ImageIcon elementalImage = new ImageIcon("assets\\elemental.png");
        // JButton basic = new JButton("basic");
        // JButton special = new JButton("special");
        // JButton elemental = new JButton("elemental");
        basic.setBorderPainted(false);
        basic.setContentAreaFilled(false);
        special.setBorderPainted(false);
        special.setContentAreaFilled(false);
        elemental.setBorderPainted(false);
        elemental.setContentAreaFilled(false);
        heal.setBorderPainted(false);
        heal.setContentAreaFilled(false);

        basic.setBounds(40, 130, 100, 50);
        special.setBounds(160, 130, 100, 50);
        elemental.setBounds(40, 250, 100, 50);
        heal.setBounds(40, 250, 100, 50);

        // JOptionPane optionAttack = new JOptionPane(attackPanel,
        // JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{});
        // JDialog dialogAttack = optionAttack.createDialog("Choose your attack!");
        dialogAttack = new CustomDialog("assets\\popup.png", 211, 17, 1);
        dialogAttack.addButton(basic);
        dialogAttack.addButton(special);
        dialogAttack.addButton(elemental);

        fight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialogAttack.setVisible(true);
                // animation dulu, jalanin battlenya, nanti tanya mau lanjut apa exit
                // janlup page balikin 1
                // dialogMove.setVisible(true);
            }
        });

        run.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (runCount > 3) {
                    JOptionPane.showMessageDialog(Main.frame, "You can't run more than 3 times!");
                } else {
                    dialogMove.setVisible(true);
                }
            }
        });

        heal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                player.heal(true);

                Sound.play("assets\\sound\\heal.wav");
                currentBattle.getPlayerHpChange().run();
            }
        });

        basic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // while(!player.isFainted() || !enemy.isFainted()){
                currentBattle.playerTurn(0);
                // currentBattle.enemyTurn();
                // }
            }
        });

        special.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // while(!player.isFainted() || !enemy.isFainted()){
                currentBattle.playerTurn(1);
                // currentBattle.enemyTurn();
                // }
            }
        });

        elemental.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // while(!player.isFainted() || !enemy.isFainted()){
                currentBattle.playerTurn(2);
                // currentBattle.enemyTurn();
                // }
            }
        });

        return main;
    }

    public static CardLayout getCard() {
        return card;
    }

    public static CardLayout getCardText() {
        return cardText;
    }

    public static JPanel getText() {
        return text;
    }

    private void textFill(String s, String path) {
        JPanel newText = new JPanel();
        newText.setPreferredSize(new Dimension(1050, 200));
        JLabel label = new JLabel(s);
        label.setFont(new Font("Courier New", Font.BOLD, 20));
        label.setBorder(new EmptyBorder(0, 50, 30, 50));
        label.setPreferredSize(new Dimension(1100, 200));
        // newText.setBackground(new Color(235, 213, 200));
        // newText.setLayout(new FlowLayout(FlowLayout.CENTER));
        newText.setOpaque(false);
        newText.add(label, BorderLayout.CENTER);
        text.add(newText, path);
    }

    public static JPanel textOption(String s) {
        JPanel newText = new JPanel();
        newText.setPreferredSize(new Dimension(1050, 200));
        JLabel label = new JLabel(s);
        label.setFont(new Font("Courier New", Font.BOLD, 20));
        label.setBorder(new EmptyBorder(0, 50, 30, 50));
        label.setPreferredSize(new Dimension(1100, 200));
        // newText.setBackground(new Color(235, 213, 200));
        // newText.setLayout(new FlowLayout(FlowLayout.CENTER));
        newText.setOpaque(false);
        newText.add(label, BorderLayout.CENTER);
        return newText;
    }

    private JPanel enemyPanel() {
        JPanel panel = new JPanel(null);
        // panel.setBackground(Color.black);
        // panel.setBounds(775, 25, 500, 225);
        enemy = currentBattle.getEnemy();
        panel.setOpaque(false);
        TransparentPanel enemyStat = new TransparentPanel("assets\\enemy-box.png");
        enemyStat.setLayout(null);
        enemyHealthBar = new HealthBar(enemy.getHP(), enemy.getHP());
        enemyHealthBar.setBounds(50, 60, 200, 30);
        enemyStat.add(enemyHealthBar);
        enemyStat.setBounds(250, 100, 300, 100);
        JLabel enemyName = new JLabel(enemy.getName());
        JLabel enemyLvl = new JLabel(String.format("Level %d", user.getLevel()));
        enemyName.setBounds(50, 30, 150, 30);
        enemyLvl.setBounds(200, 30, 150, 30);
        enemyStat.add(enemyName);
        enemyStat.add(enemyLvl);
        // panel.add(enemyHealthBar);
        // enemyStat.setBounds(0, 0, 450, 75);
        // enemyStat.setBackground(new Color(235, 213, 200));

        ImageIcon enemyIcon = new ImageIcon(enemy.getImage());
        JLabel enemyLabel = new JLabel(enemyIcon);
        enemyLabel.setOpaque(false);
        enemyLabel.setBounds(600, 0, enemyIcon.getIconWidth(),
                enemyIcon.getIconHeight());
        panel.add(enemyLabel);
        panel.add(enemyStat);
        return panel;
    }

    public static CustomDialog getDialogAttack() {
        return dialogAttack;
    }

}
