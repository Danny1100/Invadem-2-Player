package invadem;

import org.junit.Test;
import static org.junit.Assert.*;
import processing.core.*;
import java.util.*;

public class BarrierTest extends PApplet{
  PImage barrier_pic1;
  PImage barrier_pic2;
  PImage barrier_pic3;
   // public void setup(){
   //   barrier_pic1 = loadImage("src/test/resources/barrier_left1.png");
   //   barrier_pic2 = loadImage("src/test/resources/barrier_left2.png");
   //   barrier_pic3 = loadImage("src/test/resources/barrier_left3.png");
   // }

   @Test
   public void barrierConstruction() {
     // testing that a barrier will lose health
    PImage[] barrier_images = new PImage[1];
    barrier_pic1 = new PImage(8, 8);
    barrier_images[0] = barrier_pic1;
    // barrier_images[1] = barrier_pic2;
    // barrier_images[2] = barrier_pic3;
    Barrier b = new Barrier(barrier_images, 210, 420, 8, 8);
    assertEquals(b.getHealth(), 3);
    assertNotNull(b);
    b.loseHealth();
    assertNotNull(b);
    assertEquals(b.getHealth(), 2);
   }
   // @Test
   // public void testBarrierNotDestroyed() {
   //     Barrier b = /* Your Constructor Here */
   //     assertEquals(false, b.isDestroyed());
   // }
   //
   // @Test
   // public void testBarrierHitPointsMax() {
   //     Barrier b = /* Your Constructor Here */
   //     assertEquals(3, b.hitPoints());
   // }
   //
   // @Test
   // public void testBarrierHitPointsMax() {
   //     Barrier b = /* Your Constructor Here */
   //     b.hit();
   //     assertEquals(2, b.hitPoints());
   // }
   //
   // @Test
   // public void testBarrierHitPointsMax() {
   //     Barrier b = /* Your Constructor Here */
   //     b.hit();
   //     b.hit();
   //     assertEquals(1, b.hitPoints());
   // }
   //
   //
   // @Test
   // public void testBarrierIsDestroyed() {
   //     Barrier b = /* Your Constructor Here */
   //     b.hit();
   //     b.hit();
   //     b.hit();
   //     assertEquals(false, b.isDestroyed());
   // }

}
