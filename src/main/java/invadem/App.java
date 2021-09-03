package invadem;
import java.util.*;
import processing.core.*; //PApplet
import java.lang.Math;

public class App extends PApplet {
  // public App() {}
  Tank t;
  Tank t2;
  ArrayList<Invader> invader_ls = new ArrayList<Invader>();
  ArrayList<Barrier> barrier_ls = new ArrayList<Barrier>();
  ArrayList<Projectile> projectile_ls = new ArrayList<Projectile>();
  ArrayList<Projectile> invader_projectile_ls = new ArrayList<Projectile>();
  boolean pressed_r = false;
  boolean pressed_l = false;
  boolean pressed_s = false;
  boolean pressed_a = false;
  boolean pressed_w= false;
  boolean pressed_d = false;

  public void setup() {
    frameRate(60);
    reset();
  }

    public void reset(){
      //reset barriers if level has been cleared
      if (barrier_ls.size() > 0){
        barrier_ls = new ArrayList<Barrier>();
      }
      if (invader_ls.size() > 0){
        invader_ls = new ArrayList<Invader>();
      }
      //tank starting position
          PImage tank_pic = loadImage("src/main/resources/tank1.png");
          t = new Tank(new PImage[] {tank_pic}, 3, 343, 450, tank_pic.width, tank_pic.height);
          t2 = new Tank(new PImage[] {tank_pic}, 3, 303, 450, tank_pic.width, tank_pic.height);

      //invaders starting position
          PImage invader_pic1 = loadImage("src/main/resources/invader1.png");
          PImage invader_pic2 = loadImage("src/main/resources/invader2.png");
          for(int k = 0; k < 4; k++){
            int height_count = 70 + k*30;
            for(int j = 0; j < 10; j++){
              int gap_count = 170 + j * 30;
              Invader i = new Invader(new PImage[] {invader_pic1, invader_pic2}, gap_count, height_count, invader_pic1.width, invader_pic1.height);
              invader_ls.add(i);
            }
          }

      //barrier starting position
          HashMap<Integer, String> barrier_names = new HashMap<Integer, String>();
          barrier_names.put(0, "left");
          barrier_names.put(1, "top");
          barrier_names.put(2, "right");
          barrier_names.put(3, "solid");

      //top row of barriers
          for (int k = 0; k < 3; k++){
            int gap = k * 112;
            for(int i = 0; i < 3; i ++){
              PImage[] barrier_images = new PImage[3];
              for (int j = 0; j < 3; j++){
                PImage barrier_pic = loadImage("src/main/resources/barrier_" + barrier_names.get(i) + String.valueOf(j+1) + ".png");
                barrier_images[j] = barrier_pic;
              }
              Barrier b = new Barrier(barrier_images, 210 + i*barrier_images[0].width + gap, 420, barrier_images[0].width, barrier_images[0].height);
              barrier_ls.add(b);
            }
          }

      //middle and bottom row of barriers
          for (int l = 0; l < 2; l++){
            int row = l * 8;
            for (int k = 0; k < 3; k++){
              int gap = k * 112;
              for(int i = 0; i < 2; i ++){
                PImage[] barrier_images = new PImage[3];
                for (int j = 0; j < 3; j++){
                  PImage barrier_pic = loadImage("src/main/resources/barrier_" + barrier_names.get(3) + String.valueOf(j+1) + ".png");
                  barrier_images[j] = barrier_pic;
                }
                Barrier b = new Barrier(barrier_images, 210 + i*2*barrier_images[0].width + gap, 428 + row, barrier_images[0].width, barrier_images[0].height);
                barrier_ls.add(b);
              }
            }
          }
      //projectiles reset
      projectile_ls = new ArrayList<Projectile>();
      invader_projectile_ls = new ArrayList<Projectile>();
    }
    public void settings() {
        size(640, 480);
        // fullScreen();
    }

    int timer = 0;
    int move_count = 0;
    boolean down = false;
    boolean gameover = false;
    boolean nextlevel = false;
    int lvltimer = 0;
    int difficulty = 0;

    public void draw() {
      background(0);
//next level
      PImage empty = loadImage("src/main/resources/empty.png");
      PImage next_level = loadImage("src/main/resources/nextlevel.png");
      if (invader_ls.size() <= 0){
        nextlevel = true;
      }
      if (nextlevel){
        image(empty, 0, 0);
        image(next_level, 270, 220);
      }

//game over
      PImage game_over = loadImage("src/main/resources/gameover.png");
      if (t.getLives() <= 0 || t2.getLives() <= 0){
        gameover = true;
      }
      for (Invader i : invader_ls){
        if (i.getY() >= 407){
          gameover = true;
        }
      }
      if (gameover){
        image(empty, 0, 0);
        image(game_over, 270, 220);
      }
      if (gameover){
        lvltimer += 1;
        if (lvltimer > 120){
          reset();
          lvltimer = 0;
          timer = 0;
          move_count = 0;
          down = false;
          nextlevel = false;
          gameover = false;
          difficulty = 0;
        }
      }


//timer before resetting to a new level
      if (nextlevel){
        lvltimer += 1;
        if (lvltimer > 120){
          reset();
          lvltimer = 0;
          timer = 0;
          move_count = 0;
          down = false;
          nextlevel = false;
          if (difficulty < 2){
            difficulty++;
          }
        }
      }

//main game code
      if (!gameover && !nextlevel){
        //moving tank
              if (pressed_l && t.getX() >= 180){
                  t.minusX();
              }
              else if (pressed_r && t.getX() <= 460){
                t.addX();
              }
              image(t.getPic()[0], t.getX(), t.getY());

              if (pressed_a && t2.getX() >= 180){
                t2.minusX();
              }
              else if (pressed_d && t2.getX() <= 460){
                t2.addX();
              }
              image(t2.getPic()[0], t2.getX(), t2.getY());

        //drawing invaders
              if (down){
                for (Invader i : invader_ls){
                  image(i.getPic()[1], i.getX(), i.getY());
                }
              }
              else{
                for (Invader i : invader_ls){
                  image(i.getPic()[0], i.getX(), i.getY());
                }
              }

        //moving invaders
              if(timer%2 == 0){
                if (move_count <= 30){
                  for (Invader i : invader_ls){
                    i.addX();
                  }
                  move_count++;
                }
                else if (move_count > 30 && move_count <= 38){
                  down = true;
                  for (Invader i : invader_ls){
                    i.addY();
                  }
                  move_count++;
                  if (move_count > 38){
                    down = false;
                  }
                }
                else if (move_count > 38 && move_count <= 68){
                  for (Invader i : invader_ls){
                    i.minusX();
                  }
                  move_count++;
                }
                else if (move_count > 68 && move_count <= 76){
                  down = true;
                  for (Invader i : invader_ls){
                    i.addY();
                  }
                  move_count++;
                  if (move_count > 76){
                    move_count = 0;
                    down = false;
                  }
                }
              }

        //drawing barriers
              for (Barrier b : barrier_ls){
                if (b.getHealth() == 3){
                  image(b.getPic()[0], b.getX(), b.getY());
                }
                else if (b.getHealth() == 2){
                  image(b.getPic()[1], b.getX(), b.getY());
                }
                else if (b.getHealth() == 1){
                  image(b.getPic()[2], b.getX(), b.getY());
                }
              }

        //removing barriers
              for (int i = 0; i < barrier_ls.size(); i++){
                if (barrier_ls.get(i).getHealth() <= 0){
                  barrier_ls.remove(i);
                }
              }

        // removing tank and invader projectiles
              for (int i = 0; i < projectile_ls.size(); i++){
                if (projectile_ls.get(i).getY() < 0){
                  projectile_ls.remove(i);
                }
              }

              for (int i = 0; i < invader_projectile_ls.size(); i++){
                if (invader_projectile_ls.get(i).getY() > 480){
                  invader_projectile_ls.remove(i);
                }
              }

        //drawing and moving tank projectiles
              if (projectile_ls != null){
                for (Projectile p : projectile_ls){
                  if (p.getY() >= 0){
                    image(p.getPic()[0], p.getX(), p.getY());
                  }
                }
                for (Projectile p : projectile_ls){
                  if (p.getY() >= 0){
                    p.minusY();
                  }
                }
              }

        //invaders firing
              if(timer%(20-difficulty*15) == 0){
                Projectile i_p = fire(invader_ls, loadImage("src/main/resources/projectile.png"));
                invader_projectile_ls.add(i_p);
              }

        //drawing invader projectiles
              if (invader_projectile_ls != null){
                for (Projectile i_p : invader_projectile_ls){
                  if (i_p.getY() <= 480){
                    image(i_p.getPic()[0], i_p.getX(), i_p.getY());
                  }
                }
                for (Projectile i_p : invader_projectile_ls){
                  if (i_p.getY() <= 480){
                    i_p.addY();
                  }
                }
              }

        // tank projectile collision function
              boolean hit = false;
              for (int i = 0; i < projectile_ls.size(); i++){
                hit = false;
                for(int j = 0; j < barrier_ls.size(); j++){
                  if (projectile_ls.get(i).checkCollision(barrier_ls.get(j))){
                    barrier_ls.get(j).loseHealth();
                    projectile_ls.remove(i);
                    hit = true;
                    break;
                  }
                }
                if (! hit){
                  for(int k = 0; k < invader_ls.size(); k++){
                    if (projectile_ls.get(i).checkCollision(invader_ls.get(k))){
                      invader_ls.remove(k);
                      projectile_ls.remove(i);
                      break;
                    }
                  }
                }
              }
        // invader projectile collision function
              boolean i_hit = false;
              for(int i = 0; i < invader_projectile_ls.size(); i++){
                i_hit = false;
                for(int j = 0; j < barrier_ls.size(); j++){
                  if (invader_projectile_ls.get(i).checkCollision(barrier_ls.get(j))){
                    barrier_ls.get(j).loseHealth();
                    invader_projectile_ls.remove(i);
                    i_hit = true;
                    break;
                  }
                }
                if (! i_hit){
                  if (t.checkCollision(invader_projectile_ls.get(i))){
                    t.loseLife();
                    invader_projectile_ls.remove(i);
                    break;
                  }
                  else if (t2.checkCollision(invader_projectile_ls.get(i))){
                    t2.loseLife();
                    invader_projectile_ls.remove(i);
                    break;
                  }
                }
              }

        //counting frames
              timer++;
            }
      }

    public void keyPressed(){
      if (key == ' '){
        if (pressed_s == false){
          Projectile p = t.shoot(loadImage("src/main/resources/projectile.png"));
          projectile_ls.add(p);
          pressed_s = true;
        }
      }
      if (key == 'w'){
        if (pressed_w == false){
          Projectile p = t2.shoot(loadImage("src/main/resources/projectile.png"));
          projectile_ls.add(p);
          pressed_w = true;
        }
      }
      if (key == CODED){
        if (keyCode == LEFT){
          pressed_l = true;
        }
        if (keyCode == RIGHT){
          pressed_r = true;
        }
      }
      if (key == 'a'){
        pressed_a = true;
      }
      if (key == 'd'){
        pressed_d = true;
      }
    }
    public void keyReleased(){
      if (key == ' '){
        pressed_s = false;
      }
      if (key == 'w'){
        pressed_w = false;
      }
      if (key == CODED){
        if (keyCode == LEFT){
          pressed_l = false;
        }
        if (keyCode == RIGHT){
          pressed_r = false;
        }
      }
      if (key == 'a'){
        pressed_a = false;
      }
      if (key == 'd'){
        pressed_d = false;
      }
    }

//function for invader firing
    public Projectile fire(ArrayList<Invader> ls, PImage graphic){
      int min = 0;
      int max = ls.size() - 1;
      int num = (int)(Math.random()*((max-min) + 1)) + min;
      Invader i = ls.get(num);
      return new Projectile(new PImage[] {graphic}, i.getX(), i.getY() + 10, 1, 3);
    }

    public static void main(String[] args) {
        PApplet.main("invadem.App");
    }
}
