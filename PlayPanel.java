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

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class PlayPanel extends JPanel implements Runnable {

    InformationPanel ip;

    private double lastCheck;
    private static final int FPS = 144;
    private static final int TICKS = 128;
    boolean springStatus = false, releaseSignal = false;    

    //Spring related 
    int springX=500;
    private static int springY=575;
    private static int springWidth=30;
    long springPressedDuration;
    int springFluc=1;
    boolean pressed;

    //Ball related 
    private static BufferedImage ballImage;
    private static int ballX = 500;
    private static int ballY = 496;
    //0 pixel/ms 
    double speedY=0.02;
    // 0.07 pixel/ms^2
    double accelerate = 0.05;
    double deaccelerate = -0.05;

    //Threads
    Thread animator;
    int threadCounter = 0;

    //Highstiker related
    private static BufferedImage thermoStat;

    //Target related
    int widthTarget;
    int heightTarget;

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
            animator.interrupt();
    }
    //Method is called by ActionListener to start the spring compress animation
    //Method is called by MouseRelease to end the spring compress animation
    public void setSpringTimer(boolean status, long duration){
        if (status){
            startAnimation();
        }
        springStatus = status;
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
        drawTarget(g2d, 100, 200);
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
        g2d.setStroke(new BasicStroke(10));
        g2d.drawOval(springX,springY,100,springWidth);
        g2d.drawOval(springX,springY+20,100,springWidth);
        g2d.drawOval(springX,springY+40,100,springWidth);
        g2d.drawOval(springX,springY+60,100,springWidth);
    }

    public void drawTarget(Graphics2D g2d,int size, int height){
        g2d.setStroke(new BasicStroke(10));
        widthTarget = size;
        heightTarget = height;
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
    
    public void startAnimation() {

        //Only start 1 thread
        if (threadCounter ==0){
            animator = new Thread(this);
            animator.start();
            threadCounter++;
        }
        lastCheck = System.currentTimeMillis();
    }

    //Called with mouseRelease event from the "bounce button"
    public void launchBall(){
        springStatus = false;
        releaseSignal = true;
        ballY = 496;
        //150 seems to give the ball enough speed
        speedY = springPressedDuration/150;
        //Return the springs to their original location
        springX=500; 
        springY=575;
        System.out.println("BALL LAUNCHED!!!!!!" + releaseSignal);
        springWidth = 30;
    }

    //Called with the reset button
    public void reset(){
        ballY = 496;
        springX=500; 
        springY=580;
        System.out.println("RESET!!!!!!");
        springWidth = 30;
        springStatus = true;
        releaseSignal = false;
        springPressedDuration = 0;
        speedY = 0;
        repaint();
        if (threadCounter!=0) animator.interrupt();
        threadCounter = 0;
        
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
        System.out.println(speedY);
        if (speedY>=0){
            speedY+=deaccelerate;
        }
        else{
            speedY = 0;
            currRoundScore = score(ballY, heightTarget);
            System.out.println(currRoundScore);
            ip.updateCurrScore((int) currRoundScore);
            releaseSignal = false;
        }
        //System.out.println(ballY + " " + speedY);
    }

    //Handle Springs coordinates
    public void compressSpring(){
        //Ball and spring move at the same speed
        springY += springFluc; ///so they can get closer // add it getting shorter 
        if (springWidth>3){
            springWidth-=0.01;
        }
        ballY =springY-70;
        if(springFluc>1){
            springFluc-=0.01;
        }

    }
    @Override
    public void run(){
        long beforeTime, currTime;
        double timePerTick = 1000000000/TICKS;
        double timePerFrame = 1000000000/FPS;
        //delta tick rate to deal with lags
        double deltaT = 0, deltaF = 0;
        beforeTime = System.nanoTime();
        //springStatus is true when the spring is compressing when the user is holding down the mouse button
        while (springStatus && !releaseSignal) {
            currTime = System.nanoTime();
            deltaT += (currTime - beforeTime)/timePerTick;
            deltaF += (currTime - beforeTime)/timePerFrame;
            beforeTime = currTime;
            if (deltaT >=1){
                compressSpring();
                //deltaT might be 1.02 since there are inheriant lags and the game should only be update when delta is 1 so decrement by 1 and the 0.02 delay is accounted for in the next tick
                deltaT--;
            }
            if (deltaF >=1){
                repaint();
                deltaF--;
            }
        }
        beforeTime = System.nanoTime();
        //idk why but having this print statement here fixes the issue of resetting
        System.out.println(releaseSignal);
        while (releaseSignal) {
            currTime = System.nanoTime();
            deltaT += (currTime - beforeTime)/timePerTick;
            // System.out.println(deltaT);
            deltaF += (currTime - beforeTime)/timePerFrame;
            beforeTime = currTime;
            if (deltaT >=1){
                updateBallLaunch();
                //deltaT might be 1.02 since there are inheriant lags and the game should only be update when delta is 1 so decrement by 1 and the 0.02 delay is accounted for in the next tick
                deltaT--;
            }
            if (deltaF >=1){
                repaint();
                deltaF--;
            }
        }
    }
}