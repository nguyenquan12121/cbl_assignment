import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class PlayPanel extends JPanel  {

    InformationPanel ip;

    boolean springStatus = false, releaseSignal = false;    

    //Spring related 
    int springX=500;
    private static int springY=575;
    private static int springWidth=30;
    long springPressedDuration;
    int springFluc=5;
    int springcompression=0;
    int springcompression1=0;
    int springcompression2=0;
    int springcompression3=0;
    int counter=1;
    boolean pressed;

    //Ball related 
    private static BufferedImage ballImage;
    private static int ballX = 500;
    private static int ballY = 496;
    //0 pixel/ms 
    double speedY=0;
    // 0.07 pixel/ms^2
    double accelerate = 0.05;
    double deaccelerate = -0.05;

    //Highstiker related
    private static BufferedImage thermoStat;

    //Target related
    int widthTarget =100;
    int heightTarget =200;

    //Random object
    Random random;
    final int SCORE_MAX=1000;
    double currRoundScore = 0;
    private BufferedImage backgroundImage;

    public PlayPanel(){
        String ballPath = "images/pixel_ball.png";
        String thermoPath = "images/highstriker.png";
        String backgroundPath = "images/menu_background.jpg";
        try{
            File ballFile = new File(ballPath);
            ballImage = ImageIO.read(ballFile);

            File thermoFile = new File(thermoPath);
            thermoStat = ImageIO.read(thermoFile);
            File backFile = new File(backgroundPath);
            backgroundImage = ImageIO.read(backFile);                        
        }
        catch(IOException e){
            e.printStackTrace();
        }
        this.setVisible(true);
    }

    public void addInfoPanel(InformationPanel ip){
        this.ip = ip;
    }
    //Called by stop button to freeze animation
    public void setTimer(boolean status, long duration){
            // animator.interrupt();
    }
    //Method is called by ActionListener to start the spring compress animation
    //Method is called by MouseRelease to end the spring compress animation
    public void setSpringTimer(boolean status, long duration){
        springPressedDuration = duration;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawBackground(g2d);
        drawThermoStat(g2d);
        drawSpring(g2d);
        drawBall(g2d);
        drawTarget(g2d,  heightTarget);
    }

    public void drawBall(Graphics2D g2d){
        g2d.drawImage(ballImage, ballX, ballY, 100, 100, null);
        Toolkit.getDefaultToolkit().sync();
    }

    public void drawLines(Graphics2D g2d){
        g2d.setStroke(new BasicStroke(30));
        Line2D lineOne = new Line2D.Double(500, 150, 500, 680);
        Line2D lineTwo = new Line2D.Double(600, 150, 600, 680);
        Line2D ground = new Line2D.Double(0, 680, 2000, 680);
        g2d.draw(lineOne);
        g2d.draw(lineTwo);
        g2d.draw(ground);
        Toolkit.getDefaultToolkit().sync();


    }
    public void drawSpring(Graphics2D g2d){
            g2d.drawOval(springX,springY+springcompression,100,springWidth);
            g2d.drawOval(springX,springY+20+springcompression1,100,springWidth);
            g2d.drawOval(springX,springY+40+springcompression2,100,springWidth);
            g2d.drawOval(springX,springY+60+springcompression3,100,springWidth);
    }

    public void drawTarget(Graphics2D g2d, int height){
        g2d.setStroke(new BasicStroke(10));
        g2d.setColor(Color.RED);
        g2d.drawOval(545,heightTarget,10,10);
    }

    public void drawThermoStat(Graphics2D g2d){
        g2d.drawImage(thermoStat, 55,-75 , 1000, 1000, null);
        Toolkit.getDefaultToolkit().sync();
    }

    public void drawBackground(Graphics2D g2d){
        g2d.drawImage(backgroundImage, 0, 0,1400,1400, null);
    }
    

    //Called with mouseRelease event from the "bounce button"
    public void launchBall(){
        ballY = 496;
        //150 seems to give the ball enough speed
        if (springPressedDuration<1100){
        speedY = springPressedDuration/150;
        }
        else{
            speedY=1100/150;
        }
        //Return the springs to their original location
        springX=500; 
        springY=575;
        springWidth = 30;
    }

    //Called with the reset button
    public void reset(){
        ballY = 496;
        springX=500; 
        springY=580;
        springWidth = 30;
        springcompression=0;
        springcompression1=0;
        springcompression2=0;
        springcompression3=0;
        repaint();
    }
    //Handles scoring
    public double score(int BallYFinal, int heightTarget){
        int distance=Math.abs(heightTarget-BallYFinal);
        //the Lower the distance the higher the score
        double calcScore=1000/distance;
        //int score=Math.max(SCORE_MAX,calcScore );
    return calcScore;
    }

    //Handle ball coordinates
    public void updateBallLaunch(){
        ballY-=speedY;
        if (speedY>=0){
            speedY+=deaccelerate;
        }
        else{
            speedY = 0;
            currRoundScore = score(ballY, heightTarget);
            ip.updateCurrScore((int) currRoundScore);
            GameState.state = GameState.TRANSITION;
        }
    }

    //Handle Springs coordinates
    public void compressSpring(){
     if(counter%10==0){
        if(springY+springcompression3<610){
        //Ball and spring move at the same speed
        springcompression+=4;
        springcompression1+=3;
        springcompression2+=2;
        springcompression3+=1;
        springY += springFluc; ///so they can get closer // add it getting shorter 
        if (springWidth>3){
            springWidth--;
        }
        ballY =springY+springcompression-70;
        if(springFluc>1){
        springFluc--;
        }
    }
    else{
        //maximumForce=pp.
    }

}
    counter++;
    }

    public void generateTarget(){
        random = new Random();
        heightTarget = random.nextInt(150,450);
    }
}