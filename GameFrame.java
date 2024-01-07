/* 
GameFrame class creates the frame, and then calls GamePanel
*/ 

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame{


  GamePanel panel;

  public GameFrame(){
    panel = new GamePanel(); 

    //Creating Icon Image, Title, and other Frame Settings
    ImageIcon image = new ImageIcon("resources/icon.png");
    this.add(panel);
    this.setTitle("Frogger"); 
    this.setResizable(false); //frame can't change size
    this.setBackground(Color.white);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //X button will stop program execution
    this.pack();
    this.setVisible(true); //makes window visible 
    this.setLocationRelativeTo(null);
    this.setIconImage(image.getImage());
  }

}