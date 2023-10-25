import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

class MenuPanel extends JPanel implements ActionListener {
    ButtonPanel bp;
    InformationPanel ip;
    Timer timer; 
    int score=0;
    long display;
    PlayPanel ppMain;
    long startTime = -1l;
    long endTime = -1l;
    public MenuPanel(PlayPanel pp){
        this.ppMain = pp;
        timer = new Timer(10, this);
        timer.start();
        ip = new InformationPanel();
        bp = new ButtonPanel();
        this.ppMain.addInfoPanel(ip);
        this.setLayout(new BorderLayout());        
        this.add(bp, BorderLayout.NORTH);
        this.add(ip);        
    
        bp.bounce.addMouseListener(
            // I wanted to use MouseAction but it doesnt work
            new MouseListener() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent arg0) {
                }

                @Override
                public void mouseEntered(java.awt.event.MouseEvent arg0) {
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent arg0) {
                }

                @Override
                public void mousePressed(java.awt.event.MouseEvent arg0) {
                    startTime = System.currentTimeMillis();
                    timer.start();
                }

                @Override
                public void mouseReleased(java.awt.event.MouseEvent arg0) {
                    endTime = System.currentTimeMillis();
                    //ppMain.setTimer(true, duration);
                    //spring timer is used in actionlistener to pull down the lever so set it false here
                    ppMain.setSpringTimer(false, display);
                    ppMain.launchBall();
                }
            }

        );
        bp.stop.addActionListener(e -> ppMain.setTimer(false, 0l));
        bp.reset.addActionListener( e ->{
            startTime = -1l;
            endTime = -1l;           
            ip.force.setText("Force: 0");
            ip.scoreLabel.setText("Score: 0");
            timer.stop();
            ppMain.reset();
        });
        bp.stop.addActionListener(e -> {
            ip.updateRound();
        });    
    }


    
    @Override
    public void actionPerformed(ActionEvent e){
        if (startTime != -1l){
            display = System.currentTimeMillis() - startTime;
            ppMain.setSpringTimer(true, display);
            ip.force.setText("Force: "+ Long.toString(display));
        repaint();
        }
        if (endTime !=-1l){
            timer.stop();
        }
    }
}


class InformationPanel extends JPanel{
    int score, totalScore, currRound =1;
    BufferedImage backgroundImage;
    JLabel force, scoreLabel, totalScoreLabel, currRoundLabel;
    public InformationPanel(){
        String backgroundPath = "images/control_panel.jpg";
        try{
            File backFile = new File(backgroundPath);
            backgroundImage = ImageIO.read(backFile);

        }
        catch(IOException e){
            e.printStackTrace();
        }       
        force = new JLabel("Force: 0");
        scoreLabel = new JLabel("Score: 0");        
        totalScoreLabel = new JLabel("Total Score: 0");        
        currRoundLabel = new JLabel("Current Round: "+Integer.toString(currRound));
        //Center the text
        force.setHorizontalAlignment(JLabel.CENTER);        
        force.setForeground(Color.WHITE);
        force.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);        
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        totalScoreLabel.setHorizontalAlignment(JLabel.CENTER);        
        totalScoreLabel.setForeground(Color.WHITE);
        totalScoreLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));        
        currRoundLabel.setHorizontalAlignment(JLabel.CENTER);        
        currRoundLabel.setForeground(Color.WHITE);
        currRoundLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));        

        this.setLayout(new GridLayout(4, 1, 0,-300));  
        this.add(currRoundLabel);
        this.add(totalScoreLabel);
        this.add(scoreLabel);         
        this.add(force);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawBackground(g2d);
    }

    public void drawBackground(Graphics2D g2d){
        g2d.drawImage(backgroundImage, 0, 0,200,800, null);
    }

    public void updateCurrScore(int score){
        this.score = score;
        this.scoreLabel.setText("Score: "+ Integer.toString(this.score));
        updateTotalScore();
    }

    public void updateTotalScore(){
        totalScore += score;
        this.totalScoreLabel.setText("Total Score: " + Integer.toString(totalScore));
    }
    public void updateRound(){
        this.currRound++;
        this.currRoundLabel.setText("Current Round: "+ currRound);
    }
}


//Class to manage 4 buttons
class ButtonPanel extends JPanel{
    JButton bounce,stop, reset, next;
    public ButtonPanel(){
        this.setLayout(new GridLayout(2, 2,0,0));
        //add button to the pane
        bounce = new JButton("Bounce!");
        stop = new JButton("Stop!");
        reset = new JButton("Reset!");
        next = new JButton("Next!");
        this.add(bounce);
        this.add(stop);
        this.add(reset);
        this.add(next);
    }
}