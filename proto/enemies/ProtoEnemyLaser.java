package proto.enemies;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import proto.framework.ProtoAnimation;

public class ProtoEnemyLaser extends ProtoEnemy {

    int originalPlayX;

    public enum Facing {
        RIGHT, LEFT;
    }
    public static Facing facing = Facing.LEFT;
    public Rectangle laserBox = new Rectangle(0, 0, 0, 0);
    public static Timer winder, attackOn, attackOff;
    public boolean canAttack, backingup;
    public static Image walk1, walk2, walk3, walk1R, walk2R, walk3R, wind1, wind2, wind3, wind4, wind5, 
            wind1L, wind2L, wind3L, wind4L, wind5L, laser1, laser1R, laser2;
    public ProtoAnimation idleR, idleL, attackR, attackL, windupR, windupL;

    public ProtoEnemyLaser(int x, int y) {
        this.x = x;
        this.y = y;
        health = 2;
//        collision = true;
        loadImage();

        projectiles.add(laserBox);
        attackLoop();
    }

    public void loadImage() {
        spaceX = 50;
        spaceY = 40;
        ImageIcon li1 = new ImageIcon("src\\sprites\\enemies\\laser\\laserIdle1.png");
        walk1 = li1.getImage();
        ImageIcon li2 = new ImageIcon("src\\sprites\\enemies\\laser\\laserIdle2.png");
        walk2 = li2.getImage();
        ImageIcon li3 = new ImageIcon("src\\sprites\\enemies\\laser\\laserIdle3.png");
        walk3 = li3.getImage();
        
        ImageIcon li1R = new ImageIcon("src\\sprites\\enemies\\laser\\laserIdle1R.png");
        walk1R = li1R.getImage();
        ImageIcon li2R = new ImageIcon("src\\sprites\\enemies\\laser\\laserIdle2R.png");
        walk2R = li2R.getImage();
        ImageIcon li3R = new ImageIcon("src\\sprites\\enemies\\laser\\laserIdle3R.png");
        walk3R = li3R.getImage();
        
        ImageIcon w1 = new ImageIcon("src\\sprites\\enemies\\laser\\LaserCharge1.png");
        wind1 = w1.getImage();
        ImageIcon w2 = new ImageIcon("src\\sprites\\enemies\\laser\\LaserCharge2.png");
        wind2 = w2.getImage();
        ImageIcon w3 = new ImageIcon("src\\sprites\\enemies\\laser\\LaserCharge3.png");
        wind3 = w3.getImage();
        ImageIcon w4 = new ImageIcon("src\\sprites\\enemies\\laser\\LaserCharge4.png");
        wind4 = w4.getImage();
        ImageIcon w5 = new ImageIcon("src\\sprites\\enemies\\laser\\LaserCharge5.png");
        wind5 = w5.getImage();
        
        ImageIcon w1L = new ImageIcon("src\\sprites\\enemies\\laser\\LaserCharge1L.png");
        wind1L = w1L.getImage();
        ImageIcon w2L = new ImageIcon("src\\sprites\\enemies\\laser\\LaserCharge2L.png");
        wind2L = w2L.getImage();
        ImageIcon w3L = new ImageIcon("src\\sprites\\enemies\\laser\\LaserCharge3L.png");
        wind3L = w3L.getImage();
        ImageIcon w4L = new ImageIcon("src\\sprites\\enemies\\laser\\LaserCharge4L.png");
        wind4L = w4L.getImage();
        ImageIcon w5L = new ImageIcon("src\\sprites\\enemies\\laser\\LaserCharge5L.png");
        wind5L = w5L.getImage();
        
        ImageIcon la1 = new ImageIcon("src\\sprites\\enemies\\laser\\laserAttack1.png");
        laser1 = la1.getImage();
        ImageIcon la1R = new ImageIcon("src\\sprites\\enemies\\laser\\laserAttackR.png");
        laser1R = la1R.getImage();
        
        ImageIcon la2 = new ImageIcon("src\\sprites\\enemies\\laser\\laserAttack2.png");
        laser2 = la2.getImage();

        idleAnim = new ProtoAnimation();
        
        idleL = new ProtoAnimation();
        idleL.addFrame(walk1, 100);
        idleL.addFrame(walk2, 100);
        idleL.addFrame(walk3, 100);
        idleL.addFrame(walk2, 100);
        
        idleR = new ProtoAnimation();
        idleR.addFrame(walk1R, 100);
        idleR.addFrame(walk2R, 100);
        idleR.addFrame(walk3R, 100);
        idleR.addFrame(walk2R, 100);
        
        idleAnim = idleL;
        
        
        attackAnim = new ProtoAnimation();
        
        attackL = new ProtoAnimation();
        attackL.addFrame(laser1, 100);
        
        attackR = new ProtoAnimation();
        attackR.addFrame(laser1R, 100);
        
        attackAnim = attackL;

        
        hitAnim = new ProtoAnimation();
        hitAnim.addFrame(walk1, 100);

        windupAnim = new ProtoAnimation();
        
        windupL = new ProtoAnimation();
        windupL.addFrame(wind1L, 100);
        windupL.addFrame(wind2L, 100);
        windupL.addFrame(wind3L, 100);
        windupL.addFrame(wind4L, 100);
        windupL.addFrame(wind5L, 100);
        
        windupR = new ProtoAnimation();
        windupR.addFrame(wind1, 100);
        windupR.addFrame(wind2, 100);
        windupR.addFrame(wind3, 100);
        windupR.addFrame(wind4, 100);
        windupR.addFrame(wind5, 100);
        
        windupAnim = windupL;
    }

    @Override
    public void update() {
        x = x + dx;
        y = y + dy;

        if (x > 1600) {
            canAttack = false;
        } else if (x < 0) {
            canAttack = false;
        }

        if (winding == true) {
            windup();
        } else if (attacking == true) {
            attack();
        } else if (backingup == true) {
            backup();
        } else if (knocked == false) {
            idle();
        }

        aHitBox.setRect(x - 37, y - 37, 76, 76);
        playX = player.getX();
        playY = player.getY();
        healthUpdate();
        updateImage();
        if(facing == Facing.LEFT){
            idleAnim = idleL;
            attackAnim = attackL;
            windupAnim = windupL;
        }else if(facing == Facing.RIGHT){
            idleAnim = idleR;
            attackAnim = attackR;
            windupAnim = windupR;
        }
    }

    @Override
    public void idle() {
        laserBox.setBounds(-100, -100, 0, 0);

        if (x > 1600) {
            x = 0;
            facing = Facing.RIGHT;
        } else if (x < 0) {
            x = 1600;
            facing = Facing.LEFT;
        }

        if (x > playX && x < playX + 300) {
            //running away and lasering
            dx = 6;
            facing = Facing.LEFT;
            canAttack = true;
        } else if (x < playX && x > playX - 300) {
            //running away and lasering
            dx = -6;
            facing = Facing.RIGHT;
            canAttack = true;
        } else if (x < playX && x < playX - 300) {
            //approaching slowly
            dx = 1;

        } else if (x > playX && x > playX + 300) {
            // approaching slowly
            dx = -1;

        } else {
            originalPlayX = playX;
            backingup = true;
            canAttack = true;
        }
    }
  
    @Override
    public void attack() {
        if (canAttack == true) {
            dx = 0;
            dy = 0;
            if (facing == Facing.LEFT) {
                laserBox.setBounds(0, y - 37, x - 37, 76);
            } else if (facing == Facing.RIGHT) {
                laserBox.setBounds(x + 37, y - 37, 1600 - x, 76);
            }

        } else {
            attacking = false;
        }
    }

    public void windup() {
        if (canAttack == true) {
            dy = 0;
            dx = 0;
        } else {
            winding = false;
        }
    }

    public void backup() {
        if (x >= originalPlayX + 600 || x <= originalPlayX - 600) {
            dx = 0;
        } else {
            if (facing == Facing.LEFT) {
                dx = 6;
            } else if (facing == Facing.RIGHT) {
                dx = -6;
            }
        }
    }

    public void attackLoop() {
        attacking = false;
        winder = new Timer();
        winder.schedule(new windupStart(), 1000);
    }

    class attackStart extends TimerTask {

        @Override
        public void run() {
            winding = false;
            attacking = true;
            backingup = false;
            attackOff = new Timer();
            attackOff.schedule(new attackOver(), 1000);
        }
    }

    class attackOver extends TimerTask {

        @Override
        public void run() {
            attacking = false;
            winder = new Timer();
            winder.schedule(new windupStart(), 1000);
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
