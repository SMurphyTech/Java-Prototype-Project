package proto.enemies;

public class ProtoEnemyCharger extends ProtoEnemy {
    int rightLimit, leftLimit;
    
    public ProtoEnemyCharger(int x, int y) {
        setX(x);
        setY(y);
        health = 3;
        rightLimit = x;
        leftLimit = x - 900;
    }
    
    public void update() {
        x = x + dx;
        y = y + dy;
        
        if(attacking == true){
            if(x < leftLimit || x > rightLimit){
                attacking = false;
                dx = 0;
                dy = -6;
            }
        }else if(knocked == false){
            idle();
            
        }
        
        aHitBox.setRect(x - 37, y - 37, 76, 76);
        playX = player.getX();
        playY = player.getY();
        healthUpdate();
    }
    
    public void idle(){               
        if(y > playY - 20 && y < playY + 20 && playX > leftLimit && playX < rightLimit){
            dy = 0;
            attack();
            attacking = true;
        }else if(y < 100){
            dy = 6;
        }else if(y > 800){
            dy = -6;
        }
    }
    
    public void attack(){
        if(x > playX){
            dx = -10;
        }else if(x < playX){
            dx = 10;
        }
    }
}
