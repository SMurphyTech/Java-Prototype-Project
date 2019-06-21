package proto.tile;

import proto.ProtoPaint;
import proto.enemies.*;
import java.awt.Image;
import java.awt.Rectangle;
import proto.Player.ProtoPlayer;

public class ProtoTile extends ProtoTileLoadImage {

    private int tileX, tileY, speedX, speedY, type;
    public static Rectangle r;
    private ProtoPlayer player = ProtoPaint.getPlayer();
    private ProtoEnemySeeker seek = ProtoPaint.getSeek();
    boolean clear = true;

    public ProtoTile(int x, int y, int typeInt) {
        loadImage();
        tileX = x * 32 * 2;
        tileY = y * 32 * 2;

        type = typeInt;

        r = new Rectangle();

        setImage();

    }

    private void loadImage() {
        load();
    }

    public void setImage() {
        switch (type) {
            case 1:
                tileImage = crate;
                break;
            case 2:
                tileImage = brick;
                break;
            case 3:
                //door sprite goes here
                break;
            case 4:
                //door sprite goes here
                break;
            default:
                type = 0;
                break;
        }
    }

    public void update() {
        speedY = 0;

        tileY += speedY;

        speedX = 0;

        tileX += speedX;

        r.setBounds(tileX, tileY, 32 * 2, 32 * 2);

        if (r.intersects(ProtoPlayer.yellowRed)) {
            checkSideCollision(ProtoPlayer.footleft, ProtoPlayer.footright);
            checkVerticalCollision(ProtoPlayer.rect, ProtoPlayer.rect2);
        }

        setImage();
    }

    public void checkVerticalCollision(Rectangle rtop, Rectangle rbot) {

        if (rtop.intersects(r)) {
            if (type == 1) {
                if (player.isMovingD() == false) {
                    player.setDy(0);
                } else if (player.getDy() < 0) {
                    player.setDy(0);
                }
            }
            if(type == 3){

                    ProtoPaint.changeMaps("exit");
                
            }           
            if(type == 4){

                    ProtoPaint.changeMaps("entrance");
                
            }
        }

        if (rbot.intersects(r)) {
            if (type == 1) {
                if (player.isMovingU() == false) {
                    player.setDy(0); 
                } else if (player.getDy() > 0) {
                    player.setDy(0);
                }
            }
            if(type == 3){
                
                    ProtoPaint.changeMaps("exit");

            }
            if(type == 4){

                    ProtoPaint.changeMaps("entrance");
                
            }
        }

    }

    public void checkSideCollision(Rectangle leftfoot, Rectangle rightfoot) {

        if (rightfoot.intersects(r)) {
            if (type == 1) {
                if (player.isMovingL() == false) {
                    player.setDx(0);
                } else if (player.getDx() > 0) {
                    player.setDx(0);
                }
            }
        }

        if (leftfoot.intersects(r)) {
            if (type == 1) {
                if (player.isMovingR() == false) {
                    player.setDx(0);
                } else if (player.getDx() < 0) {
                    player.setDx(0);
                }
            }
        }

        if (rightfoot.intersects(seek.getaHitBox())) {
            player.takeDamage();
        }
    }

    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public Image getTileImage() {
        return tileImage;
    }

}
