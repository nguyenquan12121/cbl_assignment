import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
//Class to manage score, rounds, force display
class InformationPanel extends JPanel{
    int score, totalScore, currRound =1;
    BufferedImage backgroundImage;
    JLabel force, scoreLabel, totalScoreLabel, currRoundLabel, roundInfo;

    public InformationPanel(){
        loadGraphics();
    }

    public void loadGraphics(){
        String backgroundPath = "images/background_score.jpg";
        try{
            File backFile = new File(backgroundPath);
            backgroundImage = ImageIO.read(backFile);

        }
        catch(IOException e){
            e.printStackTrace();
        }       
        force = new JLabel("Force: 0 N");
        scoreLabel = new JLabel("Score: 0");        
        totalScoreLabel = new JLabel("Total Score: 0");        
        currRoundLabel = new JLabel("Current Round: "+Integer.toString(currRound));
        roundInfo = new JLabel();
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

        this.setLayout(new GridLayout(5, 1, 0,-300));  
        this.add(currRoundLabel);
        this.add(totalScoreLabel);
        this.add(scoreLabel);         
        this.add(force);
        this.add(roundInfo);
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
