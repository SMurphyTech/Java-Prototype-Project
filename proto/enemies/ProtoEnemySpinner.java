package proto.enemies;

import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import proto.framework.ProtoAnimation;

public class ProtoEnemySpinner extends ProtoEnemy {

    public Image idle1, idle2, idle3, jump, stand1, stand2, spin1, spin2;
    public static Timer winder, attackOn, attackOff;
    public int count = 0;
    int c = 0;

    public ProtoEnemySpinner(int x, int y) {
        this.x = x;
        this.y = y;
        health = 6;
        loadImage();

        attackLoop();
    }
    
    public void loadImage() {
        spaceX = 80;
        spaceY = 100;
        animSpeed = 5;
        
        ImageIcon dd1 = new ImageIcon("src\\sprites\\enemies\\spinner\\Dust Devil.png");
        idle1 = dd1.getImage();
        ImageIcon dd2 = new ImageIcon("src\\sprites\\enemies\\spinner\\Dust Devil walk left.png");
        idle2 = dd2.getImage();
        ImageIcon dd3 = new ImageIcon("src\\sprites\\enemies\\spinner\\Dust Devil walk right.png");
        idle3 = dd3.getImage();
        
        ImageIcon ddjump = new ImageIcon("src\\sprites\\enemies\\spinner\\Dust Devil jump.png");
        jump = ddjump.getImage();
        ImageIcon ddstand1 = new ImageIcon("src\\sprites\\enemies\\spinner\\Dust Devil stand low.png");
        stand1 = ddstand1.getImage();
        ImageIcon ddstand2 = new ImageIcon("src\\sprites\\enemies\\spinner\\Dust Devil stand high.png");
        stand2 = ddstand2.getImage();
        
        ImageIcon ddspin1 = new ImageIcon("src\\sprites\\enemies\\spinner\\Dust Devil spin 1.png");
        spin1 = ddspin1.getImage();
        ImageIcon ddspin2 = new ImageIcon("src\\sprites\\enemies\\spinner\\Dust Devil spin 2.png");
        spin2 = ddspin2.getImage();
        
        idleAnim = new ProtoAnimation();
        idleAnim.addFrame(idle1, 100);
        idleAnim.addFrame(idle2, 100);
        idleAnim.addFrame(idle1, 100);
        idleAnim.addFrame(idle3, 100);
        
        attackAnim = new ProtoAnimation();
        attackAnim.addFrame(idle1, 100);
        
        hitAnim = new ProtoAnimation();
        hitAnim.addFrame(idle1, 100);
        
        windupAnim = new ProtoAnimation();
        windupAnim.addFrame(idle1, 100);
        windupAnim.addFrame(jump, 100);
        windupAnim.addFrame(stand1, 100);
        windupAnim.addFrame(stand2, 100);
        
    }

    @Override
    public void update() {
        x = x + dx;
        y = y + dy;
        updateImage();

        if (winding == true) {
            windup();
            c += 1;
//            System.out.println(c);
        } else if (attacking == true) {
            attack();
        } else if (knocked == false) {
            idle();
        }
        if (attacking == true) {
            aHitBox.setRect(x - 148, y - 148, 304, 304);
        } else {
            aHitBox.setRect(x - 74, y - 74, 152, 152);
        }
        if (attacking == false) {
            playX = player.getX();
            playY = player.getY();
        }
        healthUpdate();
    }

    @Override
    public void idle() {
        invincible = false;
        count = 0;

        if (x > playX) {
            dx = -1;
        } else if (x < playX) {
            dx = 1;
        } else {
            dx = 0;
        }

        if (y > playY) {
            dy = -1;
        } else if (y < playY) {
            dy = 1;
        } else {
            dy = 0;
        }

    }

    @Override
    public void attack() {
        invincible = true;

//        if (count == 0) {
            if (x > playX) {
                dx = -3;
            } else if (x < playX) {
                dx = 3;
            } else {
                dx = 0;
            }

            if (y > playY) {
                dy = -3;
            } else if (y < playY) {
                dy = 3;
            } else {
                dy = 0;
            }
//        }
        count += 1;
    }

    public void windup() {
        dy = 0;
        dx = 0;
    }

    public void attackLoop() {
        attacking = false;
        winder = new Timer();
        winder.schedule(new windupStart(), 2000);
    }

    class attackStart extends TimerTask {

        @Override
        public void run() {
            winding = false;
            attacking = true;
            attackOff = new Timer();
            attackOff.schedule(new attackOver(), 2000);
        }
    }

    class attackOver extends TimerTask {

        @Override
        public void run() {
            attacking = false;
            winder = new Timer();
            winder.schedule(new windupStart(), 2000);
        }
    }

    class windupStart extends TimerTask {

        @Override
        public void run() {
            winding = true;
            attackOn = new Timer();
            attackOn.schedule(new attackStart(), 900);
        }
    }
}
