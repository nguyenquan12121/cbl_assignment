import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

class EndGamePanel extends JPanel{

    int highScore;
    LeaderboardEntry entry;
    public EndGamePanel(Frame frame, int score, LeaderboardEntry entry){
        this.setBorder(new EmptyBorder(100, 50, 100, 50));
        this.entry = entry;
        this.highScore = score;
        JLabel scoreLabel = new JLabel();
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton save = new JButton("Save To Leaderboard");
        JButton returnStart = new JButton("Return to Start Screen!");
        JButton quit = new JButton("Quit To System");
        JTextField userName = new JTextField();
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);        
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        scoreLabel.setText("CONGRATULATIONS! YOUR HIGH SCORE IS "+ this.highScore);
        this.add(scoreLabel);
        this.add(userName);
        this.add(save);
        this.add(returnStart);
        this.add(quit);
        this.setLayout(new GridLayout(5, 1, 20,60));  
        this.setBackground(Color.red);
        save.addActionListener(e ->{
        });
        quit.addActionListener(e -> System.exit(-1));
        returnStart.addActionListener(e -> {
            frame.dispose();
            StartingContainer.createAndShowGUI();
        });        
        //this.setBounds(0, 0, 700, 700);
    }
}


