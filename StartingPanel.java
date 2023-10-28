import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


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
        jl.setBorder(new EmptyBorder(10, 300, 40, 250));
        jl.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        jl.setForeground(Color.RED);

        JButton play = new JButton();
        
       play.setIcon(new ImageIcon("images/Play_Button3.png")); 
        play.setPreferredSize(new Dimension(130, 50));
        play.setBorder(null);
        play.setOpaque(false);
        play.setContentAreaFilled(false);
        play.setBorderPainted(false);

        JButton leaderboard = new JButton();
        leaderboard.setIcon(new ImageIcon("images/Leaderboard.png")); 
        leaderboard.setPreferredSize(new Dimension(130, 50));
        leaderboard.setBorder(null);
        leaderboard.setOpaque(false);
        leaderboard.setContentAreaFilled(false);
        leaderboard.setBorderPainted(false);
        JButton exit = new JButton("Exit!");
        exit.setIcon(new ImageIcon("images/Exit_Button.png")); 
        exit.setPreferredSize(new Dimension(130, 50));
        exit.setBorder(null);
        exit.setOpaque(false);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);

        this.add(jl);
        this.add(play);
        this.add(leaderboard);
        this.add(exit);
       //this.setLayout(new GridLayout(4, 1, 3, 50));
        play.addActionListener(e -> {
            frame.dispose();
            new Game();
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
