package proto.enemies;

import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import proto.framework.ProtoAnimation;

public class ProtoEnemySeeker extends ProtoEnemy {
    
    public static Timer attackOn, attackOff;
    public Image idle1, attack1, hit1;
    
    public ProtoEnemySeeker(int x, int y) {
        setX(x);
        setY(y);
        health = 3;
        loadImage();
        
        attackLoop();
    }
    
    public void loadImage(){
        spaceX = 45;
        spaceY = 40;
        animSpeed = 3;
        
        ImageIcon si = new ImageIcon("src\\sprites\\enemies\\seeker\\SeekerIdle.png");
        idle1 = si.getImage();
        
        ImageIcon sa = new ImageIcon("src\\sprites\\enemies\\seeker\\SeekerAttack.png");
        attack1 = sa.getImage();
        
        ImageIcon sh = new ImageIcon("src\\sprites\\enemies\\seeker\\SeekerAttack.png");
        hit1 = sh.getImage();
        
        idleAnim = new ProtoAnimation();
        idleAnim.addFrame(idle1, 100);
        idleAnim.addFrame(attack1, 100);
        
        attackAnim = new ProtoAnimation();
        attackAnim.addFrame(attack1, 100);
        
        hitAnim = new ProtoAnimation();
        hitAnim.addFrame(hit1, 100);
    }

    @Override
    public void update() {      
        x = x + dx;
        y = y + dy;
        updateImage();

        if(attacking == true){
            attack();
        }else if(knocked == false){
            idle();
        }
        
        aHitBox.setRect(x - 37, y - 37, 76, 76);
        playX = player.getX();
        playY = player.getY();
        healthUpdate();
    }
    
    @Override
    public void idle(){
        dx = 0;
        dy = 0;
    }
    
    @Override
    public void attack(){

        //disables regular movement while knockback is activated
        if (knocked == false) {
            if (x > playX) {
                dx = -2;
            } else if (x < playX) {
                dx = 2;
            } else {
                dx = 0;
            }

            if (y > playY) {
                dy = -2;
            } else if (y < playY) {
                dy = 2;
            } else {
                dy = 0;
            }
        }
    }

    public void attackLoop(){
        attacking = false;
        attackOn = new Timer();
        attackOn.schedule(new attackStart(), 1000);
    }
    
    class attackStart extends TimerTask{       
        @Override
        public void run(){
            attacking = true;
            attackOff = new Timer();
            attackOff.schedule(new attackOver(), 1000);
        }
    }
    
    class attackOver extends TimerTask{        
        @Override
        public void run(){
            attacking = false;
            attackOn = new Timer();
            attackOn.schedule(new attackStart(), 1000);
        }
    }
}
