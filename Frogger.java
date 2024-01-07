/* 
Frogger class defines all the behaviors of the frog, or player
*/ 
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Frogger extends Rectangle implements KeyListener{
  
  //Character Sprite
  private BufferedImage frogsprite;
  public Log attached = null;
  public static final int FROG_SIZE = 50; 
  
  //constructor creates frog at specified location
  public Frogger(int x, int y){
    super(x, y, FROG_SIZE, FROG_SIZE);
    //Add frog sprite
    try {
      frogsprite = ImageIO.read(this.getClass().getResource("resources/frogsprite1.png"));
    } catch (IOException e) {
    }

  }

  //called from GamePanel to check for keyboard input (only wasd)
  //only moves if location is within frame
  public void keyPressed(KeyEvent e){
    
    if(e.getKeyChar() == 'd'){
      if (x < 400){
        x += 50;
      }
    }

    if(e.getKeyChar() == 'a'){
      if (x > 25){
        x -= 50;
      }
    }

    if(e.getKeyChar() == 'w'){
      if (y>5){
        y -= 50;
        
      } 
    }

    if(e.getKeyChar() == 's'){
      if (y < 500){
        y += 50;
      }
    }
  }
  //Attaches frog to a certain log
  public void attach(Log log){
    attached = log;
  }
  //The only time the frog will move if it is on a log, and it will obtain the same x speed as the log
  public void move(){
    if (attached != null){
      x += attached.xVelo;
    }
  }

  //draws the current location of the frog 
  public void draw(Graphics g){
    g.drawImage(frogsprite, x, y, null);
  }

  @Override
  public void keyTyped(KeyEvent e) {
    
  }

  @Override
  public void keyReleased(KeyEvent e) {
    
  }
  
}