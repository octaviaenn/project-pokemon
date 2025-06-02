import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.*;

public class TransparentPanel extends JPanel{

    private BufferedImage image;

    public TransparentPanel(String path){

        try{
            image = ImageIO.read(new File(path));
        } catch (IOException e){
            e.printStackTrace();
        }
        setOpaque(false);

    }

    protected void paintComponent(Graphics g){
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        super.paintComponent(g);
    }

}
