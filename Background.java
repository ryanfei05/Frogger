/* 
Background class applies background image
*/ 
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Background extends Rectangle {
    //Background image
    private BufferedImage background;
    //constructor for image location
    public Background(int x, int y){
        super(x, y, 500, 550);
        try {
          background = ImageIO.read(this.getClass().getResource("resources/background1.jpg"));
        } catch (IOException e) {
        }
    
      }
      //Draws background from gamepanel
      public void draw(Graphics g){
        g.drawImage(background, x, y, null);
      }

}
