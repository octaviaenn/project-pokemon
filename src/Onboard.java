import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Onboard {

    private static CardLayout card;
    private static JPanel home;

    public Onboard(JFrame frame) {

        home = new JPanel();
        frame.add(home);

        card = new CardLayout();
        home.setSize(1400, 750);
        home.setLayout(card);

        // JPanel mainPanel = new JPanel();

        // add(mainPanel, "HomePage");

        ImagePanel homebg = new ImagePanel("assets\\blue-cloud.jpeg");
        home.add(homebg, "Onboard");

        homebg.setPreferredSize(new Dimension(1400, 750));
        homebg.setLayout(null);

        // GridBagConstraints c = new GridBagConstraints();

        JLabel title = new JLabel("LET'S PLAY CHARMON!");
        title.setFont(new Font("Impact", Font.BOLD, 40));
        title.setBounds(525, 200, 500, 50);
        // c.gridx = 0;
        // c.gridy = 0;
        // c.gridwidth = 2;
        // c.anchor = GridBagConstraints.CENTER;
        // c.insets = new Insets(10, 10, 70, 10);
        homebg.add(title);

        ImageIcon neww = new ImageIcon("assets\\new-game.png");
        JButton newGame = new JButton(neww);
        // newGame.setFont(new Font("Verdana", Font.BOLD, 20));
        newGame.setBorderPainted(false);
        newGame.setContentAreaFilled(false);
        newGame.setBounds(600, 300, 200, 100);
        // c.gridx = 0;
        // c.gridy = 1;
        // c.gridwidth = 1;
        // c.insets = new Insets(10, 10, 10, 55);
        homebg.add(newGame);
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                home.add(new NewGame().page(), "NewGame");
                card.show(home, "NewGame");
            }
        });

        ImageIcon resume = new ImageIcon("assets\\load-game.png");
        JButton resumeGame = new JButton(resume);
        // resumeGame.setFont(new Font("Verdana", Font.BOLD, 20));
        resumeGame.setBorderPainted(false);
        resumeGame.setContentAreaFilled(false);
        resumeGame.setBounds(600, 400, 200, 100);
        // c.gridx = 1;
        // c.gridy = 1;
        // c.gridwidth = 1;
        // c.insets = new Insets(10, 55, 10, 10);
        homebg.add(resumeGame);

        // JLabel tutorial = new JLabel("Click here if you need tutorial");
        // tutorial.setFont(new Font("Arial", Font.BOLD, 20));
        ImageIcon exit = new ImageIcon("assets\\exit.png");
        JButton exitGame = new JButton(exit);
        exitGame.setBorderPainted(false);
        exitGame.setContentAreaFilled(false);
        exitGame.setBounds(600, 500, 200, 100);
        // c.gridx = 0;
        // c.gridy = 2;
        // c.gridwidth = 2;
        // c.anchor = GridBagConstraints.CENTER;
        // c.insets = new Insets(50, 10, 10, 10);
        homebg.add(exitGame);

        exitGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

    }

    public static CardLayout getCard() {
        return card;
    }

    public static JPanel getHome() {
        return home;
    }
}
