package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class EndPanel extends JPanel{
    BufferedImage backgroundImage;
    public EndPanel(EndGamePanel egp, EndGameLeaderboardPanel eglp) {
        String backgroundPath = "images/background_score.jpg";
        try {
            File backFile = new File(backgroundPath);
            backgroundImage = ImageIO.read(backFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setLayout(new GridLayout(1, 2));
        this.add(egp);
        this.add(eglp);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawBackground(g2d);
    }

    public void drawBackground(Graphics2D g2d) {
        g2d.drawImage(backgroundImage, 0, 0, 1200, 800, null);
    }
}