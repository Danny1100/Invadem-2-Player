package invadem;
import processing.core.*;
public class Tank extends Picture{
  private int lives;

  public Tank(PImage[] pic, int lives, int x, int y, int width, int height){
    super(pic, x, y, width, height);
    this.lives = lives;
  }
  public Projectile shoot(PImage graphic){
    return new Projectile(new PImage[] {graphic}, this.getX() + 11, this.getY() - 10, 1, 3);
  }
  public void loseLife(){
    lives--;
  }
  public int getLives(){
    return lives;
  }
}
