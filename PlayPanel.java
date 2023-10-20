import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.util.concurrent.TimeUnit;

class PlayPanel extends JPanel implements Runnable {
    private static BufferedImage ballImage;
    private static int ballX = 500;
    private static int ballY = 496;
    private int frame = 0, bounces =0;
    private double lastCheck;
    private static final int FPS = 144;
    private static boolean initiated = false;
    //0 pixel/ms 
    double speedY=5;
    // 0.07 pixel/ms^2
    double accelerate = 0.03;
    double deaccelerate = -0.05;
    int springX=500;
    int springY=580;
    int springY2=600;
    int springY3=620;
    int springY4=640;
    int springWidth=30;
    int springFluc=5;
    boolean pressed;
    Thread animator;
    boolean cont=true;
    boolean exp=true;
    int time;
    //fMenuPanel menuPanel= new MenuPanel();
    public PlayPanel(){
        String filePath = "aimages/1200px-Soccerball.svg.png";
        try{
            File imageFile = new File(filePath);
            ballImage = ImageIO.read(imageFile);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        this.setBackground(Color.white);
    }
    public void setTimer(boolean status, long duration){
        if (status){
            initiated = true;
            startAnimation();
            speedY = duration/500;
            time=(int) duration;
        }
        else{
            initiated = false;
            animator.interrupt();
        }
    }

    public void reset(){
        ballY = 496;
        springY=580;
        springY2=600;
        springY3=620;
        springY4=640;
        springWidth=30;
        bounces = 0;
        initiated = false;
        animator.interrupt();
        repaint();
        cont=true;
        exp=true;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawLines(g2d);
        drawSpring(g2d);
        drawBall(g2d);
        drawTarget(g2d, 70, 200);
        
        frame++;
        //1 second has passed
        if (System.currentTimeMillis() - lastCheck >=1000){
            //System.out.println("FPS: "+ frame);
            lastCheck = System.currentTimeMillis();
            frame =0;
        }


    }

    public void drawBall(Graphics2D g2d){
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
        animator.start();
        lastCheck = System.currentTimeMillis();
    }

    public void cycle(){
        speedY +=deaccelerate;
       //System.out.println(ballY + " " + speedY + " " + bounces);
        ballY-=speedY;
        if (ballY > 496){
            //Ball sinks to the ground so i just stop the animation at this point
          //if (Math.abs(speedY)<0.3 || ballY > 800){
             reset();
           // System.out.println("STOPPED!");
           // }
            // speedY+=(1.3-bounces*accelerate);
          //  speedY+=(1.3);
            //bounces++;
           // speedY*=-1;
        }
    }
    public void setcont (boolean a){
        cont=a;
    }
    public void contractSpring(){
        int force=getForce();
            if(springY+springWidth<(580+force)){
            springY=springY+springFluc;
            System.out.println(springY=springY+springFluc);
            ballY=springY-75;
            }
             if((springY2+springWidth)<(580+force)){ 
                springY2=springY2+springFluc;
            }
            if((springY3+springWidth)<(580+force)){
                springY3=springY3+springFluc;
            }
            if((springY4+springWidth)<600+(force-25)){
                springY4=springY4+springFluc;
            }
            if(springWidth>(30-(9*(force/20)))){
                System.out.println("Hello");
                springWidth-=5;
           }
            else{
               cont=false;
            
        }
    
    }
public int getForce(){
    int force=time/70; //possibly add multiplier
        if(force>50){
            force=50;
        }
    return force;
}

public void expandSpring(){
    System.out.println(springY-springWidth);
            if(springY>580){
            springY=springY-springFluc;
            ballY=springY-75;} 
           if(springY2-springWidth>600){ 
            springY2=springY2-springFluc;
           }
            if(springY3-springWidth>620){
            springY3=springY3-springFluc;
            }
            if(springY4-springWidth>640){
            springY4=springY4-springFluc;
            }
            if(springWidth<30){
                springWidth+=5;
            }
            else{
                springY=580;
                springY2=600;
                springY3=620;
                springY4=640;
                exp=false;
            
            
        }
    }

    
    

    @Override
    public void run(){
        double beforeTime, currTime, timePerFrame;
        timePerFrame = 999999999/FPS;
        beforeTime = System.nanoTime();
        while (cont) {
            currTime = System.nanoTime();
            try {
                Thread.sleep(100);
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            if (currTime - beforeTime >= timePerFrame){
                contractSpring();
                repaint();
                beforeTime = currTime;
            }
        }
        while (exp) {
            currTime = System.nanoTime();
            try {
                Thread.sleep(50);
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
            if (currTime - beforeTime >= timePerFrame){
                expandSpring();
                repaint();
                beforeTime = currTime;
            }
        }
       while (initiated) {
            currTime = System.nanoTime();
            if (currTime - beforeTime >= timePerFrame){
                cycle();
                repaint();
                beforeTime = currTime;
            }
    }

}
}
