/* 
Score class keeps track of wins
*/ 
import java.awt.*;

public class Score extends Rectangle{
    //Win count
    public int win;
    public int lives;
    //Constructor starts at 0 wins
    public Score() {
        win = 0;
        lives = 3;
    }
    //Draws string of score on frame
    public void draw(Graphics g){
        g.setColor(Color.black);
        g.setFont(new Font("Dialog", Font.BOLD, 35));
        g.drawString("Wins: " + win, 10, 30);
        g.setColor(Color.red);
        g.drawString("Lives: " + lives, 150, 30);
    }
}
