import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class NewGame {

    //private CardLayout card;
    private CardLayout cardText;
    private CardLayout cardInput;
    private int pageText = 1;

    public NewGame() {

    }

    public JPanel page(){

        JPanel home = Onboard.getHome();
        CardLayout mainCard = Onboard.getCard();

        JPanel main = new JPanel();

        //card = new CardLayout();
        main.setSize(1400, 750);
        //main.setLayout(card);

        ImagePanel newGame = new ImagePanel("assets\\basic-bg.jpeg");
        main.add(newGame, "NewGame");

        newGame.setPreferredSize(new Dimension(1400, 750));
        newGame.setLayout(null);

        //GridBagConstraints c = new GridBagConstraints();

        JPanel top = new JPanel(null);
        //top.setLayout(new GridLayout(1, 2));
        top.setOpaque(false);
        top.setBounds(0, 0, 1400, 500);
        //top.setBackground(Color.PINK);
//        c.gridx = 0;
//        c.gridy = 0;
//        c.gridheight = 2;
//        c.gridwidth = 1;
//        c.weighty = 0;
//        //c.anchor = GridBagConstraints.;
//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.insets = new Insets(10, 10, 10, 10);
        //c.gridheight = ;
        newGame.add(top);

        cardInput = new CardLayout();
        JPanel input = new JPanel(cardInput);
        input.setPreferredSize(new Dimension(700, 500));
        input.setBounds(0, 0, 750, 550);
        input.setOpaque(false);
        top.add(input);

        JPanel pict = new JPanel();
        //pict.setPreferredSize(new Dimension(750, 550));
        pict.setBounds(750, 0, 700, 500);
        pict.setBackground(Color.BLUE);
        top.add(pict);

//        CardLayout cardBack = new CardLayout();
        JPanel backBtn = new JPanel();
        backBtn.setBounds(50, 590, 30, 30);
        backBtn.setBackground(Color.black);
        newGame.add(backBtn);
        backBtn.setVisible(false);

//        JPanel notAvail = new JPanel();
//        notAvail.setOpaque(false);
//        backBtn.add(notAvail, "Not");

//        JPanel backVis = new JPanel();
//        backVis.setBackground(Color.black);
////        JButton back = new JButton("Back");
////        back.setBounds(0, 0, 30, 30);
////        backVis.add(back);
//        backBtn.add(backVis, "Visible");
//        cardBack.show(backBtn, "Not");

        //CardLayout cardNext = new CardLayout();
        JPanel nextBtn = new JPanel();
        nextBtn.setBounds(1330, 590, 30, 30);
        nextBtn.setBackground(Color.black);
        newGame.add(nextBtn);

//        JPanel nextVis = new JPanel();
//        nextVis.setBackground(Color.black);
////        JButton next = new JButton("Next");
////        next.setBounds(1170, 600, 30, 30);
////        nextVis.add(next);
//        nextBtn.add(nextVis, "Visible");
//        nextBtn.add(notAvail, "Not");
//        cardNext.show(nextBtn, "Visible");

        cardText = new CardLayout();
        JPanel text = new JPanel(cardText);
        //text.setPreferredSize(new Dimension(1200, 200));
        text.setBounds(100, 500, 1200, 200);
        //text.setBackground(Color.WHITE);
//        c.gridx = 0;
//        c.gridy = 2;
//        c.gridheight = 1;
//        c.gridwidth = 1;
//        c.weighty = 1.0;
//        c.anchor = GridBagConstraints.PAGE_END;
        newGame.add(text);

        JPanel text1 = new JPanel();
        text1.setPreferredSize(new Dimension(1200, 200));
        JLabel hello = new JLabel("<html><div style='width:1100px;'>Welcome to the world of Charmon!</div></html>");
        hello.setFont(new Font("Courier New", Font.BOLD, 20));
        hello.setBorder(new EmptyBorder(20, 15, 20, 15));
        hello.setPreferredSize(new Dimension(1100, 200));
        text1.setBackground(new Color(235, 213, 200));
//        text1.setLayout(new FlowLayout(FlowLayout.CENTER));
        text1.add(hello, BorderLayout.CENTER);
        text.add(text1, "Text1");

        JPanel text2 = new JPanel();
        text2.setPreferredSize(new Dimension(1200, 200));
        JLabel profName = new JLabel("<html><div style='width:1100px;'>I'm Chia, your guide in this fantastic land filled with mystical<br>creatures called Charmon.</div></html>");
        profName.setFont(new Font("Courier New", Font.BOLD, 20));
        profName.setBorder(new EmptyBorder(20, 15, 20, 15));
        profName.setPreferredSize(new Dimension(1100, 200));
        text2.setBackground(new Color(235, 213, 200));
//        text2.setLayout(new FlowLayout(FlowLayout.CENTER));
        text2.add(profName, BorderLayout.CENTER);
        text.add(text2, "Text2");

        JPanel text3 = new JPanel();
        text3.setPreferredSize(new Dimension(1200, 200));
        JLabel backStory = new JLabel("<html><div style='width:1100px;'>Back in my day, we raised Charmon with love.</div></html>");
        backStory.setFont(new Font("Courier New", Font.BOLD, 20));
        backStory.setBorder(new EmptyBorder(20, 15, 20, 15));
        backStory.setPreferredSize(new Dimension(1100, 200));
        text3.setBackground(new Color(235, 213, 200));
//        text3.setLayout(new FlowLayout(FlowLayout.CENTER));
        text3.add(backStory, BorderLayout.CENTER);
        text.add(text3, "Text3");

        JPanel text4 = new JPanel();
        text4.setPreferredSize(new Dimension(1200, 200));
        JLabel urTurn = new JLabel("<html><div style='width:1100px;'>And now, it's your turn to raise one, and explore the world!</div></html>");
        urTurn.setFont(new Font("Courier New", Font.BOLD, 20));
        urTurn.setBorder(new EmptyBorder(20, 15, 20, 15));
        urTurn.setPreferredSize(new Dimension(1100, 200));
        text4.setBackground(new Color(235, 213, 200));
//        text4.setLayout(new FlowLayout(FlowLayout.CENTER));
        text4.add(urTurn, BorderLayout.CENTER);
        text.add(text4, "Text4");

        JPanel text5 = new JPanel();
        text5.setPreferredSize(new Dimension(1200, 200));
        JLabel who = new JLabel("<html><div style='width:1100px;'>But before we begin, tell me about yourself! What is your name?</div></html>");
        who.setFont(new Font("Courier New", Font.BOLD, 20));
        who.setBorder(new EmptyBorder(20, 15, 20, 15));
        who.setPreferredSize(new Dimension(1100, 200));
        text5.setBackground(new Color(235, 213, 200));
//        text5.setLayout(new FlowLayout(FlowLayout.CENTER));
        text5.add(who, BorderLayout.CENTER);
        text.add(text5, "Text5");

        JPanel text6 = new JPanel();
        text6.setPreferredSize(new Dimension(1200, 200));
        JLabel ntmy = new JLabel("<html><div style='width:1100px;'>Nice to meet you [name]! Now, it's time to choose your first Charmon companion!</div></html>");
        ntmy.setFont(new Font("Courier New", Font.BOLD, 20));
        ntmy.setBorder(new EmptyBorder(20, 15, 20, 15));
        ntmy.setPreferredSize(new Dimension(1100, 200));
        text6.setBackground(new Color(235, 213, 200));
//        text6.setLayout(new FlowLayout(FlowLayout.CENTER));
        text6.add(ntmy, BorderLayout.CENTER);
        text.add(text6, "Text6");

        JPanel empty = new JPanel();
        empty.setPreferredSize(new Dimension(700, 500));
        //empty.setOpaque(false);
        empty.setBounds(0, 0, 700, 500);
        empty.setBackground(Color.BLACK);
        input.add(empty, "Empty");

        JPanel inputName = new JPanel(null);
        inputName.setBounds(0, 0, 700, 500);
        JLabel nameText = new JLabel("Enter your name here");
        JTextField name = new JTextField();
        name.setBounds(200, 200, 300, 50);
        inputName.setOpaque(false);
        inputName.add(name);
        input.add(inputName, "InputName");

//        text.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                pageText++;
//                cardText.show(text, String.format("Text%d", pageText));
//                if(pageText==5) cardInput.show(input, "InputName");
//                else cardInput.show(input, "Empty");
//                if(pageText==7) {
//                    home.add(new BattleScreen().page(), "BattleScreen");
//                    mainCard.show(home, "BattleScreen");
//                }
//            }
//        });

        name.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e){
                nextBtn.setVisible(true);
            }
        });

        backBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(pageText == 1) return;
                pageText--;
                if(pageText == 1){
                    backBtn.setVisible(false);
                    cardText.show(text, String.format("Text%d", pageText));
                    return;
                }
                backBtn.setVisible(true);
                cardText.show(text, String.format("Text%d", pageText));
                if(pageText==5) cardInput.show(input, "InputName");
                else cardInput.show(input, "Empty");
                if(pageText==7) {
                    home.add(new BattleScreen().page(), "BattleScreen");
                    mainCard.show(home, "BattleScreen");
                }
            }
        });

        nextBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                pageText++;
                backBtn.setVisible(true);
                cardText.show(text, String.format("Text%d", pageText));
                if(pageText==5){
                    cardInput.show(input, "InputName");
                    if(name.getText().isEmpty()){
                        nextBtn.setVisible(false);
                    } else{
                        nextBtn.setVisible(true);
                    }
                }
                else{
                    cardInput.show(input, "Empty");
                    nextBtn.setVisible(true);
                }
                if(pageText==7) {
                    home.add(new BattleScreen().page(), "BattleScreen");
                    mainCard.show(home, "BattleScreen");
                }
            }
        });

        return main;

    }
}
