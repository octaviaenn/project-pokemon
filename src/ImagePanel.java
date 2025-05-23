import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.*;

public class ImagePanel extends JPanel{

    private BufferedImage image;

    public ImagePanel(String path){

        try{
            image = ImageIO.read(new File(path));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

}
