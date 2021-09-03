package invadem;
import processing.core.*;
public class Invader extends Picture{
  private boolean alive = true;
  public Invader(PImage[] pic, int x, int y, int width, int height){
    super(pic, x, y, width, height);
  }
}
