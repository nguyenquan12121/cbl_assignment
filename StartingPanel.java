import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

class StartingPanel extends JPanel{
    private BufferedImage backgroundImage;
    public StartingPanel(Frame frame){
        String backgroundPath = "images/menu_background.jpg";
        try{
            File backFile = new File(backgroundPath);
            backgroundImage = ImageIO.read(backFile);

        }
        catch(IOException e){
            e.printStackTrace();
        }
        this.setBorder(new EmptyBorder(100, 500, 100, 500));
        JLabel jl = new JLabel("Hot Highstriker!");
        jl.setFont((new Font(Font.DIALOG, Font.PLAIN, 30)));
        jl.setForeground(Color.WHITE);
        JButton play = new JButton("Play!");
        JButton leaderboard = new JButton("Show leaderboard!");
        JButton exit = new JButton("Exit!");
        this.add(jl);
        this.add(play);
        this.add(leaderboard);
        this.add(exit);
        this.setLayout(new GridLayout(4,1,3,50));
        play.addActionListener(e ->{
            frame.dispose();
            new Game();
        });

        leaderboard.addActionListener(e->{
            frame.dispose();
            LeaderboardContainer.createAndShowGUI();
        });

        exit.addActionListener(e -> System.exit(-1));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawBackground(g2d);
    }

    public void drawBackground(Graphics2D g2d){
        g2d.drawImage(backgroundImage, 0, 0,1400,1400, null);
    }
}