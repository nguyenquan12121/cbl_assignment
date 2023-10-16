import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class PlayPanel extends JPanel implements Runnable {
    private static BufferedImage ballImage;
    private static int ballX = 650;
    private static int ballY = 500;
    private static final int DELAY = 10;
    private static boolean initiated = false;
    //0 pixel/ms 
    double speedY=5;
    // 0.07 pixel/ms^2
    double accelerate = 0.07;
    double deaccelerate = -0.07;
    Thread animator;
    public PlayPanel(){
        String filePath = "images/1200px-Soccerball.svg.png";
        try{
            File imageFile = new File(filePath);
            ballImage = ImageIO.read(imageFile);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        this.setBackground(Color.orange);
    }
    public void setTimer(boolean status, long duration){
        if (status){
            initiated = true;
            startAnimation();
            speedY = duration/500;
        }
        else{
            initiated = false;
            animator.interrupt();
        }
        System.out.println(initiated);
    }

    public void reset(){
        ballY = 500;
        initiated = false;
        animator.interrupt();
        repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawBaw(g2d);
        drawLines(g2d);
    }

    public void drawBaw(Graphics2D g2d){
        g2d.drawImage(ballImage, ballX, ballY, 100, 100, null);
        Toolkit.getDefaultToolkit().sync();
    }

    public void drawLines(Graphics2D g2d){
        g2d.setStroke(new BasicStroke(10));
        Line2D lineOne = new Line2D.Double(750, 150, 750, 600);
        Line2D lineTwo = new Line2D.Double(650, 150, 650, 600);
        Line2D ground = new Line2D.Double(0, 600, 2000, 600);
        g2d.draw(lineOne);
        g2d.draw(lineTwo);
        g2d.draw(ground);
        Toolkit.getDefaultToolkit().sync();

    }

    public void startAnimation() {
        animator = new Thread(this);
        animator.start();
    }

    public void cycle(){
        speedY +=deaccelerate;
        System.out.println(ballY + " " + speedY);
        ballY-=speedY;
        if (ballY > 500){
            //Ball sinks to the ground so i just stop the animation at this point
            if (Math.abs(speedY)<0.78 || ballY > 508){
                reset();
            }
            speedY+=1.3;
            speedY*=-1;
        }
    }

    @Override
    public void run(){
        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (initiated) {
            cycle();
            repaint();
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }
            try {
                //Ensure that every repaint() call is exactly 10ms apart
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                initiated = false;
            }

            beforeTime = System.currentTimeMillis();

    }

}
}