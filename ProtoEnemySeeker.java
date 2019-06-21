package proto.enemies;

public class ProtoEnemySeeker extends ProtoEnemy {

    public ProtoEnemySeeker(int x, int y) {
        setX(x);
        setY(y);
    }

    public void update() {
        x = x + dx;
        y = y + dy;

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

        aHitBox.setRect(x - 37, y - 37, 76, 76);
        playX = player.getX();
        playY = player.getY();
        healthUpdate();
    }

}
