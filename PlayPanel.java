import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class PlayPanel extends JPanel implements Runnable {
    private static BufferedImage ballImage;
    private static int ballX = 500;
    private static int ballY = 496;
    private int frame = 0;
    private double lastCheck;
    private static final int FPS = 144;
    private static boolean springStatus = false, releaseSignal = false;    
    int springX=500, threadCounter = 0;
    long springPressedDuration;
    private static int springY=580;
    private static int springY2=600;
    private static int springY3=620;
    private static int springY4=640;
    private static int springWidth=30;
    int springFluc=5;
    boolean pressed;
    //0 pixel/ms 
    double speedY=0.02;
    // 0.07 pixel/ms^2
    double accelerate = 0.03;
    double deaccelerate = -0.05;
    Thread animator;
    public PlayPanel(){
        String filePath = "images/pixel_ball.png";
        try{
            File imageFile = new File(filePath);
            ballImage = ImageIO.read(imageFile);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        this.setBackground(Color.orange);
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
        drawSpring(g2d);
        drawBaw(g2d);
        drawLines(g2d);
        drawTarget(g2d, 70, 200);
        frame++;
        //1 second has passed
        if (System.currentTimeMillis() - lastCheck >=1000){
            System.out.println("FPS: "+ frame);
            lastCheck = System.currentTimeMillis();
            frame =0;
        }
    }

    public void drawBaw(Graphics2D g2d){
        g2d.drawImage(ballImage, ballX, ballY, 100, 100, null);
        Toolkit.getDefaultToolkit().sync();
    }

    public void drawLines(Graphics2D g2d){
        g2d.setStroke(new BasicStroke(10));
        Line2D lineOne = new Line2D.Double(500, 150, 500, 680);
        Line2D lineTwo = new Line2D.Double(600, 150, 600, 680);
        Line2D ground = new Line2D.Double(0, 680, 2000, 680);
        g2d.draw(lineOne);
        g2d.draw(lineTwo);
        g2d.draw(ground);
        Toolkit.getDefaultToolkit().sync();

    }
    public void drawSpring(Graphics2D g2d){
        g2d.drawOval(springX,springY,100,springWidth);
        g2d.drawOval(springX,springY+20,100,springWidth);
        g2d.drawOval(springX,springY+40,100,springWidth);
        g2d.drawOval(springX,springY+60,100,springWidth);
    }

    public void drawTarget(Graphics2D g2d,int size, int height){
        g2d.drawRect(500,height,100,size);
    }

    public void startAnimation() {
        animator = new Thread(this);
        //Only start 1 thread
        if (threadCounter ==0){
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
        springX=500; 
        springY=580;
        springY2=600;
        System.out.println("BALL LAUNCHED!!!!!!");
        springY3=620;
        springY4=640;
        springWidth = 30;
    }
    //Called with the reset button
    public void reset(){
        ballY = 496;
        springX=500; 
        threadCounter = 0;
        springY=580;
        springY2=600;
        System.out.println("RESET!!!!!!");
        springY3=620;
        springY4=640;
        springWidth = 30;
        springStatus = false;
        releaseSignal = false;
        springPressedDuration = 0;
        animator.interrupt();
        repaint();
    }
    //Handle ball coordinates
    public void cycle(){
        speedY +=deaccelerate;
        //System.out.println(ballY + " " + speedY + " " + bounces);
//        ballY-=(int) springPressedDuration/100;
        ballY-=springPressedDuration/500;
        if (springPressedDuration>=0){
            springPressedDuration-=3;
        }
        System.out.println(springPressedDuration);
        // if (ballY > 496){
        //     //Ball sinks to the ground so i just stop the animation at this point
        //     if (Math.abs(speedY)<0.7 || ballY > 505){
        //         //reset();
        //         //System.out.println("STOPPED!");
        //     }
        //     // speedY+=(1.3-bounces*accelerate);
        //     // speedY+=(1.3);
        //     // bounces++;
        //     // speedY*=-1;
        // }
    }
    //Handle Springs coordinates
    public void compressSpring(){
        //System.out.println(springY);
        //Ball and spring move at the same speed
        springY += 1;
        ballY +=1;

    }
    @Override
    public void run(){
        double beforeTime, currTime, timePerFrame;
        timePerFrame = 1000000000/FPS;
        beforeTime = System.nanoTime();
        //springStatus is true when the spring is compressing when the user is holding down the mouse button
        System.out.println("SS "+ springStatus +" RS " + releaseSignal);
        while (springStatus) {
            currTime = System.nanoTime();
            //Rendering at lower frame to slow down the lowering speed of the spring
            //Setting the lower speed to lower than 1 doesnt change the position of the spring
            if (currTime - beforeTime >= timePerFrame*5){
                //System.out.println("SPRING!");
                compressSpring();
                repaint();
                beforeTime = currTime;
            }
        }
        while (releaseSignal) {
            currTime = System.nanoTime();
            if (currTime - beforeTime >= timePerFrame){
                cycle();
                //System.out.println("BALL FLYING!");
                repaint();
                beforeTime = currTime;
            }
        }
        // while (initiated) {
        //     currTime = System.nanoTime();
        //     if (currTime - beforeTime >= timePerFrame*5){
        //         cycle();
        //         //compressSpring();
        //         repaint();
        //         beforeTime = currTime;
        //     }
        // }
}
}