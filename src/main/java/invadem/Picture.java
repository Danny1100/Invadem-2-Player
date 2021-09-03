package invadem;
import processing.core.*;
public class Picture{
  private PImage[] pic;
  private int x;
  private int y;
  private int width;
  private int height;
  private int[] velocity = {1, 1};

  public Picture(PImage[] pic, int x, int y, int width, int height){
    this.pic = pic;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }
  public PImage[] getPic(){
    return pic;
  }
  public int getX(){
    return x;
  }
  public int getY(){
    return y;
  }
  public int getWidth(){
    return width;
  }
  public int getHeight(){
    return height;
  }
  public void addX(){
    this.x += velocity[0];
  }
  public void minusX(){
    this.x -= velocity[0];
  }
  public void addY(){
    this.y += velocity[1];
  }
  public void minusY(){
    this.y -= velocity[1];
  }

  public boolean checkCollision(Picture p2){
    if ((this.getX() < p2.getX() + p2.getWidth())
    && (p2.getX() < this.getX() + this.getWidth())
    && (this.getY() < p2.getY() + p2.getHeight())
    && (p2.getY() < this.getY() + this.getHeight()))
    {
      return true;
    }
    return false;
  }
}
