import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

class DrawingLine extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(10));
        Line2D lineOne = new Line2D.Double(750, 150, 750, 600);
        Line2D lineTwo = new Line2D.Double(650, 150, 650, 600);
        Line2D ground = new Line2D.Double(0, 600, 2000, 600);
        g2d.draw(lineOne);
        g2d.draw(lineTwo);
        g2d.draw(ground);
    }


    // @Override
    // public Dimension getPreferredSize() {
    //     return new Dimension(1080, 720);
    // }
}