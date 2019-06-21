package proto.enemies;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import proto.Player.ProtoPlayer;
import proto.Player.ProtoPlayer.Dir;
import proto.Player.ProtoAttack;
import proto.ProtoPaint;

public class ProtoEnemy {

    public int x, y, dx, dy;
    public int health = 3;
    public boolean invincible = false, knocked = false;
    public Image attackImage;
    public Rectangle aHitBox = new Rectangle(0, 0, 0, 0);
    static Random rand = new Random();
    public static Timer invincibility, knockback;

    public static ProtoPlayer player = ProtoPaint.getPlayer();
    public static int playX = player.getX();
    public static int playY = player.getY();

    public void takeDamage() {
        health -= 1;
        System.out.println("Monster is down to: " + health + " health!");
        
        
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
        }
    }

    public void healthUpdate() {
        if (aHitBox.intersects(ProtoAttack.getSliceBox())) {
            if (invincible == false) {
                takeDamage();
            }
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getaHitBox() {
        return aHitBox;
    }

}
