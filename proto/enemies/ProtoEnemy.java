package proto.enemies;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import proto.Player.ProtoPlayer;
import proto.Player.ProtoAttack;
import proto.ProtoPaint;
import proto.framework.ProtoAnimation;

public class ProtoEnemy {

    //static variables = no no
    public int x, y, dx, dy;
    public int health;
    public boolean invincible = false, knocked = false;
    public boolean attacking = false, winding = false;
    public boolean collision = false;
    public Image currentImage, idleImage, attackImage, hitImage;
    public ProtoAnimation currentAnim, idleAnim, attackAnim, hitAnim, windupAnim;
    public int animSpeed = 7; 
    public int spaceX, spaceY, imageX, imageY;
    public Rectangle aHitBox = new Rectangle(0, 0, 0, 0);
    static Random rand = new Random();
    public static Timer invincibility, knockback;
    
    public static ArrayList<ProtoEnemy> remainingEnemies = new ArrayList<ProtoEnemy>();   
    public ArrayList<Rectangle> projectiles = new ArrayList<Rectangle>();

    public static ProtoPlayer player = ProtoPaint.getPlayer();
    public static int playX = player.getX();
    public static int playY = player.getY();

    public static void loadEnemies(ArrayList enemies) {       
        
        //remainingEnemies.clear();
        
        remainingEnemies = enemies;
               
        System.out.println(enemies);
    }
    
    public void update() {
        //monster is updated
    }
    
    public void updateImage() {
        if(winding == true){
            currentAnim = windupAnim;
        }else if(attacking == true){
            currentAnim = attackAnim;
        }else if(knocked == true){
            currentAnim = hitAnim;
        }else{
            currentAnim = idleAnim;
        }
        
        currentAnim.update(animSpeed);
        currentImage = currentAnim.getImage();
        
        imageX = x - spaceX;
        imageY = y - spaceY;
    }
    
    public void spawn() {
        //monster appears
    }
    
    public void attack() {
        //monster attacks
    }
    
    public void idle() {
        //when monster is idle
    }
    
    public void takeDamage() {
        health -= 1;
//        System.out.println("Monster is down to: " + health + " health!");
        
        
        //invincibility for enemies?
        invincible = true;
        invincibility = new Timer();
        invincibility.schedule(new invinceOver(), 1000);
        
        
        knocked = true;
        if(null != player.getDir())switch (player.getDir()) {
            case UP:
                dy = -7;
                break;
            case DOWN:
                dy = 7;
                break;
            case RIGHT:
                dx = 7;
                break;
            case LEFT:
                dx = -7;
                break;
            default:
                break;
        }
        knockback = new Timer();
        knockback.schedule(new knockOver(), 300);
    }

    class invinceOver extends TimerTask {

        @Override
        public void run() {
            invincible = false;
            //invincibilty over
        }
    }

    class knockOver extends TimerTask {

        @Override
        public void run() {
            knocked = false;
            //knockback over
            if(health == 0){
                die();
            }
        }
    }

    public void healthUpdate() {
        if (aHitBox.intersects(ProtoAttack.getSliceBox())) {
            if (invincible == false) {
                takeDamage();
            }
        }
    }
    
    public void die(){
        //monster dies
        System.out.println("die");
        remainingEnemies.remove(this);
    }
    
    public void reset(){
        invincible = false;
        knocked = false;
        winding = false;
        attacking = false;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public int getImageX() {
        return imageX;
    }

    public int getImageY() {
        return imageY;
    }

    public Rectangle getaHitBox() {
        return aHitBox;
    }   
    
    public Image getCurrentImage() {
        return currentImage;
    }

}
