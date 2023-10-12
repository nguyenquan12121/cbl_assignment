import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;


public class Ball{
    Point location;
    Dimension size;
    BufferedImage image;    
    /**
     * @param location
     * @param size
     * @param image
     */
    public Ball(Point location, Dimension size, BufferedImage image) {
        this.location = location;
        this.size = size;
        this.image = image;
    }    
}