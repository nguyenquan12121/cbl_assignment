package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.border.EmptyBorder;

import main.Game;


class StartingPanel extends JPanel {
    private BufferedImage backgroundImage;

    public StartingPanel(Frame frame) {
        String backgroundPath = "images/menu_background.jpg";
        try {
            File backFile = new File(backgroundPath);
            backgroundImage = ImageIO.read(backFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //this.setBorder(new EmptyBorder(10, 300, 40, 250));
        ImageIcon imageIcon = new ImageIcon("images/HighStriker_Logo.png");
        JLabel jl = new JLabel(imageIcon);
        jl.setBorder(new EmptyBorder(10, 280, 40, 250));

        JButton play = new JButton();
        
       play.setIcon(new ImageIcon("images/Play_Button3.png")); 
        play.setPreferredSize(new Dimension(120, 42));
        play.setBorder(null);
        play.setOpaque(false);
        play.setContentAreaFilled(false);
        play.setBorderPainted(false);

        JButton leaderboard = new JButton();
        leaderboard.setIcon(new ImageIcon("images/Leaderboard.png")); 
        leaderboard.setPreferredSize(new Dimension(130, 42));
        leaderboard.setBorder(null);
        leaderboard.setOpaque(false);
        leaderboard.setContentAreaFilled(false);
        leaderboard.setBorderPainted(false);
        JButton exit = new JButton();
        exit.setIcon(new ImageIcon("images/Exit_Button.png")); 
        exit.setPreferredSize(new Dimension(143, 42));
        exit.setBorder(null);
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);

        this.add(jl);
        this.add(play);
        this.add(leaderboard);
        this.add(exit);
       //this.setLayout(new GridLayout(4, 1, 1, 1));
        play.addActionListener(e -> {
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

    public void drawBackground(Graphics2D g2d) {
        g2d.drawImage(backgroundImage, 0, 0, 800, 600, null);
    }
}
