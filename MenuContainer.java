
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



public class MenuContainer extends JPanel {
    BufferedImage backgroundImage;
    public MenuContainer(){
        String backgroundPath = "images/menu_background.jpg";
        try{
            File backFile = new File(backgroundPath);
            backgroundImage = ImageIO.read(backFile);

        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
    public static void addComponentsToPane (JFrame frame)  {
        MainPanel mp = new MainPanel(frame);
        frame.add(mp);
    }



    private static void createAndShowGUI() {

        //Create and set up the window.
        JFrame frame = new JFrame("Welcome Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setPreferredSize(new Dimension(1280, 720));
        //Set up content pane
        addComponentsToPane(frame);


        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}


class MainPanel extends JPanel{
    private BufferedImage backgroundImage;
    public MainPanel(Frame frame){
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