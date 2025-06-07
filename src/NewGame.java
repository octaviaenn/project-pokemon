import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NewGame {

    // private CardLayout card;
    private CardLayout cardText;
    private CardLayout cardInput;
    private int pageText = 1;
    private TransparentPanel text;
    private User newUser;
    private CardLayout mainCard;
    private JPanel home;
    private static boolean isReady = false;
    private boolean readyChoose = false;
    private LoadingAnimation loading = new LoadingAnimation("assets\\disc.png");
    private static LoadingAnimation loadingBattle = new LoadingAnimation("assets\\disc.png");
    private boolean flagInput = false;
    private static BattleScreen battleScreen;

    public NewGame() {

    }

    public JPanel page() {

        home = Onboard.getHome();
        mainCard = Onboard.getCard();
        isReady = false;
        readyChoose = false;

        JPanel main = new JPanel();

        // card = new CardLayout();
        main.setSize(1400, 750);
        // main.setLayout(card);

        ImagePanel newGame = new ImagePanel("assets\\basic-bg.jpeg");
        main.add(newGame, "NewGame");

        newGame.setPreferredSize(new Dimension(1400, 750));
        newGame.setLayout(null);

        // GridBagConstraints c = new GridBagConstraints();

        JPanel top = new JPanel(null);
        // top.setLayout(new GridLayout(1, 2));
        top.setOpaque(false);
        top.setBounds(0, 0, 1400, 500);
        // top.setBackground(Color.PINK);
        // c.gridx = 0;
        // c.gridy = 0;
        // c.gridheight = 2;
        // c.gridwidth = 1;
        // c.weighty = 0;
        // //c.anchor = GridBagConstraints.;
        // c.fill = GridBagConstraints.HORIZONTAL;
        // c.insets = new Insets(10, 10, 10, 10);
        // c.gridheight = ;
        newGame.add(top);

        cardInput = new CardLayout();
        JPanel input = new JPanel(cardInput);
        input.setPreferredSize(new Dimension(700, 500));
        input.setBounds(0, 0, 750, 550);
        input.setOpaque(false);
        top.add(input);

        TransparentPanel pict = new TransparentPanel("assets\\cathy.png");
        // pict.setPreferredSize(new Dimension(750, 550));
        pict.setBounds(900, 210, 268, 304);
        top.add(pict);

        // CardLayout cardBack = new CardLayout();
        BufferedImage backImage = null;
        try {
            backImage = ImageIO.read(new File("assets\\back-btn.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image back = backImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        JButton backBtn = new JButton(new ImageIcon(back));
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setBounds(40, 580, 50, 50);
        // backBtn.setBackground(Color.black);
        newGame.add(backBtn);
        backBtn.setVisible(false);

        // JPanel notAvail = new JPanel();
        // notAvail.setOpaque(false);
        // backBtn.add(notAvail, "Not");

        // JPanel backVis = new JPanel();
        // backVis.setBackground(Color.black);
        //// JButton back = new JButton("Back");
        //// back.setBounds(0, 0, 30, 30);
        //// backVis.add(back);
        // backBtn.add(backVis, "Visible");
        // cardBack.show(backBtn, "Not");

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
        nextBtn.setBounds(1310, 580, 50, 50);
        // nextBtn.setBackground(Color.black);
        newGame.add(nextBtn);

        // JPanel nextVis = new JPanel();
        // nextVis.setBackground(Color.black);
        //// JButton next = new JButton("Next");
        //// next.setBounds(1170, 600, 30, 30);
        //// nextVis.add(next);
        // nextBtn.add(nextVis, "Visible");
        // nextBtn.add(notAvail, "Not");
        // cardNext.show(nextBtn, "Visible");

        cardText = new CardLayout();
        text = new TransparentPanel("assets\\text.png");
        text.setLayout(cardText);
        // JPanel text = new JPanel(cardText);
        // text.setPreferredSize(new Dimension(1200, 200));
        text.setBounds(100, 500, 1200, 200);
        // text.setBackground(Color.WHITE);
        // c.gridx = 0;
        // c.gridy = 2;
        // c.gridheight = 1;
        // c.gridwidth = 1;
        // c.weighty = 1.0;
        // c.anchor = GridBagConstraints.PAGE_END;
        newGame.add(text);

        JPanel text1 = new JPanel();
        text1.setPreferredSize(new Dimension(1050, 200));
        JLabel hello = new JLabel("<html><div style='width:1100px;'>Welcome to the world of Charmon!</div></html>");
        hello.setFont(new Font("Courier New", Font.BOLD, 20));
        hello.setBorder(new EmptyBorder(0, 50, 30, 50));
        hello.setPreferredSize(new Dimension(1100, 200));
        // text1.setBackground(new Color(235, 213, 200));
        // text1.setLayout(new FlowLayout(FlowLayout.CENTER));
        text1.setOpaque(false);
        text1.add(hello, BorderLayout.CENTER);
        text.add(text1, "Text1");

        JPanel text2 = new JPanel();
        text2.setPreferredSize(new Dimension(1050, 200));
        JLabel profName = new JLabel(
                "<html><div style='width:1100px;'>I'm Cathy, your guide in this fantastic land filled with mystical creatures<br>called Charmon.</div></html>");
        profName.setFont(new Font("Courier New", Font.BOLD, 20));
        profName.setBorder(new EmptyBorder(0, 50, 30, 50));
        profName.setPreferredSize(new Dimension(1100, 200));
        // text2.setBackground(new Color(235, 213, 200));
        // text2.setLayout(new FlowLayout(FlowLayout.CENTER));
        text2.setOpaque(false);
        text2.add(profName, BorderLayout.CENTER);
        text.add(text2, "Text2");

        JPanel text3 = new JPanel();
        text3.setPreferredSize(new Dimension(1050, 200));
        JLabel backStory = new JLabel(
                "<html><div style='width:1100px;'>Back in my day, we raised Charmon with love.</div></html>");
        backStory.setFont(new Font("Courier New", Font.BOLD, 20));
        backStory.setBorder(new EmptyBorder(0, 50, 30, 50));
        backStory.setPreferredSize(new Dimension(1100, 200));
        // text3.setBackground(new Color(235, 213, 200));
        // text3.setLayout(new FlowLayout(FlowLayout.CENTER));
        text3.setOpaque(false);
        text3.add(backStory, BorderLayout.CENTER);
        text.add(text3, "Text3");

        JPanel text4 = new JPanel();
        text4.setPreferredSize(new Dimension(1050, 200));
        JLabel urTurn = new JLabel(
                "<html><div style='width:1100px;'>And now, it's your turn to raise one, and explore the world!</div></html>");
        urTurn.setFont(new Font("Courier New", Font.BOLD, 20));
        urTurn.setBorder(new EmptyBorder(0, 50, 30, 50));
        urTurn.setPreferredSize(new Dimension(1100, 200));
        // text4.setBackground(new Color(235, 213, 200));
        // text4.setLayout(new FlowLayout(FlowLayout.CENTER));
        text4.setOpaque(false);
        text4.add(urTurn, BorderLayout.CENTER);
        text.add(text4, "Text4");

        JPanel text5 = new JPanel();
        text5.setPreferredSize(new Dimension(1050, 200));
        JLabel who = new JLabel(
                "<html><div style='width:1100px;'>But before we begin, tell me about yourself! What is your name?</div></html>");
        who.setFont(new Font("Courier New", Font.BOLD, 20));
        who.setBorder(new EmptyBorder(0, 50, 30, 50));
        who.setPreferredSize(new Dimension(1100, 200));
        // text5.setBackground(new Color(235, 213, 200));
        // text5.setLayout(new FlowLayout(FlowLayout.CENTER));
        text5.setOpaque(false);
        text5.add(who, BorderLayout.CENTER);
        text.add(text5, "Text5");

        // JPanel text6 = new JPanel();
        // text6.setPreferredSize(new Dimension(1050, 200));
        // JLabel ntmy = new JLabel(
        // "<html><div style='width:1100px;'>Nice to meet you [name]! Now, it's time to
        // choose your first Charmon companion!</div></html>");
        // ntmy.setFont(new Font("Courier New", Font.BOLD, 20));
        // ntmy.setBorder(new EmptyBorder(0, 50, 30, 50));
        // ntmy.setPreferredSize(new Dimension(1100, 200));
        // // text6.setBackground(new Color(235, 213, 200));
        // // text6.setLayout(new FlowLayout(FlowLayout.CENTER));
        // text6.setOpaque(false);
        // text6.add(ntmy, BorderLayout.CENTER);
        // text.add(text6, "Text6");

        JPanel empty = new JPanel();
        empty.setPreferredSize(new Dimension(700, 500));
        empty.setOpaque(false);
        empty.setBounds(0, 0, 700, 500);
        // empty.setBackground(Color.BLACK);
        input.add(empty, "Empty");

        TransparentPanel inputBox = new TransparentPanel("assets\\player-box.png");
        inputBox.setBounds(250, 300, 300, 100);
        // inputBox.setLayout(null);
        JPanel inputName = new JPanel(null);
        inputName.setBounds(0, 0, 700, 500);
        JLabel nameText = new JLabel("Enter your name here");
        JTextField name = new JTextField();
        // if (name != null && !name.getText().isEmpty()) {
        // String a = name.getText();
        // User newUser = new User(a);
        // UserData.addUser(newUser);
        // }
        name.setOpaque(false);
        name.setPreferredSize(new Dimension(250, 50));
        name.setBounds(30, 250, 250, 50);
        // name.setBorder(new EmptyBorder(0, 15, 30, 15));
        inputName.setOpaque(false);
        inputBox.add(name);
        inputName.add(nameText);
        inputName.add(inputBox);
        input.add(inputName, "InputName");

        // text.addMouseListener(new MouseAdapter() {
        // public void mouseClicked(MouseEvent e) {
        // pageText++;
        // cardText.show(text, String.format("Text%d", pageText));
        // if(pageText==5) cardInput.show(input, "InputName");
        // else cardInput.show(input, "Empty");
        // if(pageText==7) {
        // home.add(new BattleScreen().page(), "BattleScreen");
        // mainCard.show(home, "BattleScreen");
        // }
        // }
        // });

        name.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                nextBtn.setVisible(true);
            }
        });

        backBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (flagInput) {
                    nextBtn.setVisible(true);
                }
                if (pageText == 1)
                    return;
                pageText--;
                if (pageText == 1) {
                    backBtn.setVisible(false);
                    cardText.show(text, String.format("Text%d", pageText));
                    return;
                }
                backBtn.setVisible(true);
                cardText.show(text, String.format("Text%d", pageText));
                if (pageText == 5)
                    cardInput.show(input, "InputName");
                else
                    cardInput.show(input, "Empty");
            }
        });

        nextBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("page:" + pageText);
                if (pageText == 5) {
                    System.out.println("ambil nama");
                    newUser = new User(name.getText());
                    textName(newUser.getName());
                    // newUser.setCurrentChar("Pikachu");
                    addBattleScreen();
                    addKarakterPokemon();
                    // System.out.println("INI ISIIIIII" + newUser.getCurrentChar());
                }

                pageText++;
                System.out.println("nambah jadi" + pageText);
                backBtn.setVisible(true);
                cardText.show(text, String.format("Text%d", pageText));
                if (pageText == 5) {
                    flagInput = true;
                    System.out.println("Page 5");
                    cardInput.show(input, "InputName");
                    if (name.getText().isEmpty()) {
                        nextBtn.setVisible(false);
                    } else {
                        nextBtn.setVisible(true);
                    }
                } else {
                    cardInput.show(input, "Empty");
                    nextBtn.setVisible(true);
                }
                if (pageText == 7) {
                    nextBtn.setVisible(false);
                    backBtn.setVisible(false);
                    if (readyChoose) {
                        // If already loaded, show it immediately
                        System.out.println("Karakter Pokemon already loaded. Showing directly.");
                        mainCard.show(home, "KarakterPokemon");
                    } else {
                        // If not yet loaded, show loading animation
                        System.out.println("Karakter Pokemon not yet loaded. Showing animation.");
                        loading.setBounds(650, 200, 102, 153);
                        newGame.add(loading);
                        loading.startAnimation();
                        // The SwingWorker's done() method will handle showing Karakter Pokemon when
                        // it's
                        // done
                    }
                }
            }
        });

        return main;

    }

    private void textName(String name) {
        JPanel text6 = new JPanel();
        text6.setPreferredSize(new Dimension(1050, 200));
        String s = String.format(
                "<html><div style='width:1100px;'>Nice to meet you %s!<br>Now, it's time to choose your first Charmon companion!</div></html>",
                name);
        JLabel ntmy = new JLabel(s);
        ntmy.setFont(new Font("Courier New", Font.BOLD, 20));
        ntmy.setBorder(new EmptyBorder(0, 50, 30, 50));
        ntmy.setPreferredSize(new Dimension(1100, 200));
        // text6.setBackground(new Color(235, 213, 200));
        // text6.setLayout(new FlowLayout(FlowLayout.CENTER));
        text6.setOpaque(false);
        text6.add(ntmy, BorderLayout.CENTER);
        text.add(text6, "Text6");
    }

    private void addKarakterPokemon() {
        new SwingWorker<JPanel, Void>() {
            @Override
            protected JPanel doInBackground() throws Exception {
                readyChoose = false;
                KarakterPokemon choose = new KarakterPokemon(newUser);
                return choose; // Get the actual JPanel
            }

            @Override
            protected void done() {
                try {
                    JPanel choose = get();
                    System.out.println("sukses nambahin KarakterPokemon");
                    readyChoose = true;
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
                isReady = false;
                BattleScreen battleScreen = new BattleScreen(newUser);
                return battleScreen; // Get the actual JPanel
            }

            @Override
            protected void done() {
                try {
                    battleScreen = get();
                    System.out.println("sukses nambahin battlescreen");
                    isReady = true;
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

    public static LoadingAnimation getLoadingBattle() {
        return loadingBattle;
    }

    public static BattleScreen getBattleScreen() {
        return battleScreen;
    }

}