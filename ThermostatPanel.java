import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

class ThermostatPanel extends JPanel {
    private static BufferedImage thermoStat;
    public ThermostatPanel( ){
        String thermoPath = "images/highstriker.png";
        try{
            File thermoFile = new File(thermoPath);
            thermoStat = ImageIO.read(thermoFile);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawThermoStat(g2d);
    }
    public void drawThermoStat(Graphics2D g2d){
        g2d.drawImage(thermoStat, 100,-50 , 600, 1000, null);
        Toolkit.getDefaultToolkit().sync();
    }
}