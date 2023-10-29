package ui;

import entity.LeaderboardEntry;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import utils.WriteData;

/** Endgame panel class which displays the user final score and prompts them to save their record.
* 
*/
class EndGamePanel extends JPanel {
    static int clicked = 0;
    EndGameLeaderboardPanel endGameLeaderboard;
    int highScore;
    LeaderboardEntry entry;
    String filePath = "leaderboard.txt";
    JFrame frame;
    JLabel scoreLabel;

    /** Constructor class.
    * 
    */
    public EndGamePanel(LeaderboardEntry entry, EndGameLeaderboardPanel eglp) {
        this.endGameLeaderboard = eglp;
        this.setBorder(new EmptyBorder(100, 50, 100, 50));
        this.entry = entry;
        this.highScore = entry.score;
        scoreLabel = new JLabel();
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
        scoreLabel.setForeground(Color.GREEN);
        scoreLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        scoreLabel.setText("CONGRATULATIONS! YOUR HIGH SCORE IS " + this.highScore);
        this.add(scoreLabel);
        this.add(userName);
        this.add(save);
        this.add(quit);
        this.setOpaque(false);
        this.setLayout(new GridLayout(5, 1, 20, 30));
        save.addActionListener(e -> {
            if (clicked == 0) {
                entry.updateUsername(userName.getText());
                this.endGameLeaderboard.updateRecord(entry);
                clicked++;
                List<LeaderboardEntry> list = this.endGameLeaderboard.recordList;
                WriteData.writeToFile(filePath, list);                
            }
        });
        quit.addActionListener(e -> System.exit(-1));           
    }

    /** add frame to the current panel to be disposed.
    * 
    */    
    public void addFrame(JFrame frame) {
        this.frame = frame;
    }

    /** add leaderboard entry.
    * 
    */    
    public void addEntry(LeaderboardEntry entry) {
        this.entry = entry;
    }
}


