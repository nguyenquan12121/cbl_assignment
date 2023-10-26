import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class EndGamePanel extends JPanel{

    private int highScore;

    public EndGamePanel(Frame frame, int score){
        this.highScore = score;
        JLabel scoreLabel = new JLabel();
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton play = new JButton("Update DB");
        JButton exit = new JButton("Return to Start Screen!");
        JTextField userName = new JTextField("Please enter your name!");
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);        
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        scoreLabel.setText("CONGRATULATIONS! YOUR HIGH SCORE IS "+ this.highScore);
        this.add(scoreLabel);
        this.add(play);
        this.add(exit);
        this.add(userName);
        this.setLayout(new GridLayout(5, 1, 0,0));  
        this.setBackground(Color.red);
        play.addActionListener(e ->{
        });
        exit.addActionListener(e -> System.exit(-1));        
        

    }
}