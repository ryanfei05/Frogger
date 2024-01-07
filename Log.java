/* 
Log class defines all the behaviors of log object
*/ 
import java.awt.*;

//Extends from car class, so has all characteristics of movement speed and resetting if out of window
public class Log extends Car{
  
  //constructor creates log with position and speed
  public Log(int x, int y, int s){  
    super(x, y, s);
  }

  //draws log
  public void draw(Graphics g){
    g.setColor(new Color(200, 110, 0));
    g.fillRect(x, y, 100, 50);
    g.setColor(new Color(150, 75, 0));
    g.fillRect(x+5, y+4, 90, 42);
  }

}