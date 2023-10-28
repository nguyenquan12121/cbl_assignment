package ui;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import entity.LeaderboardEntry;
import utils.WriteData;

class EndGamePanel extends JPanel{
    static int clicked = 0;
    EndGameLeaderboardPanel endGameLeaderboard;
    int highScore;
    LeaderboardEntry entry;
    String filePath = "leaderboard.txt";
    JFrame frame;
    public EndGamePanel(LeaderboardEntry entry, EndGameLeaderboardPanel eglp){
        this.endGameLeaderboard = eglp;
        this.setBorder(new EmptyBorder(100, 50, 100, 50));
        this.entry = entry;
        this.highScore = entry.score;
        JLabel scoreLabel = new JLabel();
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton save = new JButton();
        save.setIcon(new ImageIcon("images/Leaderboard.png")); 
        save.setBorder(null);
        save.setOpaque(false);
        save.setContentAreaFilled(false);
        save.setBorderPainted(false);        
        JButton quit = new JButton();
        quit.setIcon(new ImageIcon("images/Exit_Button.png")); 
        quit.setBorder(null);
        quit.setOpaque(false);
        quit.setContentAreaFilled(false);
        quit.setBorderPainted(false); 
        JTextField userName = new JTextField();
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);        
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        scoreLabel.setText("CONGRATULATIONS! YOUR HIGH SCORE IS "+ this.highScore);
        this.add(scoreLabel);
        this.add(userName);
        this.add(save);
        this.add(quit);
        this.setOpaque(false);
        this.setLayout(new GridLayout(5, 1, 20,30));  
        save.addActionListener(e ->{
            if (clicked==0){
                entry.updateUsername(userName.getText());
                this.endGameLeaderboard.updateRecord(entry);
                clicked++;
                List<LeaderboardEntry> list = this.endGameLeaderboard.recordList;
                WriteData.writeToFile(filePath, list);                
            }
        });
        quit.addActionListener(e -> System.exit(-1));           
    }
    public void addFrame(JFrame frame){
        this.frame = frame;
    }
    public void addEntry(LeaderboardEntry entry){
        this.entry = entry;
    }
}


