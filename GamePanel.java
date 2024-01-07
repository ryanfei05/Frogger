/* 
GamePanel class is the main game loop, calling classes of objects and running the game
*/ 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, KeyListener{

  
  //dimensions of window
  public static final int GAME_WIDTH = 500;
  public static final int GAME_HEIGHT = 550;

  //Creating objects of all the game classes
  public Thread gameThread;
  public Image image;
  public Background background;
  public Graphics graphics;
  public Frogger frog;
  public Score score;
  public Car cars[];
  public Log logs[];

  //GamePanel Constructor initializing all game components
  public GamePanel(){
    setup();
    this.setFocusable(true); //make everything in this class appear on the screen
    this.addKeyListener(this); //start listening for keyboard input
    this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));

    //make this class run at the same time as other classes (without this each class would "pause" while another class runs).
    gameThread = new Thread(this); 
    gameThread.start();
  }

  //Creating specific objects and locations for game elemsnt
  public void setup(){
    //Set background image, frog starting position, cars & logs, as well as the score
    background = new Background(0, 0);
    frog = new Frogger(225, 500); 
    cars = new Car[6]; //6 car objects
    logs = new Log[12];//12 log objects
    score = new Score();

    //Creating car positions along the grid, with spacing
    int c = 0;
    for (int i = 0; i<2; i++){
      int x = i*350;
      cars[c] = new Car(x, 450,2 );
      c++;
    }
    
    for (int i = 0; i<2; i++){
      int x = i*350 + 100;
      cars[c] = new Car(x, 400,-2 );
      c++;
    }
    for (int i = 0; i<1; i++){
      int x =250;
      cars[c] = new Car(x, 350,4 );
      c++;
    }
    for (int i = 0; i<1; i++){
      int x = 100;
      cars[c] = new Car(x, 300,-4);
      c++;
    }

    //Creating log positions along the grid, with spacing
    c = 0;
    for (int i = 0; i<4; i++){
      int x = i*300;
      logs[c] = new Log(x, 200,1);
      c++;
    }
    
    for (int i = 0; i<2; i++){
      int x = i*200 + 30;
      logs[c] = new Log(x, 150,-3 );
      c++;
    }
    for (int i = 0; i<2; i++){
      int x = i*200 + 30;
      logs[c] = new Log(x, 100,2);
      c++;
    }
    for (int i = 0; i<4; i++){
      int x = i*200 + 100;
      logs[c] = new Log(x, 50,-4);
      c++;
    }
    
  }

  public void paint(Graphics g){
    //"double buffering"
    image = createImage(GAME_WIDTH, GAME_HEIGHT); //draw off screen
    graphics = image.getGraphics();
    draw(graphics); //update the positions of everything on the screen 
    g.drawImage(image, 0, 0, this); //redraw everything on the screen

  }

  //call the draw methods in each class to update positions 
  public void draw(Graphics g){
    background.draw(g);
    
    for(Car car: cars){
      car.draw(g);
    }
    for(Log log: logs){
      log.draw(g);
    }
    frog.draw(g);
    score.draw(g);    
  }

  //call the move methods in other classes to update positions
  public void move(){
    for(Car car: cars){
      car.move();
    }
    for(Log log: logs){
      log.move();
    }
    frog.move();
  }

  //Reset frog's position
  public void resetFrog(){
    frog.x = 225;
    frog.y = 500;
    frog.attached = null;
  }
  
  //handles all collision detection for frog and obstacles
  public void checkCollision(){
    //If the frog touches a car, it will reset
    for(Car car: cars){
      if(frog.intersects(car)){
        score.lives--;
        resetFrog();
      }
    
    }
    //If the frogis past the cars and touches the water, it will reset unless it is touching a moving log
    if(frog.y <250){
      boolean safe = false;
      for(Log log: logs){
        //Frog gains same speed as log
        if (frog.intersects(log)){
          safe = true;
          frog.attach(log);
        }
      }
      if (!safe){
        if(frog.y >50){ //Makes sure that frog can reach the end without needing to be on a log
          score.lives--;
          resetFrog();
        }
        
      }
      //Frog resets if the log carries it out of the window
      if (frog.x <0 || frog.x >500){
        score.lives--;
        resetFrog();
      }
    } else{
      frog.attached = null;
    }

  }

  //If Frog reaches the end, add to score & reset
  public void detectWin() {

    if (frog.y < 10) {
      score.win++;
      if(score.win >= 1){
        for(Car car: cars){
          if(car.xVelo >0){
            car.xVelo += score.win;
          } else if(car.xVelo<0){
            car.xVelo -= score.win;
          }
  
        }
        for (Log log: logs){
          if(log.xVelo >0){
            log.xVelo += score.win;
          } else if(log.xVelo<0){
            log.xVelo -= score.win;
          }
        }
      }
      resetFrog();
    }
    if (score.lives == 0) {
      score.win = 0;
      setup();
      resetFrog();
    }
    
  }

    

  //run() method makes the game continue running without end. 
  public void run(){
    // The following lines of code "force" the computer to get stuck in a loop for short intervals between calling other methods to update the screen. 
    long lastTime = System.nanoTime();
    double amountOfTicks = 60;
    double ns = 1000000000/amountOfTicks;
    double delta = 0;
    long now;

    while(true){ //this is the infinite game loop
      now = System.nanoTime();
      delta = delta + (now-lastTime)/ns;
      lastTime = now;

      //Moving objects, checking for collisions, checking for win constraints, repainting
      if(delta >= 1){
        move();
        checkCollision();
        detectWin();
        repaint();
        delta--;
      }
    }
  }

  //check key press for frog movement
  public void keyPressed(KeyEvent e){
    frog.keyPressed(e);
  }

  //empty
  public void keyReleased(KeyEvent e){
    
  }

  //empty
  public void keyTyped(KeyEvent e){

  }

}