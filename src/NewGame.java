import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NewGame {

    private CardLayout card;
    private CardLayout cardText;
    private int pageText = 0;

    public NewGame() {

    }

    public JPanel create(){

        JPanel main = new JPanel();

        card = new CardLayout();
        main.setSize(1500, 800);
        main.setLayout(card);

        ImagePanel newGame = new ImagePanel("assets\\basic-bg.jpeg");
        main.add(newGame, "NewGame");

        newGame.setPreferredSize(new Dimension(1500, 800));
        newGame.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        JPanel top = new JPanel();
        top.setLayout(new GridLayout(1, 2));
        top.setOpaque(false);
        //top.setBackground(Color.PINK);
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.weighty = 0;
        //c.anchor = GridBagConstraints.;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10);
        //c.gridheight = ;
        newGame.add(top, c);

        cardText = new CardLayout();
        JPanel text = new JPanel(cardText);
        text.setPreferredSize(new Dimension(1200, 250));
        //text.setBackground(Color.WHITE);
        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.PAGE_END;
        newGame.add(text, c);

        JPanel text1 = new JPanel();
        text1.setPreferredSize(new Dimension(1200, 250));
        JLabel hello = new JLabel("<html><div style='width:1100px;'>Welcome to the world of Charmon!</div></html>");
        hello.setFont(new Font("Courier New", Font.BOLD, 20));
        hello.setBorder(new EmptyBorder(20, 15, 20, 15));
        hello.setPreferredSize(new Dimension(1100, 200));
        text1.setBackground(new Color(235, 213, 200));
//        text1.setLayout(new FlowLayout(FlowLayout.CENTER));
        text1.add(hello, BorderLayout.CENTER);
        text.add(text1, "Text1");

        JPanel text2 = new JPanel();
        text2.setPreferredSize(new Dimension(1200, 250));
        JLabel profName = new JLabel("<html><div style='width:1100px;'>I'm Professor Mangrove, your guide in this fantastic land  filled with mystical creatures called Charmon.</div></html>");
        profName.setFont(new Font("Courier New", Font.BOLD, 20));
        profName.setBorder(new EmptyBorder(20, 15, 20, 15));
        profName.setPreferredSize(new Dimension(1100, 200));
        text2.setBackground(new Color(235, 213, 200));
//        text2.setLayout(new FlowLayout(FlowLayout.CENTER));
        text2.add(profName, BorderLayout.CENTER);
        text.add(text2, "Text2");

        JPanel text3 = new JPanel();
        text3.setPreferredSize(new Dimension(1200, 250));
        JLabel backStory = new JLabel("<html><div style='width:1100px;'>Back in my day, we raised Charmon with love.</div></html>");
        backStory.setFont(new Font("Courier New", Font.BOLD, 20));
        backStory.setBorder(new EmptyBorder(20, 15, 20, 15));
        backStory.setPreferredSize(new Dimension(1100, 200));
        text3.setBackground(new Color(235, 213, 200));
//        text3.setLayout(new FlowLayout(FlowLayout.CENTER));
        text3.add(backStory, BorderLayout.CENTER);
        text.add(text3, "Text3");

        JPanel text4 = new JPanel();
        text4.setPreferredSize(new Dimension(1200, 250));
        JLabel urTurn = new JLabel("<html><div style='width:1100px;'>And now, it's your turn to raise one, and explore the world!</div></html>");
        urTurn.setFont(new Font("Courier New", Font.BOLD, 20));
        urTurn.setBorder(new EmptyBorder(20, 15, 20, 15));
        urTurn.setPreferredSize(new Dimension(1100, 200));
        text4.setBackground(new Color(235, 213, 200));
//        text4.setLayout(new FlowLayout(FlowLayout.CENTER));
        text4.add(urTurn, BorderLayout.CENTER);
        text.add(text4, "Text4");

        JPanel text5 = new JPanel();
        text5.setPreferredSize(new Dimension(1200, 250));
        JLabel who = new JLabel("<html><div style='width:1100px;'>But before we begin, tell me about yourself! What is your name?</div></html>");
        who.setFont(new Font("Courier New", Font.BOLD, 20));
        who.setBorder(new EmptyBorder(20, 15, 20, 15));
        who.setPreferredSize(new Dimension(1100, 200));
        text5.setBackground(new Color(235, 213, 200));
//        text5.setLayout(new FlowLayout(FlowLayout.CENTER));
        text5.add(who, BorderLayout.CENTER);
        text.add(text5, "Text5");

        JPanel text6 = new JPanel();
        text6.setPreferredSize(new Dimension(1200, 250));
        JLabel ntmy = new JLabel("<html><div style='width:1100px;'>Nice to meet you [name]! Now, it's time to choose your first Charmon companion!</div></html>");
        ntmy.setFont(new Font("Courier New", Font.BOLD, 20));
        ntmy.setBorder(new EmptyBorder(20, 15, 20, 15));
        ntmy.setPreferredSize(new Dimension(1100, 200));
        text6.setBackground(new Color(235, 213, 200));
//        text6.setLayout(new FlowLayout(FlowLayout.CENTER));
        text6.add(ntmy, BorderLayout.CENTER);
        text.add(text6, "Text6");

        text.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                pageText++;
                cardText.show(text, String.format("Text%d", pageText));

            }
        });

        return main;

    }
}
