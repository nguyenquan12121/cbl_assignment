import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

//Never let an AI write human code
class PlayPanel extends JPanel implements ActionListener {
    private static BufferedImage ballImage;
    private static int ballX = 650;
    private static int ballY = 100;
    //0 pixel/ms 
    double speedY = 0;
    // 0.07 pixel/ms^2
    double accelerate = 0.07;
    double deaccelerate = -0.07;
    Timer timer; 
    public PlayPanel(){
        timer = new Timer(10,this);
        timer.start();
        String filePath = "images/1200px-Soccerball.svg.png";
        try{
            File imageFile = new File(filePath);
            ballImage = ImageIO.read(imageFile);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(ballImage, ballX, ballY, 100, 100, null);
        g2d.setStroke(new BasicStroke(10));
        Line2D lineOne = new Line2D.Double(750, 150, 750, 600);
        Line2D lineTwo = new Line2D.Double(650, 150, 650, 600);
        Line2D ground = new Line2D.Double(0, 600, 2000, 600);
        g2d.draw(lineOne);
        g2d.draw(lineTwo);
        g2d.draw(ground);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        //Ball falling towards the ground
            speedY +=accelerate;
            ballY+=speedY;
        //Ball bouncing off the (a very bouncy) ground
        if (ballY>=500){
            speedY*=-1;
        }

        repaint();
    }
}