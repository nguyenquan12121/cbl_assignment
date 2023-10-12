import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


class PlayPanel extends JPanel {
    private static BufferedImage ballImage;
    private static int x;
    private static int y; 
    public static BufferedImage readImage(){

        String filePath = "images/1200px-Soccerball.svg.png";
        try{
            File imageFile = new File(filePath);
            ballImage = ImageIO.read(imageFile);
        
        }
        catch(IOException e){
            e.printStackTrace();
        }
    return ballImage;
    
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        BufferedImage image = readImage();
        g2d.drawImage(image, 650, 100, 100, 100, null);
        g2d.setStroke(new BasicStroke(10));
        Line2D lineOne = new Line2D.Double(750, 150, 750, 600);
        Line2D lineTwo = new Line2D.Double(650, 150, 650, 600);
        Line2D ground = new Line2D.Double(0, 600, 2000, 600);
        g2d.draw(lineOne);
        g2d.draw(lineTwo);
        g2d.draw(ground);
    }
}