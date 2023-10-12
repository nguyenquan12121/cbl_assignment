import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.RenderingHints;


class BallLabel extends JPanel{
    private Ball ball;
    private BufferedImage scaledImage;
    public BallLabel(Ball ball){
        this.ball = ball;
        setPreferredSize(ball.size);

    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (ball !=null){
            BufferedImage bi = ball.image;
            int x =(int) ball.size.getWidth();
            int y = (int) ball.size.getHeight();

            g2d.drawImage(bi, x,y,null);
            scaledImage = new BufferedImage(100, 100, bi.TYPE_INT_ARGB);
            g2d = scaledImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(bi, 0, 0, 100, 100, null);
            g2d.dispose();         
        }
    }
}