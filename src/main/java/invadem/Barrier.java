package invadem;
import processing.core.*;
public class Barrier extends Picture{
  private int health = 3;
  public Barrier(PImage[] pic, int x, int y, int width, int height){
    super(pic, x, y, width, height);
  }
  public int getHealth(){
    return health;
  }
  public void loseHealth(){
    health--;
  }
}
