import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.border.EmptyBorder;


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

        this.setBorder(new EmptyBorder(10, 300, 40, 250));
        JLabel jl = new JLabel("Hot Highstriker!");
        jl.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        jl.setForeground(Color.RED);

        JButton play = new JButton("Play!");
        play.setIcon(new ImageIcon("control_panel.jpg")); 
        play.setPreferredSize(new Dimension(20, 10)); 

        JButton leaderboard = new JButton("Show leaderboard!");
        JButton exit = new JButton("Exit!");

        this.add(jl);
        this.add(play);
        this.add(leaderboard);
        this.add(exit);
        this.setLayout(new GridLayout(4, 1, 3, 50));
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
