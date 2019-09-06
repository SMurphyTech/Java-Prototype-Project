package proto.enemies;

import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

public class ProtoEnemyShooter extends ProtoEnemy {
    boolean shooting;
    
    //determines the square in which the shooter moves
    int topY, bottomY, rightX, leftX;

    //determines where the shooter is currently moving to 
    enum Dir {
        up, down, right, left
    }
    Dir dir = Dir.right;
    
    //makes the attack() method determine the shooter's next direction at the beginning of its attack
    int count = 0;
    
    //timer for how long the shooter stops
    public Timer wait;
    
    //bullet hitboxes
    public Rectangle upBullet = new Rectangle(0, 0, 0, 0), downBullet = new Rectangle(0, 0, 0, 0), 
            rightBullet = new Rectangle(0, 0, 0, 0), leftBullet = new Rectangle(0, 0, 0, 0);
    
    //bullet coords
    int upBulletY, downBulletY, rightBulletX, leftBulletX;
    //bullet speed
    int bulletSpeed = 9;

    public ProtoEnemyShooter(int x, int y) {
        this.x = x;
        this.y = y;
        health = 4;
        //makes shooter invincible forever
        invincible = true;

        topY = y;
        bottomY = y + 400;
        rightX = x + 400;
        leftX = x;

        projectiles.add(upBullet);
        projectiles.add(downBullet);
        projectiles.add(rightBullet);
        projectiles.add(leftBullet);
    }

    @Override
    public void update() {
        x = x + dx;
        y = y + dy;
//        updateImage();        
        
        if (attacking == true) {
            attack();
        } else if (knocked == false) {
            idle();
        }
        
        if(shooting == true){
            bulletsUpdate();
        }

        aHitBox.setRect(x - 37, y - 37, 76, 76);
        
        healthUpdate();
    }

    @Override
    public void attack() {
        
        dx = 0;
        dy = 0;

        if (count == 0) {
            switch (dir) {
                case up:
                    dir = Dir.right;
                    break;
                case down:
                    dir = Dir.left;
                    break;
                case right:
                    dir = Dir.down;
                    break;
                case left:
                    dir = Dir.up;
                    break;
                    
            }
            
            upBulletY = aHitBox.y;
            downBulletY = aHitBox.y;
            rightBulletX = aHitBox.x;
            leftBulletX = aHitBox.x;
            shooting = true;
            
            wait = new Timer();
            wait.schedule(new waitOver(), 1000);
            
            count += 1;
        }
    }

    @Override
    public void idle() {
        count = 0;
        switch (dir) {
            case up:
                dy = -4;
                if(y <= topY){
                    attacking = true;
                }
                break;
            case down:
                dy = 4;
                if(y >= bottomY){
                    attacking = true;
                }
                break;
            case right:
                dx = 4;
                if(x >= rightX){
                    attacking = true;
                }
                break;
            case left:
                dx = -4;
                if(x <= leftX){
                    attacking = true;
                }
                break;

        }
    }
    
    public void bulletsUpdate() {
        upBulletY = upBulletY - bulletSpeed;
        upBullet.setBounds(aHitBox.x + 20, upBulletY, 35, 100);
        
        downBulletY = downBulletY + bulletSpeed;
        downBullet.setBounds(aHitBox.x + 20, downBulletY, 35, 100);
        
        rightBulletX = rightBulletX + bulletSpeed;
        rightBullet.setBounds(rightBulletX, aHitBox.y + 20, 100, 35);
        
        leftBulletX = leftBulletX - bulletSpeed;
        leftBullet.setBounds(leftBulletX, aHitBox.y + 20, 100, 35);
        
    }
    
    public void bulletsCancel() {
        upBullet.setBounds(0, 0, 0, 0);
        downBullet.setBounds(0, 0, 0, 0);
        rightBullet.setBounds(0, 0, 0, 0);
        leftBullet.setBounds(0, 0, 0, 0);
    }
    
    class waitOver extends TimerTask {
        
        @Override
        public void run(){
            attacking = false;            
        }
    }
}
