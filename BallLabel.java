import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.RenderingHints;


class BallLabel{
    private static BufferedImage ballImage;
    private static BufferedImage scaledImage;

    public static BufferedImage drawBall(){
        String filePath = "images/1200px-Soccerball.svg.png";
        try{
            File imageFile = new File(filePath);
            ballImage = ImageIO.read(imageFile);
            scaledImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = scaledImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(ballImage, 0, 0, 100, 100, null);
            g2d.dispose();         
        }
        catch(IOException e){
            e.printStackTrace();
        }
    return scaledImage;
    }
}