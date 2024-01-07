/* 
Car class defines all the behaviors of the car obstacles
*/ 
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Car extends Rectangle{

  //Car Sprite
  private BufferedImage carsprite;
  public int xVelo; //car speed
  
  
  //constructor creates the obstacle at a location and specified speed
  public Car(int x, int y, int s){
    
    super(x, y, 100, 50);
    this.xVelo = s;
    //Add car sprite
    try {
      carsprite = ImageIO.read(this.getClass().getResource("resources/car.png"));
    } catch (IOException e) {
    }
  }

  //update the position of the car 
  public void move(){
    x = x + xVelo;

    //If car is moving right and goes out of screen, reset on the left of the screen
    //If car is moving left and goes out of screen, reset on the right of the screen
    if(xVelo >0 &&x > 550){
      x = -100;
    } else if(x< -100){
      x = 550;
    }
  }

  //draw the car to the screen (
  public void draw(Graphics g){
    g.drawImage(carsprite, x, y, null);
  }

}